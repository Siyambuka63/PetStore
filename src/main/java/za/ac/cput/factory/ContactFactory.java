package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;

public class ContactFactory {
    public static Contact createContact(String number, String email) {
        if (!Helper.isValidPhoneNumber(number)) return null;
        if (!Helper.isValidEmail(email)) return null;
        return new Contact.Builder()
                .setEmail(email)
                .setPhone(number)
                .build();
    }
}
