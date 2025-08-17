// Nkheso Mathebula - 230762883
package za.ac.cput.domain.orderItem;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    private float pricePerItem;
    private int quantity;
    private float totalPrice;

    protected OrderItem() {}

    private OrderItem(Builder builder) {
        this.pricePerItem = builder.pricePerItem;
        this.quantity = builder.quantity;
        this.totalPrice = builder.totalPrice;
        this.id = builder.id;
    }

    public OrderItemId getId() {
        return id;
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
                ", pricePerItem=" + pricePerItem +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private OrderItemId id;
        private float pricePerItem;
        private int quantity;
        private float totalPrice;

        public Builder setId(OrderItemId id) {
            this.id = id;
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
            this.id = orderItem.id;
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