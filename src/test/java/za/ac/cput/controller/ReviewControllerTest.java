//NKHESO
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.User;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.user.UserFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {

    private static Review review;
    private static Product product;
    private static User user;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/petstore/review";

    @BeforeAll
    static void setUp() {
        product = ProductFactory.createProduct(1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99, 199.99, true, 23, 1.34, "Jock", "Adult", "Dry", "Dog", "Lamb");
        user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123");
        review = ReviewFactory.createReview(user, product, "Great product!", 4.7f);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        Review createdReview = restTemplate.postForObject(url, review, Review.class);
        assertNotNull(createdReview);
        System.out.println(createdReview);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + review.getId().getUserId() + "/" + review.getId().getProductId();
        Review readReview = restTemplate.getForObject(url, Review.class);
        assertNotNull(readReview);
        System.out.println(readReview);
    }

    @Test
    @Order(3)
    void update() {
        Review updated = new Review.Builder().copy(review).setComment("Updated comment").setRating(4).build();
        String url = BASE_URL + "/update";
        Review result = restTemplate.postForObject(url, updated, Review.class);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        List<Review> reviews = restTemplate.getForObject(url, List.class);
        assertNotNull(reviews);
        System.out.println(reviews);
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + review.getId().getUserId() + "/" + review.getId().getProductId();
        restTemplate.delete(url);
        System.out.println(review.getId());
    }
}
