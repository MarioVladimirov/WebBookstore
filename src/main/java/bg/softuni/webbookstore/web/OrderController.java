package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public String orders(Model model,
                         @AuthenticationPrincipal UserDetails principal) {

        model.addAttribute("orders", orderService
                .findAllOrdersByCustomer(principal.getUsername()));

        return "orders";
    }

    @GetMapping("/{id}")
    public String orderDetails(@PathVariable Long id,
                               Model model) {

        model.addAttribute("order", orderService.findById(id));

        return "order-details";
    }

    @GetMapping("/create")
    public String createOrder(@AuthenticationPrincipal UserDetails principal) {

        Long orderId = orderService
                .createOrder(principal.getUsername());

        return "redirect:/orders/" + orderId;
    }
}
