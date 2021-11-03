package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    List<OrderViewModel> findAllOrdersByCustomer(String username);

    List<OrderViewModel> findLastFiveOrdersByCustomer(String username);

    Long createOrder(String username);

    OrderViewModel findById(Long id);
}
