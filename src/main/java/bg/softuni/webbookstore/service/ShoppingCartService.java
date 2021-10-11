package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.view.CartItemViewModel;

import java.util.List;

public interface ShoppingCartService {

    List<CartItemViewModel> listCartItemsByCustomerUsername(String username);
}
