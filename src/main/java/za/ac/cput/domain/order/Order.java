package za.ac.cput.domain.order;

import java.time.LocalDate;
import java.util.List;
/*
     Order class
     Author: Sinovuyo Mathungana (230143725)
*/
public class Order {
    private long orderID;
    private long userID;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private double totalPrice;
    private List<OrderItem> items;
    private Status status;

    private Order(){}

    private Order(Builder builder){
        this.orderID = builder.orderID;
        this.userID = builder.userID;
        this.orderDate = builder.orderDate;
        this.deliveryDate = builder.deliveryDate;
        this.totalPrice = builder.totalPrice;
        this.items = builder.items;
        this.status = builder.status;
    }

    public long getOrderID() {
        return orderID;
    }

    public long getUserID() {
        return userID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItem> getItems() {
        return items;
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
                "orderID=" + orderID +
                ", userID=" + userID +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                ", status=" + status +
                '}';
    }

    public static class Builder{
        private long orderID;
        private long userID;
        private LocalDate orderDate;
        private LocalDate deliveryDate;
        private double totalPrice;
        private List<OrderItem> items;
        private Status status;

        public Builder setOrderID(long orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setUserID(long userID) {
            this.userID = userID;
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
        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setItems(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder copy(Order order){
            this.orderID = order.orderID;
            this.userID = order.userID;
            this.orderDate = order.orderDate;
            this.deliveryDate = order.deliveryDate;
            this.totalPrice = order.totalPrice;
            this.items = order.items;
            this.status = order.status;
            return this;
        }
        public Order build(){return new Order(this);}
    }
}
