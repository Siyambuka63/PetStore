package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.user.User;
import za.ac.cput.factory.OrderFactory;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartControllerTest {
    private static Order cart;
    private static User user;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/petstore/cart";


    @BeforeAll
    static void setUp() {
        user = new User.Builder().setUserID(1L).build();
        cart = (Order) OrderFactory.createCart(user);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create"+user.getId();
        ResponseEntity<Order> response = restTemplate.postForEntity(url,null,Order.class);
        assertNotNull(response.getBody());
        assertEquals(Status.Cart, response.getBody().getClass());
        System.out.println("Created Cart:" + response.getBody());
    }

    @Test
    void read() {
        String url = BASE_URL + "/read" + cart.getId();
        ResponseEntity<Order> response = restTemplate.getForEntity(url,Order.class);
        assertNotNull(response.getBody());
        System.out.println("Read Cart:" + response.getBody());
    }

    @Test
    void update() {
        Order updatedCart = new Order.Builder().copy(cart).setPrice(299.99f).build();
        String url = BASE_URL + "/update";
        ResponseEntity<Order> response = restTemplate.postForEntity(url,updatedCart,Order.class);
        assertNotNull(response.getBody());
        assertEquals(299.99f, response.getBody().getPrice());
        System.out.println("Updated Cart:" + response.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete" + cart.getId();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read" + cart.getId();
        ResponseEntity<Order> response = restTemplate.getForEntity(readUrl,Order.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    void getAll() {
        String url = BASE_URL + "/getall";
        List<Order> carts = restTemplate.getForObject(url,List.class);
        assertNotNull(carts);
        System.out.println(carts);
    }

}