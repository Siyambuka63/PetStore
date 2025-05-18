/*
   Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

public class ReviewFactory {
    public static Review createReview(int reviewId, int userId, int productId, String review, Float rating) {
        if (Helper.isNullOrEmpty(review)) return null;
        if (!Helper.isValidRating(rating)) return null;

        return new Review.Builder()
                .setReviewId(reviewId)
                .setUserId(userId)
                .setProductId(productId)
                .setReview(review)
                .setRating(rating)
                .build();
    }
}
