package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.ContactFactory;
import za.ac.cput.factory.user.UserFactory;
import za.ac.cput.service.impl.OrderServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl service;

    private static Order order;

    @BeforeAll
    public static void setUp() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        order = OrderFactory.createOrder(1, user, orderDate, deliveryDate, 8000, status);
    }

    @Test
    void a_create() {
        System.out.println("Order to be created: " + order);
        Order created = service.create(order);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Order read = service.read(order.getId());
        assertNotNull(read);
        System.out.println("read: " + read);
    }

    @Test
    void c_update() {
        assertNotNull(order);
        Order newOrder = new Order.Builder().copy(order).setPrice(30000).build();
        Order updatedOrder = service.update(newOrder);
        assertNotNull(updatedOrder);
        System.out.println(updatedOrder);
    }

    @Test
    void d_delete() {
        service.delete(order.getId());

        Order deleted = service.read(order.getId());
        assertNull(deleted);
        System.out.println(deleted);
    }

    @Test
    void e_getAll() {
        System.out.println(service.getAll());
    }

    @Test
    void f_findByOrderDate() {
        LocalDate orderDate = LocalDate.now();

        assertNotNull(service.findByOrderDate(orderDate));
        System.out.println(service.findByOrderDate(orderDate));
    }

    @Test
    void g_findByDeliveryDate() {
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");

        assertNotNull(service.findByDeliveryDate(deliveryDate));
        System.out.println(service.findByDeliveryDate(deliveryDate));
    }

    @Test
    void h_findByTotalPrice() {
        assertNotNull(service.findByPrice(order.getPrice()));
        System.out.println(service.findByPrice(order.getPrice()));
    }

    @Test
    void i_findByStatus() {
        Status status = Status.Busy;

        assertNotNull(service.findByStatus(status));
        System.out.println(service.findByStatus(status));
    }

    @Test
    void j_createCartAndGetActiveCart() {
        // Create a cart using factory method
        Order cart = OrderFactory.createCart(order.getUser());
        Order createdCart = service.create(cart);

        assertNotNull(createdCart);
        assertEquals(Status.Cart, createdCart.getStatus());
        System.out.println(createdCart);

        // Fetch the active cart for the user
        Order activeCart = service.getActiveCart(order.getUser());
        assertNotNull(activeCart);
        assertEquals(Status.Cart, activeCart.getStatus());
        System.out.println(activeCart);
    }

    @Test
    void k_checkoutCart() {
        // Ensure there is an active cart
        Order cart = service.getActiveCart(order.getUser());
        assertNotNull(cart);

        // Checkout the cart
        Order checkedOutCart = service.checkoutCart(order.getUser());
        assertNotNull(checkedOutCart);
        assertEquals(Status.Pending, checkedOutCart.getStatus());
        System.out.println(checkedOutCart);
    }
}