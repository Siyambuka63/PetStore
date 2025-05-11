package za.ac.cput.factory;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

public class ReviewFactory {
    public static Review createReview(int reviewId, int userId, int productId, String review, Float rating) {
        if (Helper.isNullOrEmpty(review)) return null;
        if (!Helper.isValidRating(rating)) return null;
        if (!Helper.isValidId(reviewId) || !Helper.isValidId(userId) || !Helper.isValidId(productId)) return null;

        return new Review.Builder()
                .reviewId(reviewId)
                .userId(userId)
                .productId(productId)
                .review(review)
                .rating(rating)
                .build();
    }
}


