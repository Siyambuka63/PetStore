// Nkheso
package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderItemFactory;
import za.ac.cput.factory.ProductFactory;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.ContactFactory;
import za.ac.cput.factory.user.UserFactory;

import java.time.LocalDate;
import java.util.ArrayList;
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
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        Status status = Status.Busy;

        Card card =  CardFactory.createCard(987554456,"Ozow", "Visa_4456","4456","Visa");
        Address shippingAddress  = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Address billingAddress = AddressFactory.createAddress(3453,"apartment","Cape Town","237 Nkani Street","7894","7570", Type.Both);
        Contact contact = ContactFactory.createContact(1, "0987654321", "test@gmail.com");
        List<Product> wishlistItems = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        User user = UserFactory.createUser(1, "Name", "Middle", "Last", "password123", wishlistItems, reviews, card, shippingAddress, billingAddress, contact);

        Order order = OrderFactory.createOrder(1,user,orderDate,deliveryDate,8000,status);

        List<String> categories = new ArrayList<>();
        List<User> wishlistedUser = new ArrayList<>();
        Product product = ProductFactory.createProduct(4, "Montego Classic", "Dry Dog Food","placeholder.jpg", 4f,189.00f, 189.00f, false, 30, 5.0f, "Montego", "Puppy", "Dry", "Dog", "Beef", categories, wishlistedUser);

        orderItem = OrderItemFactory.createOrderItem(order, product, 10.0f, 2);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<OrderItem> response = restTemplate.postForEntity(url, orderItem, OrderItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Created: " + response.getBody());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        String url = BASE_URL + "/read/" + orderItem.getId().getOrderId() + "/" + orderItem.getId().getProductId();
        ResponseEntity<OrderItem> response = restTemplate.getForEntity(url, OrderItem.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
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
    @org.junit.jupiter.api.Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + orderItem.getId().getOrderId() + "/" + orderItem.getId().getProductId();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + orderItem.getId().getOrderId() + "/" + orderItem.getId().getProductId();
        ResponseEntity<OrderItem> response = restTemplate.getForEntity(readUrl, OrderItem.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted: " + orderItem.getId());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("All Items: " + response.getBody());
    }
}
