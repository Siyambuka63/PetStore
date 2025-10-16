//NKHESO
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.UserFactory;

import java.util.ArrayList;
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

    private final String BASE_URL = "http://localhost:8080/petstore/reviews";

    @BeforeAll
    static void setUp() {
        List<String> categories = new ArrayList<>();

        product = ProductFactory.createProduct(1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99f, 19L, 23, 1.34f, "Jock", "Adult", "Dry", "Dog", "Lamb", categories);

        List<Product> wishlistItems = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");

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
        String url = BASE_URL + "/read/" + review.getId().getEmail() + "/" + review.getId().getProductId();
        Review readReview = restTemplate.getForObject(url, Review.class);
        assertNotNull(readReview);
        System.out.println(readReview);
    }

    @Test
    @Order(3)
    void update() {
        Review updated = new Review.Builder().copy(review).setReview("Updated comment").setRating(4).build();
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
        String url = BASE_URL + "/delete/" + review.getId().getEmail() + "/" + review.getId().getProductId();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + review.getId().getEmail() + "/" + review.getId().getProductId();
        Review deletedReview = restTemplate.getForObject(readUrl, Review.class);
        assertNull(deletedReview);
        System.out.println(deletedReview);
    }
}