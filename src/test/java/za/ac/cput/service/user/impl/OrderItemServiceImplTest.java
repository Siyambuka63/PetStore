//NKHESO
package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.orderItem.OrderItemId;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.UserFactory;
import za.ac.cput.service.impl.OrderItemServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemServiceImplTest {

    private static OrderItem orderItem;
    private static Order order;
    private static Product product;

    @Autowired
    private OrderItemServiceImpl service;

    @BeforeAll
    static void setUp() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        List<Product> wishlistItems = new ArrayList<Product>();
        List<Review> reviews = new ArrayList<Review>();

        Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
        Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);

        User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");
        order = OrderFactory.createOrder(1,user,orderDate,deliveryDate,8000,status);

        List<String> categories = new ArrayList<>();
        List<User> wishlistedUser = new ArrayList<>();
        product = ProductFactory.createProduct(1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99f, 15.00f, 23, 1.34f, "Jock", "Adult", "Dry", "Dog", "Lamb", categories);

        orderItem = OrderItemFactory.createOrderItem(order, product, 100f, 2);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        OrderItem created = service.create(orderItem);
        assertNotNull(created);
        System.out.println( created);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        OrderItem read = service.read(new OrderItemId(order.getId(), product.getId()));
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() {
        OrderItem updatedOrderItem = new OrderItem.Builder().copy(orderItem).setQuantity(3).setTotalPrice(300f).build();
        OrderItem updated = service.update(updatedOrderItem);
        assertNotNull(updated);
        assertEquals(3, updated.getQuantity());
        System.out.println( updated);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() {
        List<OrderItem> all = service.getAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);
        System.out.println("All OrderItems: " + all);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void delete() {
        service.delete(new OrderItemId(order.getId(), product.getId()));
        OrderItem deleted = service.read(new OrderItemId(order.getId(), product.getId()));
        assertNull(deleted);
        System.out.println( deleted);
    }
}
