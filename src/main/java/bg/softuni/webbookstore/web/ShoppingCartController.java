package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.CartItemViewModel;
import bg.softuni.webbookstore.service.ShoppingCartService;
import bg.softuni.webbookstore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String showShoppingCart(Model model,
                                   @AuthenticationPrincipal UserDetails principal) {

        List<CartItemViewModel> cartItemViewModels = shoppingCartService
                .listCartItemsByCustomer(principal.getUsername());

        model.addAttribute("cartItems", cartItemViewModels);

        return "shopping-cart";
    }
}
