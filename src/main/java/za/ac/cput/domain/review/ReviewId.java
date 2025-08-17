package za.ac.cput.domain.review;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReviewId implements Serializable {
    private Long userId;
    private Long productId;

    protected ReviewId() {
    }

    public ReviewId(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ReviewId{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}