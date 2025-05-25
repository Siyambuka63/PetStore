/*
   Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    private int reviewId;
    private int userId;
    private int productId;
    private String review;
    private Float rating;

    protected Review() {}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.userId = builder.userId;
        this.productId = builder.productId;
        this.review = builder.review;
        this.rating = builder.rating;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getReview() {
        return review;
    }

    public Float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {
        private int reviewId;
        private int userId;
        private int productId;
        private String review;
        private Float rating;

        public Builder setReviewId(int reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder setReview(String review) {
            this.review = review;
            return this;
        }

        public Builder setRating(Float rating) {
            this.rating = rating;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.userId = review.userId;
            this.productId = review.productId;
            this.review = review.review;
            this.rating = review.rating;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}