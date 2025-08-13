package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.factory.user.ContactFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactServiceImplTest {

    private static Contact contact;

    @Autowired
    private ContactServiceImpl service;

    @BeforeAll
    static void setUp() {contact = ContactFactory.createContact(1, "0987654321", "email@gmail.com");}

    @Test
    @Order(1)
    void create() {
        Contact created = service.create(contact);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Contact read = service.read(contact.getContactId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Contact updatedContact = new Contact.Builder().copy(contact).setPhone("0123456789").build();
        Contact updated = service.update(updatedContact);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        service.delete(contact.getContactId());
        Contact deleted = service.read(contact.getContactId());
        assertNull(deleted);
        System.out.println(deleted);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Contact> contacts = service.getAll();
        assertNotNull(contacts);
        System.out.println(contacts);
    }

    @Test
    @Disabled
    void findByEmail() {
        Contact foundByEmail = service.findByEmail(contact.getEmail());
        assertNotNull(foundByEmail);
        System.out.println(foundByEmail);
    }

    @Test
    @Disabled
    void findByPhone() {
        Contact foundByPhone = service.findByPhone(contact.getPhone());
        assertNotNull(foundByPhone);
        System.out.println(foundByPhone);
    }
}