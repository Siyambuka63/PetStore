// Nkheso Mathebula - 230762883
package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.product.Product;

@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    private Product product;

    private float pricePerItem;
    private int quantity;
    private float totalPrice;

    protected OrderItem() {}

    private OrderItem(Builder builder) {
        this.order = builder.order;
        this.product = builder.product;
        this.pricePerItem = builder.pricePerItem;
        this.quantity = builder.quantity;
        this.totalPrice = builder.totalPrice;
        Long orderId = (order != null) ? order.getId() : null;
        Long productId = (product != null) ? product.getId() : null;
        this.id = new OrderItemId(orderId, productId);
    }


    public OrderItemId getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public float getPricePerItem() {
        return pricePerItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", pricePerItem=" + pricePerItem +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private Order order;
        private Product product;
        private float pricePerItem;
        private int quantity;
        private float totalPrice;

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setPricePerItem(float pricePerItem) {
            this.pricePerItem = pricePerItem;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.order = orderItem.order;
            this.product = orderItem.product;
            this.pricePerItem = orderItem.pricePerItem;
            this.quantity = orderItem.quantity;
            this.totalPrice = orderItem.totalPrice;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
