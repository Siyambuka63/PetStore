package za.ac.cput.domain.review;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReviewId reviewId = (ReviewId) o;
        return Objects.equals(userId, reviewId.userId) && Objects.equals(productId, reviewId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}