package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.view.OrderViewModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderViewModel> findAllOrdersByCustomer(String username);

    List<OrderViewModel> findLastFiveOrdersByCustomer(String username);

    Long createOrder(String username);

    Optional<OrderViewModel> findById(Long id);
}
