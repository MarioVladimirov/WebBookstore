package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.OrderEntity;
import bg.softuni.webbookstore.model.entity.OrderItemEntity;
import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.entity.enums.OrderStatusEnum;
import bg.softuni.webbookstore.model.view.CartItemViewModel;
import bg.softuni.webbookstore.model.view.OrderViewModel;
import bg.softuni.webbookstore.repository.BookRepository;
import bg.softuni.webbookstore.repository.OrderItemRepository;
import bg.softuni.webbookstore.repository.OrderRepository;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.service.OrderService;
import bg.softuni.webbookstore.service.ShoppingCartService;
import bg.softuni.webbookstore.service.UserService;
import bg.softuni.webbookstore.utils.StringUtils;
import bg.softuni.webbookstore.web.exception.EmptyOrderException;
import bg.softuni.webbookstore.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bg.softuni.webbookstore.constant.GlobalConstants.*;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, BookRepository bookRepository, UserService userService, ShoppingCartService shoppingCartService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderViewModel> findAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(this::getOrderViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderViewModel> findAllOrdersByCustomer(String username) {
        return orderRepository
                .findByCustomerUsernameOrderByOrderTimeDesc(username)
                .stream()
                .map(this::getOrderViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderViewModel> findLastFiveOrdersByCustomer(String username) {
        return orderRepository
                .findTop5ByCustomerUsernameOrderByOrderTimeDesc(username)
                .stream()
                .map(this::getOrderViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public Long createOrder(String username) {

        List<CartItemViewModel> itemsToOrder = shoppingCartService
                .getCartItemsByCustomer(username);

        if (itemsToOrder.size() == 0) {
            throw new EmptyOrderException(EMPTY_ORDER_ERROR_MESSAGE);
        }

        BigDecimal totalPrice = itemsToOrder
                .stream()
                .map(CartItemViewModel::calculatePrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));

        OrderEntity orderEntity = new OrderEntity()
                .setOrderTime(LocalDateTime.now())
                .setPrice(totalPrice)
                .setStatus(OrderStatusEnum.ORDERED)
                .setCustomer(getUserEntity(username));

        OrderEntity newOrder = orderRepository.save(orderEntity);

        itemsToOrder
                .stream()
                .map(this::getOrderItemEntity)
                .map(orderItemEntity -> orderItemEntity.setOrder(newOrder))
                .forEach(orderItemRepository::save);

        shoppingCartService.deleteOrderedCardItems(username);

        return newOrder.getId();
    }

    @Override
    public Optional<OrderViewModel> findById(Long id) {
        return orderRepository
                .findById(id)
                .map(this::getOrderViewModel);
    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<UserEntity> userOpt = userRepository
                .findByUsername(userName);
        Optional<OrderEntity> orderOpt = orderRepository
                .findById(id);

        if (userOpt.isEmpty() || orderOpt.isEmpty()) {
            return false;
        } else {
            OrderEntity orderEntity = orderOpt.get();

            return userService.isAdmin(userOpt.get().getUsername())
                    || orderEntity.getCustomer().getUsername().equals(userName);
        }
    }

    @Override
    public void updateStatus(Long orderId, OrderStatusEnum status) {
        OrderEntity orderEntity = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_ORDER));

        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
    }

    @Override
    public void proceedOrder(Long id) {
        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_ORDER));

        OrderStatusEnum currentStatus = orderEntity.getStatus();
        if (!currentStatus.equals(OrderStatusEnum.DELIVERED)) {
            OrderStatusEnum newStatus = currentStatus.next();
            orderEntity.setStatus(newStatus);
            orderRepository.save(orderEntity);
        }
    }

    @Override
    public boolean canChangeStatus(Long orderId) {
        OrderEntity orderEntity = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException(OBJECT_NAME_ORDER));

        return orderEntity
                .getOrderedBooks()
                .stream()
                .anyMatch(orderItemEntity -> orderItemEntity.getBook().getActive())
                && !orderEntity.getStatus().equals(OrderStatusEnum.DELIVERED);
    }


    private OrderItemEntity getOrderItemEntity(CartItemViewModel cartItem) {
        return new OrderItemEntity()
                .setQuantity(cartItem.getQuantity())
                .setBook(bookRepository
                        .findByIdAndActiveTrue(cartItem.getBook().getId())
                        .orElseThrow(() ->
                                new ObjectNotFoundException("book")));
    }

    private OrderViewModel getOrderViewModel(OrderEntity orderEntity) {
        return modelMapper
                .map(orderEntity, OrderViewModel.class)
                .setCustomerFullName(StringUtils.getFullNameAsString(
                        orderEntity.getCustomer().getFirstName(),
                        orderEntity.getCustomer().getLastName()));
    }

    private UserEntity getUserEntity(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("user"));
    }
}
