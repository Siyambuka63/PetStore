package za.ac.cput.domain.orderItem;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;

    protected OrderItemId() {
    }

    public OrderItemId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }


    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "OrderItemId{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}