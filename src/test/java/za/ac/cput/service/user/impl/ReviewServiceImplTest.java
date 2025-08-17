//NKHESO
package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.ReviewId;
import za.ac.cput.domain.product.Product;
import za.ac.cput.domain.user.User;
import za.ac.cput.factory.product.ProductFactory;
import za.ac.cput.factory.user.UserFactory;
import za.ac.cput.factory.ReviewFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceImplTest {

    private static Review review;
    private static User user;
    private static Product product;

    @Autowired
    private ReviewServiceImpl service;

    @BeforeAll
    static void setUp() {

        user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123");
        product = ProductFactory.createProduct(1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99, 199.99, true, 23, 1.34, "Jock", "Adult", "Dry", "Dog", "Lamb");


        review = ReviewFactory.createReview(user, product, "Great product!", 4.7f);
        assertNotNull(review, "Review should not be null");
    }

    @Test
    @Order(1)
    void create() {
        Review created = service.save(review);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        ReviewId id = new ReviewId(user.getId(), product.getId());
        Review read = service.read(id);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Review updatedReview = new Review.Builder().copy(review).setReview("Updated review text").setRating(4.9f).build();
        Review updated = service.save(updatedReview);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        ReviewId id = new ReviewId(user.getId(), product.getId());
        service.delete(id);
        Review deleted = service.read(id);
        assertNull(deleted);
        System.out.println(deleted);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Review> reviews = service.findAll();
        assertNotNull(reviews);
        System.out.println(reviews);
    }

    @Test
    @Order(6)
    void findByUserId() {
        List<Review> reviewsByUser = service.findByUserId(user.getId());
        assertNotNull(reviewsByUser);
        System.out.println(reviewsByUser);
    }

    @Test
    @Order(7)
    void findByProductId() {
        List<Review> reviewsByProduct = service.findByProductId(product.getId());
        assertNotNull(reviewsByProduct);
        System.out.println( reviewsByProduct);
    }
}
