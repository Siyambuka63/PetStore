package za.ac.cput.factory.user;

import za.ac.cput.domain.user.Contact;
import za.ac.cput.util.Helper;

public class ContactFactory {
    public static Contact createContact(int id, String number, String email) {
        if (!Helper.isValidPhoneNumber(number)) return null;
        if (!Helper.isValidEmail(email)) return null;
        return new Contact.Builder()
                .setId(id)
                .setEmail(email)
                .setPhone(number)
                .build();
    }
}
