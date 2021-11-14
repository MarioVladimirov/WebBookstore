package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.view.OrderViewModel;
import bg.softuni.webbookstore.service.OrderService;
import bg.softuni.webbookstore.web.exception.EmptyOrderException;
import bg.softuni.webbookstore.web.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static bg.softuni.webbookstore.constant.GlobalConstants.*;

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

        OrderViewModel orderViewModel = orderService
                .findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException(OBJECT_NAME_ORDER));

        model.addAttribute("order", orderViewModel);

        return "order-details";
    }

    @GetMapping("/create")
    public String createOrder(@AuthenticationPrincipal UserDetails principal) {

        Long orderId = orderService
                .createOrder(principal.getUsername());

        return "redirect:/orders/" + orderId;
    }

    @ExceptionHandler(EmptyOrderException.class)
    public ModelAndView handleEmptyOrderExceptions(EmptyOrderException e) {
        ModelAndView modelAndView = new ModelAndView("errors/empty-order-error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}
