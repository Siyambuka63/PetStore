package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.*;

class ContactFactoryTest {

    private static Contact contactWithInvalidEmail = ContactFactory.createContact(1, "0987654321", "test@gmail.com.");
    private static Contact contactWithInvalidPhone = ContactFactory.createContact(1, "09876543211", "test@gmail.com");
    private static Contact contactWithoutEmail = ContactFactory.createContact(1, "0987654321", "");
    private static Contact contactWithoutPhone = ContactFactory.createContact(1, "", "test@gmail.com");
    private static Contact contactWithValidDetail = ContactFactory.createContact(1, "0987654321", "test@gmail.com");

    @Test
    void testCreateContactWithInvalidEmail() {
        assertNull(contactWithInvalidEmail);
        System.out.println(contactWithInvalidEmail);
    }

    @Test
    void testCreateContactWithInvalidPhone() {
        assertNull(contactWithInvalidPhone);
        System.out.println(contactWithInvalidPhone);
    }

    @Test
    void testCreateContactWithoutEmail() {
        assertNull(contactWithoutEmail);
        System.out.println(contactWithoutEmail);
    }

    @Test
    void testCreateContactWithoutPhone() {
        assertNull(contactWithoutPhone);
        System.out.println(contactWithoutPhone);
    }

    @Test
    void testCreateContactWithValidDetail() {
        assertNotNull(contactWithValidDetail);
        System.out.println(contactWithValidDetail);
    }
}