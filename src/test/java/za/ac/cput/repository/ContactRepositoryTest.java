package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.ContactFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactRepositoryTest {

    private static ContactRepository repository = ContactRepository.getRepository();

    private static Contact contact = ContactFactory.createContact("0987654321", "email@gmail.com");

    @Test
    @Order(1)
    void create() {
        Contact createdContact = repository.create(contact);
        assertNotNull(createdContact);
        System.out.println(createdContact);
    }

    @Test
    @Order(2)
    void read() {
        Contact readContact = repository.read(contact.getEmail());
        assertNotNull(readContact);
        System.out.println(readContact);
    }

    @Test
    @Order(3)
    void update() {
        Contact newContact = new Contact.Builder().copy(contact).setPhone("0123456789").build();
        Contact updatedContact = repository.update(newContact);
        assertNotNull(updatedContact);
        System.out.println(updatedContact);
    }

    @Test
    @Order(4)
    void delete() {
        Contact deletedContact = repository.delete(contact.getEmail());
        assertNotNull(deletedContact);
        System.out.println(deletedContact);
    }

    @Test
    @Order(5)
    void getAll() {
        List<Contact> contacts = repository.getAll();
        assertNotNull(contacts);
        System.out.println(contacts);
    }
}