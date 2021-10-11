package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.UserEntity;
import bg.softuni.webbookstore.model.view.CartItemViewModel;
import bg.softuni.webbookstore.repository.CartItemRepository;
import bg.softuni.webbookstore.repository.UserRepository;
import bg.softuni.webbookstore.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ShoppingCartServiceImpl(CartItemRepository cartItemRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartItemViewModel> listCartItemsByCustomer(String username) {
        return cartItemRepository
                .findByCustomerUsername(username)
                .stream()
                .map(cartItemEntity -> modelMapper
                        .map(cartItemEntity, CartItemViewModel.class))
                .collect(Collectors.toList());
    }


    /*
    UserEntity customer = userRepository
            .findByUsername(bookAddServiceModel.getCreator())
            .orElseThrow(() -> new IllegalArgumentException(
                    "Creator " + bookAddServiceModel.getCreator() + " could not be found")
            );
        bookEntity.setCustomer(customer);

     */
}
