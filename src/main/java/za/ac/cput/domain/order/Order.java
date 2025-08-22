package za.ac.cput.domain.order;

import jakarta.persistence.*;
import za.ac.cput.domain.user.User;

import java.time.LocalDate;

/*
     Order class
     Author: Sinovuyo Mathungana (230143725)
*/
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private float price;
    @Enumerated(EnumType.STRING)
    private Status status;

    protected Order() {}

    private Order(Builder builder) {
        this.id = builder.orderID;
        this.user = builder.user;
        this.orderDate = builder.orderDate;
        this.deliveryDate = builder.deliveryDate;
        this.price = builder.price;
        this.status = builder.status;
    }

    public long getId() {
        return id;
    }

    public User getUser() {return user;}

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        public User user;
        private long orderID;
        private LocalDate orderDate;
        private LocalDate deliveryDate;
        private float price;
        private Status status;

        public Builder setOrderID(long orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setDeliveryDate(LocalDate deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder copy(Order order) {
            this.orderID = order.id;
            this.user = order.user;
            this.orderDate = order.orderDate;
            this.deliveryDate = order.deliveryDate;
            this.price = order.price;
            this.status = order.status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}