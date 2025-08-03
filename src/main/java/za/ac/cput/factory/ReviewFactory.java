/*
   Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.User;
import za.ac.cput.util.Helper;

public class ReviewFactory {

    public static Review createReview(User user, Product product, String review, float rating) {
        if (user == null || product == null || Helper.isNullOrEmpty(review) || !Helper.isValidRating(rating)) {
            return null;
        }

        return new Review.Builder()
                .setUser(user)
                .setProduct(product)
                .setReview(review)
                .setRating(rating)
                .build();
    }
}
