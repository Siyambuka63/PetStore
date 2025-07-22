package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Card;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.domain.user.User;
import za.ac.cput.factory.user.UserFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    private static User user;

    @Autowired
    private UserServiceImpl service;

    @BeforeAll
    static void setUp() {
        //ToDo make valid wishlist
        List<Product> wishlistItems = new ArrayList<Product>();

        //ToDo make valid reviews
        List<Review> reviews = new ArrayList<Review>();

        //ToDo make valid card
        Card card = null;

        //ToDo make valid address
        Address shippingAddress  = null;

        //ToDo make valid address
        Address billingAddress = null;

        //ToDo make valid contact
        Contact contact = null;

        user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    }

    @Test
    @Order(1)
    void create() {
        User createdUser = service.create(user);
        assertNotNull(createdUser);
        System.out.println(createdUser);
    }

    @Test
    @Order(2)
    void read() {
        User readUser = service.read(user.getUserID());
        assertNotNull(readUser);
        System.out.println(readUser);
    }

    @Test
    @Order(3)
    void update() {
        User updateUser = new User.Builder().copy(user).setFirstName("First Name").build();
        User updatedUser = service.update(updateUser);
        assertNotNull(updatedUser);
        System.out.println(updatedUser);
    }

    @Test
    @Order(5)
    void delete() {
        service.delete(user.getUserID());
        User deletedUser = service.read(user.getUserID());
        assertNull(deletedUser);
        System.out.println(deletedUser);
    }

    @Test
    @Order(4)
    void getAll() {
        List<User> users = service.getAll();
        assertNotNull(users);
        System.out.println(users);
    }
}