package za.ac.cput.domain.review;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewId implements Serializable {
    private String email;
    private Long productId;

    protected ReviewId() {
    }

    public ReviewId(String email, Long productId) {
        this.email = email;
        this.productId = productId;
    }

    public String getEmail() {return email;}

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ReviewId{" +
                "email='" + email + '\'' +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReviewId reviewId = (ReviewId) o;
        return Objects.equals(email, reviewId.email) && Objects.equals(productId, reviewId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, productId);
    }
}