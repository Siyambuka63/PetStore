/*
   Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.factory;

import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.ReviewId;
import za.ac.cput.domain.user.User;
import za.ac.cput.util.Helper;

public class ReviewFactory {
    public static Review createReview(ReviewId reviewId, String review, float rating) {
        if (reviewId == null) return null;
        if (!Helper.isValidRating(rating)) return null;

        return new Review.Builder()
                .setReviewId(reviewId)
                .setReview(review)
                .setRating(rating)
                .build();
    }

    public static Review createReview(long userId, long productId, String review, float rating) {
        ReviewId reviewId = new ReviewId(userId, productId);

        return createReview(reviewId, review, rating);
    }

    public static Review createReview(User user, Product product, String review, float rating) {
        if (user == null) return null;
        if (product == null) return null;

        return createReview(user.getId(), product.getId(), review, rating);
    }
}
