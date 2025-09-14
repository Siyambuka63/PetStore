package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.UserFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderFactoryTest {

    @Test
    void a_createOrder() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        List<Review> reviews = new ArrayList<Review>();

        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");
        Order order = OrderFactory.createOrder(1,user,orderDate,deliveryDate,8000,status);
        assertNotNull(order);
        System.out.println(order);
    }

    @Test
    void b_createOrderWithoutOrderDate() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");

        Order orderWithInvalidOrderDate = OrderFactory.createOrder(1,user,null,deliveryDate,8000,status);
        assertNull(orderWithInvalidOrderDate);
        System.out.println(orderWithInvalidOrderDate);
    }
    @Test
    void c_createOrderWithInvalidDeliveryDate() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;
        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");
        Order orderWithInvalidDeliveryDate = OrderFactory.createOrder(1,user,orderDate,null,8000,status);
        assertNull(orderWithInvalidDeliveryDate);
        System.out.println(orderWithInvalidDeliveryDate);
    }

    @Test
    void e_createOrderWithInvalidStatus() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;
        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        List<Review> reviews = new ArrayList<Review>();

        User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");
        Order orderWithInvalidStatus = OrderFactory.createOrder(1,user,orderDate,deliveryDate,8000,null);
        assertNull(orderWithInvalidStatus);
        System.out.println(orderWithInvalidStatus);
    }

    @Test
    void f_createCart() {
        List<Product> wishlistItems = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);

        User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");

        Order cart = OrderFactory.createCart(user);

        assertNotNull(cart);
        assertEquals(Status.Cart, cart.getStatus());
        assertEquals(0f, cart.getPrice());
        assertNull(cart.getDeliveryDate()); // delivery not set yet
        System.out.println(cart);
    }

    @Test
    void g_createCartWithNullUser() {
        Order cart = OrderFactory.createCart(null);
        assertNull(cart);
        System.out.println(cart);
    }
}