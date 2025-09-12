package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
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
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();
        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);

        user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");
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
        User readUser = service.read(user.getEmail());
        assertNotNull(readUser);
        System.out.println(readUser.getEmail());
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
        service.delete(user.getEmail());
        User deletedUser = service.read(user.getEmail());
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