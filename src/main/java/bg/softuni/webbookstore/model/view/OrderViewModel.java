package bg.softuni.webbookstore.model.view;

import bg.softuni.webbookstore.model.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OrderViewModel {

    private Long id;
    private LocalDate date;
    private BigDecimal price;
    private StatusEnum status;
    private String customerFullName;
    private String customerAddress;
    private Set<OrderItemViewModel> orderedBooks;


    public OrderViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderViewModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public OrderViewModel setStatus(StatusEnum status) {
        this.status = status;
        return this;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public OrderViewModel setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
        return this;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public OrderViewModel setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public Set<OrderItemViewModel> getOrderedBooks() {
        return orderedBooks;
    }

    public OrderViewModel setOrderedBooks(Set<OrderItemViewModel> orderedBooks) {
        this.orderedBooks = orderedBooks;
        return this;
    }
}
