package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.ContactFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceImplTest {

    private static Address address;

    @Autowired
    private AddressServiceImpl service;

    @BeforeAll
    static void setUp() {
        address = AddressFactory.createAddress(1234, "Flat", "Cape Town", "237 Bee Street", "Kraaifontein", "7570");
    }
        @Test
        @Order(1)
        void create() {
            Address created = service.create(address);
            assertNotNull(created);
            System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Address read = service.read(address.getAddressID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Address updateAddress = new Address.Builder().copy(address).setCity("Pretoria").build();
        Address updated = service.update(updateAddress);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        service.delete(address.getAddressID());
        Address deleted = service.read(address.getAddressID());
        assertNull(deleted);
        System.out.println(deleted);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Address> addresses = service.getAll();
        assertNotNull(addresses);
        System.out.println(addresses);
    }

    @Test
    @Disabled
    void findByAddressId() {
        Address foundByAddressId = service.findByAddressId(address.getAddressID());
        assertNotNull(foundByAddressId);
        System.out.println(foundByAddressId);
    }

    @Test
    @Disabled
    void findByPostalCode() {
        Address foundByPostalCode = service.findByPostalCode(address.getPostalCode());
        assertNotNull(foundByPostalCode);
        System.out.println(foundByPostalCode);
    }
}