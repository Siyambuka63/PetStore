package za.ac.cput.factory.user;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserFactoryTest {

    private final List<Product> wishlistItems = new ArrayList<Product>();

    private final List<Review> reviews = new ArrayList<Review>();

    private final Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
    private final Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
    private final Address billingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
    private final Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");

    private final User userWithInvalidName = UserFactory.createUser(1, "", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    private final User userWithInvalidLastName = UserFactory.createUser(1, "Name", "Middle", "", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    private final User userWithInvalidPassword = UserFactory.createUser(1, "Name", "Middle", "Last", "", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);
    private final User userWithInvalidContact = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, null);
    private final User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

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