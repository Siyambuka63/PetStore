package za.ac.cput.domain.user.wishlist;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class WishlistId implements Serializable {
    private String contactId;
    private long productId;

    public WishlistId(String email, Long productId) {
        this.contactId = email;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "WishlistId{" +
                "contactId=" + contactId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WishlistId that = (WishlistId) o;
        return Objects.equals(contactId, that.contactId) && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, productId);
    }
}
