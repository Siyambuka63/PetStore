package za.ac.cput.domain;

import java.util.List;

public class Wishlist {

    private Integer wishlistId;
    private Integer userId;
    private List<Product> item;

    private Wishlist() {}

    private Wishlist(Builder builder) {
        this.wishlistId = builder.wishlistId;
        this.userId = builder.userId;
        this.item = builder.item;
    }


    public Integer getWishlistId() {
        return wishlistId;
    }

    public Integer getUserId() {
        return userId;
    }

    public List<Product> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistId=" + wishlistId +
                ", userId=" + userId +
                ", item=" + item +
                '}';
    }

    public static class Builder {
        private Integer wishlistId;
        private Integer userId;
        private List<Product> item;


        public Builder wishlistId(Integer wishlistId) {
            this.wishlistId = wishlistId;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder item(List<Product> item) {
            this.item = item;
            return this;
        }


        public Wishlist build() {
            return new Wishlist(this);
        }
    }

}

