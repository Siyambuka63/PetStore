/* Review.java
   Review class
   Author: Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.domain;

public class Review {

    private int reviewId;
    private int userId;
    private int productId;
    private String review;
    private Float rating;

    private Review() {}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.userId = builder.userId;
        this.productId = builder.productId;
        this.review = builder.review;
        this.rating = builder.rating;
    }


    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getProductId() {
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


        public Builder reviewId(Integer reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder review(String review) {
            this.review = review;
            return this;
        }

        public Builder rating(Float rating) {
            this.rating = rating;
            return this;
        }


        public Review build() {
            return new Review(this);
        }
    }

}
