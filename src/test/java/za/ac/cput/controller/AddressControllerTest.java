package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Type;
import za.ac.cput.factory.user.AddressFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressControllerTest {
    private static Address address;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/petstore/address";

    @BeforeAll
    static void setUp() {
        address = AddressFactory.createAddress(1234, "Flat", "Cape Town", "2234 M Street", "Krraifontein", "7570", Type.Both);
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        Address createdAddress = restTemplate.postForObject(url, address, Address.class);
        assertNotNull(createdAddress);
        System.out.println(createdAddress);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + address.getAddressID();
        ResponseEntity<Address> readAddress = restTemplate.getForEntity(url, Address.class);
        assertNotNull(readAddress);
        System.out.println(readAddress);
    }

    @Test
    @Order(3)
    void update() {
        Address updateAddress = new Address.Builder().copy(address).setPostalCode("6570").build();
        String url = BASE_URL + "/update";
        ResponseEntity<Address> updated = restTemplate.postForEntity(url, updateAddress, Address.class);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete/" + address.getAddressID();
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + address.getAddressID();
        ResponseEntity<Address> reponse = restTemplate.getForEntity(readUrl, Address.class);
        assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
        System.out.println("true");
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        List<Address> addresses = restTemplate.getForObject(url, List.class);
        assertNotNull(addresses);
        System.out.println(addresses);
    }
}