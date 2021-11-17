package bg.softuni.webbookstore.model.entity;

import bg.softuni.webbookstore.model.entity.enums.OrderStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    @ManyToOne
    private UserEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    Set<OrderItemEntity> orderedBooks;

    public OrderEntity() {
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderEntity setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public OrderEntity setStatus(OrderStatusEnum status) {
        this.status = status;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrderEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public Set<OrderItemEntity> getOrderedBooks() {
        return orderedBooks;
    }

    public OrderEntity setOrderedBooks(Set<OrderItemEntity> orderedBooks) {
        this.orderedBooks = orderedBooks;
        return this;
    }
}
