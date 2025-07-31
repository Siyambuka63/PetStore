package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.OrderItemId;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.product.Product;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.factory.order.OrderFactory;
import za.ac.cput.factory.product.ProductFactory;

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
        order = OrderFactory.createOrder(1L);
        product = ProductFactory.createProduct(1, "Multistage", "Nibbles", "placeholder.jpg", 4f, 249.99, 199.99, true, 23, 1.34, "Jock", "Adult", "Dry", "Dog", "Lamb");

        orderItem = OrderItemFactory.createOrderItem(order, product, 100f, 2);
        orderItemId = new OrderItemId(order.getId(), product.getId());}

    @Test
    @Order(1)
    void create() {
        OrderItem created = service.create(orderItem);
        assertNotNull(created);
        System.out.println( created);
    }

    @Test
    @Order(2)
    void read() {
        OrderItem read = service.read(new OrderItemId(order.getId(), product.getId()));
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        OrderItem updatedOrderItem = new OrderItem.Builder().copy(orderItem).setQuantity(3).setTotalPrice(300f).build();
        OrderItem updated = service.update(updatedOrderItem);
        assertNotNull(updated);
        assertEquals(3, updated.getQuantity());
        System.out.println( updated);
    }

    @Test
    @Order(4)
    void getAll() {
        List<OrderItem> all = service.getAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);
        System.out.println("All OrderItems: " + all);
    }

    @Test
    @Order(5)
    void delete() {
        service.delete(new OrderItemId(order.getId(), product.getId()));
        OrderItem deleted = service.read(new OrderItemId(order.getId(), product.getId()));
        assertNull(deleted);
        System.out.println( deleted);
    }
}
