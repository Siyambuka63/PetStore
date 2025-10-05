//NKHESO
package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.review.ReviewId;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.UserFactory;
import za.ac.cput.factory.ReviewFactory;
import za.ac.cput.service.impl.ReviewServiceImpl;

import java.util.ArrayList;
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
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);

        user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");

        List<String> categories = new ArrayList<>();
        List<User> wishlistedUser = new ArrayList<>();
        product = ProductFactory.createProduct(1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99f, 15.00f, 23, 1.34f, "Jock", "Adult", "Dry", "Dog", "Lamb", categories);


        review = ReviewFactory.createReview(user, product, "Great product!", 4.7f);
        assertNotNull(review, "Review should not be null");
    }

    @Test
    @Order(1)
    void create() {
        Review created = service.create(review);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        ReviewId id = new ReviewId(user.getEmail(), product.getId());
        Review read = service.read(id);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Review updatedReview = new Review.Builder().copy(review).setReview("Updated review text").setRating(4.9f).build();
        Review updated = service.update(updatedReview);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        ReviewId id = new ReviewId(user.getEmail(), product.getId());
        service.delete(id);
        Review deleted = service.read(id);
        assertNull(deleted);
        System.out.println(deleted);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Review> reviews = service.getAll();
        assertNotNull(reviews);
        System.out.println(reviews);
    }
}