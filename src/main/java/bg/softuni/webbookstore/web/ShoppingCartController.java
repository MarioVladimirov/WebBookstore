package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.CartItemViewModel;
import bg.softuni.webbookstore.service.BookService;
import bg.softuni.webbookstore.service.ShoppingCartService;
import bg.softuni.webbookstore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final BookService bookService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, BookService bookService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping()
    public String showShoppingCart(Model model,
                                   @AuthenticationPrincipal UserDetails principal) {

        getCartItemsByCustomer(model, principal);

        return "shopping-cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            Model model,
                            @AuthenticationPrincipal UserDetails principal) {

        shoppingCartService.addToCart(id, principal.getUsername());

        getCartItemsByCustomer(model, principal);

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromWishlist(@PathVariable Long id,
                                     Model model,
                                     @AuthenticationPrincipal UserDetails principal) {

        shoppingCartService.removeFromCart(id, principal.getUsername());

        getCartItemsByCustomer(model, principal);

        return "redirect:/cart";
    }

    @GetMapping("/decrease-quantity/{id}")
    public String decreaseQuantity(@PathVariable Long id,
                                   Model model,
                                   @AuthenticationPrincipal UserDetails principal) {

        shoppingCartService.decreaseQuantity(id, principal.getUsername());

        getCartItemsByCustomer(model, principal);

        return "redirect:/cart";
    }

    private void getCartItemsByCustomer(Model model, @AuthenticationPrincipal UserDetails principal) {
        List<CartItemViewModel> cartItemViewModels = shoppingCartService
                .getCartItemsByCustomer(principal.getUsername());

        model.addAttribute("cartItems", cartItemViewModels);
    }
}
