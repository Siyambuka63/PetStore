/*
   Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import za.ac.cput.domain.product.Product;

import java.util.List;

@Entity
public class Wishlist {

    @Id
    private int wishlistId;
    private int userId;
    private List<Product> items;

    protected Wishlist() {}

    private Wishlist(Builder builder) {
        this.wishlistId = builder.wishlistId;
        this.userId = builder.userId;
        this.items = builder.item;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public List<Product> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistId=" + wishlistId +
                ", userId=" + userId +
                ", items=" + items +
                '}';
    }

    public static class Builder {
        private int wishlistId;
        private int userId;
        private List<Product> item;

        public Builder setWishlistId(int wishlistId) {
            this.wishlistId = wishlistId;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setItem(List<Product> items) {
            this.item = items;
            return this;
        }

        public Builder copy(Wishlist wishlist) {
            this.wishlistId = wishlist.wishlistId;
            this.userId = wishlist.userId;
            this.item = wishlist.items;
            return this;
        }

        public Wishlist build() {
            return new Wishlist(this);
        }


    }
}
