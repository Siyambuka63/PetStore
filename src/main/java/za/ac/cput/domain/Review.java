package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.domain.product.Product;
import za.ac.cput.domain.user.User;

@Entity
public class Review {

    @EmbeddedId
    private ReviewId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    private String review;
    private float rating;

    protected Review() {}

    private Review(Builder builder) {
        this.user = builder.user;
        this.product = builder.product;
        this.id = new ReviewId(user.getId(), product.getId());
        this.review = builder.review;
        this.rating = builder.rating;
    }

    public ReviewId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public String getReview() {
        return review;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                " user=" + user +
                ", product=" + product +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {
        private User user;
        private Product product;
        private String review;
        private float rating;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setReview(String review) {
            this.review = review;
            return this;
        }

        public Builder setRating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder copy(Review review) {
            this.user = review.user;
            this.product = review.product;
            this.review = review.review;
            this.rating = review.rating;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
