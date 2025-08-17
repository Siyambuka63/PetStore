package za.ac.cput.domain.review;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Review {
    @EmbeddedId
    private ReviewId id;
    private String review;
    private float rating;

    protected Review() {
    }

    private Review(Builder builder) {
        this.id = builder.id;
        this.review = builder.review;
        this.rating = builder.rating;
    }

    public ReviewId getId() {
        return id;
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
                "id=" + id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {
        private ReviewId id;
        private String review;
        private float rating;

        public Builder setReviewId(ReviewId id) {
            this.id = id;
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
            this.id = review.id;
            this.review = review.review;
            this.rating = review.rating;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}