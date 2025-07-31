// Nkheso Mathebula - 230762883
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.product.Product;
import za.ac.cput.factory.OrderItemFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemControllerTest {

    private static OrderItem orderItem;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/orderitem";

    @BeforeAll
    static void setUp() {
        Order order = new Order();
        order.setId(1L);

        Product product = new Product();
        product.setId(101L);

        orderItem = OrderItemFactory.createOrderItem(order, product, 10.0f, 2);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<OrderItem> response = restTemplate.postForEntity(url, orderItem, OrderItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Created: " + response.getBody());
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + orderItem.getId().getOrderId() + "/" + orderItem.getId().getProductId();
        ResponseEntity<OrderItem> response = restTemplate.getForEntity(url, OrderItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void update() {
        OrderItem updated = new OrderItem.Builder().copy(orderItem).setQuantity(5).setTotalPrice(50.0f).build();
        String url = BASE_URL + "/update";
        HttpEntity<OrderItem> request = new HttpEntity<>(updated);
        ResponseEntity<OrderItem> response = restTemplate.exchange(url, HttpMethod.PUT, request, OrderItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + orderItem.getId().getOrderId() + "/" + orderItem.getId().getProductId();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + orderItem.getId().getOrderId() + "/" + orderItem.getId().getProductId();
        ResponseEntity<OrderItem> response = restTemplate.getForEntity(readUrl, OrderItem.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted: " + orderItem.getId());
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All Items: " + response.getBody());
    }
}
