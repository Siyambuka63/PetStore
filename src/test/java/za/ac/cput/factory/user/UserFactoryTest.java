package za.ac.cput.factory.user;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Card;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.domain.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    private List<Product> wishlistItems = new ArrayList<Product>();

    private List<Review> reviews = new ArrayList<Review>();

    //ToDo make valid card
    private Card card = null;

    //ToDo make valid address
    private Address shippingAddress  = null;

    //ToDo make valid address
    private Address billingAddress = null;

    //ToDo make valid contact
    private Contact contact = null;

    private User userWithInvalidName = UserFactory.createUser(1, "", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    private User userWithInvalidLastName = UserFactory.createUser(1, "Name", "Middle", "", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    private User userWithInvalidPassword = UserFactory.createUser(1, "Name", "Middle", "Last", "", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    private User userWithInvalidContact = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, null);
    private User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

    @Test
    void testCreateUserWithInvalidName() {
        assertNull(userWithInvalidName);
        System.out.println(userWithInvalidName);
    }

    @Test
    void testCreateUserWithInvalidLastName() {
        assertNull(userWithInvalidLastName);
        System.out.println(userWithInvalidLastName);
    }

    @Test
    void testCreateUserWithInvalidPassword() {
        assertNull(userWithInvalidPassword);
        System.out.println(userWithInvalidPassword);
    }

    @Test
    void testCreateUserWithInvalidContact() {
        assertNull(userWithInvalidContact);
        System.out.println(userWithInvalidContact);
    }

    @Test
    void testCreateUser() {
        assertNotNull(user);
        System.out.println(user);
    }
}