package bg.softuni.webbookstore.web;

import bg.softuni.webbookstore.model.entity.enums.OrderStatusEnum;
import bg.softuni.webbookstore.model.view.OrderViewModel;
import bg.softuni.webbookstore.service.OrderService;
import bg.softuni.webbookstore.web.exception.EmptyOrderException;
import bg.softuni.webbookstore.web.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static bg.softuni.webbookstore.constant.GlobalConstants.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());

        return "orders";
    }

    @GetMapping("/my-orders")
    public String ordersByCustomer(Model model,
                         @AuthenticationPrincipal UserDetails principal) {

        model.addAttribute("orders", orderService
                .findAllOrdersByCustomer(principal.getUsername()));

        return "orders";
    }

    @PreAuthorize("isOwner(#id)")
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

    @PreAuthorize("isAdmin()")
    @GetMapping("/change-status")
    public String changeStatus() {
        return "change-order-status";
    }

    @PreAuthorize("isAdmin()")
    @PostMapping("/change-status")
    public String changeStatusConfirm(@RequestParam Long orderId,
                                @RequestParam String status) {

        orderService.updateStatus(orderId, OrderStatusEnum.valueOf(status.toUpperCase()));

        return "redirect:/orders/" + orderId;
    }

    @PreAuthorize("isAdmin()")
    @GetMapping("/proceed/{id}")
    public String proceedOrder(@PathVariable Long id) {
        orderService.proceedOrder(id);
        return "redirect:/orders/" + id;
    }
}
