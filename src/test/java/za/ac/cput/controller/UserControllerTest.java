package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.ContactFactory;
import za.ac.cput.factory.user.UserFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    private static User user;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/petstore/user";

    @BeforeAll
    static void setUp() {
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();
        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");

        user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        User createdUser = restTemplate.postForObject(url, user, User.class);
        assertNotNull(createdUser);
        System.out.println(createdUser);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + user.getId();
        User readUser = restTemplate.getForObject(url, User.class);
        assertNotNull(readUser);
        System.out.println(readUser);
    }

    @Test
    @Order(3)
    void update() {
        User updatedUser = new User.Builder().copy(user).setFirstName("First Name").build();
        String url = BASE_URL + "/update";
        User update = restTemplate.postForObject(url, updatedUser, User.class);
        assertNotNull(update);
        System.out.println(update);
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + user.getId();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + user.getId();
        User deletedUser = restTemplate.getForObject(readUrl, User.class);
        assertNull(deletedUser);
        System.out.println(deletedUser);
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        List<User> users = restTemplate.getForObject(url, List.class);
        assertNotNull(users);
        System.out.println(users);
    }
}