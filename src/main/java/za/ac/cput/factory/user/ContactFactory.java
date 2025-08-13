package za.ac.cput.factory.user;

import za.ac.cput.domain.user.Contact;
import za.ac.cput.util.Helper;

public class ContactFactory {
    public static Contact createContact(long contactId, String number, String email) {
        if (!Helper.isValidPhoneNumber(number)) return null;
        if (!Helper.isValidEmail(email)) return null;
        return new Contact.Builder()
                .setContactId(contactId)
                .setEmail(email)
                .setPhone(number)
                .build();
    }
}
