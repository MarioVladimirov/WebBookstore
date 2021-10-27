package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.OrderEntity;
import bg.softuni.webbookstore.model.view.OrderViewModel;
import bg.softuni.webbookstore.repository.OrderRepository;
import bg.softuni.webbookstore.service.OrderService;
import bg.softuni.webbookstore.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderViewModel> findAllOrdersByCustomer(String username) {
        return orderRepository
                .findByCustomerUsernameOrderByDateDesc(username)
                .stream()
                .map(this::getOrderViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderViewModel> findLastFiveOrdersByCustomer(String username) {
        return orderRepository
                .findTop5ByCustomerUsernameOrderByDateDesc(username)
                .stream()
                .map(this::getOrderViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public OrderViewModel findById(Long id) {
        return orderRepository
                .findById(id)
                .map(this::getOrderViewModel)
                .orElseThrow(() ->
                        new IllegalStateException("Order not found"));
    }

    private OrderViewModel getOrderViewModel(OrderEntity orderEntity) {
        return modelMapper
                .map(orderEntity, OrderViewModel.class)
                .setCustomerFullName(StringUtils.getFullNameAsString(
                        orderEntity.getCustomer().getFirstName(),
                        orderEntity.getCustomer().getLastName()));
    }
}
