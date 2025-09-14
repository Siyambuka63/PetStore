package za.ac.cput.domain.user.wishlist;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Wishlist {
    @EmbeddedId
    private WishlistId id;

    private Wishlist(WishlistId id) { this.id = id; }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                '}';
    }

    public static class Builder {
        private WishlistId id;

        public Builder setId(WishlistId id) {
            this.id = id;
            return this;
        }

        public Builder copy(Wishlist wishlist) {
            this.id = wishlist.getId();
            return this;
        }

        public Wishlist build() {return new Wishlist(id);}
    }
}
