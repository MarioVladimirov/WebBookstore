package bg.softuni.webbookstore.model.entity;

import bg.softuni.webbookstore.model.entity.enums.StatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @ManyToOne
    private UserEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    Set<OrderItemEntity> orderedBooks;

    public OrderEntity() {
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public OrderEntity setStatus(StatusEnum status) {
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
