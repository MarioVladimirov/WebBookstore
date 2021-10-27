package bg.softuni.webbookstore.model.view;

import bg.softuni.webbookstore.model.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderViewModel {

    private Long id;
    private LocalDate date;
    private BigDecimal price;
    private StatusEnum status;
    private String customerFullName;


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
}
