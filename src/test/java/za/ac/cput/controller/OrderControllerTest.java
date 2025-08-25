package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderControllerTest {

    private static Order order;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/petstore/order";

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

        order = OrderFactory.createOrder(1,user,orderDate,deliveryDate,8000,status);
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Order> postResponse = restTemplate.postForEntity(url,order, Order.class);
        assertNotNull(postResponse);
        Order createdOrder = postResponse.getBody();
        assertEquals(order.getId(),createdOrder.getId());
        System.out.println("Created " + createdOrder);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + order.getId();
        ResponseEntity<Order> readOrder =  this.restTemplate.getForEntity(url, Order.class);
        assertNotNull(readOrder);
        System.out.println(readOrder);
    }

    @Test
    void c_update() {
        Order updateOrder = new Order.Builder().copy(order).setPrice(7000).build();
        String url = BASE_URL + "/update";
        ResponseEntity<Order> updated = restTemplate.postForEntity(url, updateOrder, Order.class);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void e_delete() {
        String url = BASE_URL + "/delete/" + order.getId();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + order.getId();
        ResponseEntity<Order> reponse = restTemplate.getForEntity(readUrl, Order.class);
        assertNull(reponse.getBody());
        System.out.println(reponse);
    }

    @Test
    void d_getAll() {
        String url = BASE_URL + "/getAll";
        List<Order> orders = restTemplate.getForObject(url, List.class);
        assertNotNull(orders);
        System.out.println(orders);
    }
}