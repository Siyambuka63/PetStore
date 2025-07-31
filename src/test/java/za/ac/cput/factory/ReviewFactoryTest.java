package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.product.Product;
import za.ac.cput.domain.user.User;
import za.ac.cput.factory.product.ProductFactory;
import za.ac.cput.factory.user.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

    private User validUser = UserFactory.createUser(1, "Name", "Middle", "Last", "password123");
    private Product validProduct = ProductFactory.createProduct( 1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99, 199.99, true, 23, 1.34, "Jock", "Adult", "Dry", "Dog", "Lamb");

    private Review reviewWithNullUser = ReviewFactory.createReview(null, validProduct, "Nice product", 4.0f);
    private Review reviewWithNullProduct = ReviewFactory.createReview(validUser, null, "Nice product", 4.0f);
    private Review reviewWithEmptyReviewText = ReviewFactory.createReview(validUser, validProduct, "", 4.0f);
    private Review reviewWithInvalidRating = ReviewFactory.createReview(validUser, validProduct, "Good", -1f);
    private Review validReview = ReviewFactory.createReview(validUser, validProduct, "Excellent!", 4.7f);

    @Test
    void testCreateReviewWithNullUser() {
        assertNull(reviewWithNullUser);
        System.out.println(reviewWithNullUser);
    }

    @Test
    void testCreateReviewWithNullProduct() {
        assertNull(reviewWithNullProduct);
        System.out.println( reviewWithNullProduct);
    }

    @Test
    void testCreateReviewWithEmptyReviewText() {
        assertNull(reviewWithEmptyReviewText);
        System.out.println( reviewWithEmptyReviewText);
    }

    @Test
    void testCreateReviewWithInvalidRating() {
        assertNull(reviewWithInvalidRating);
        System.out.println(reviewWithInvalidRating);
    }

    @Test
    void testCreateValidReview() {
        assertNotNull(validReview);
        System.out.println(validReview);
    }

}
