package za.ac.cput.factory;

import za.ac.cput.domain.Card;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.ShippingAddress;
import za.ac.cput.domain.User;
import za.ac.cput.domain.enums.Role;
import za.ac.cput.util.Helper;

public class UserFactory {
    public static User createUser(
            int userID,
            String firstName,
            String lastName,
            String password,
            Card card,
            ShippingAddress address,
            Contact contact,
            Role role
    ) {
        if (Helper.isNullOrEmpty(firstName)) return null;
        if (Helper.isNullOrEmpty(lastName)) return null;
        if (Helper.isNullOrEmpty(password)) return null;
        if (card == null) return null;
        if (address == null) return null;
        if (contact == null) return null;
        if (role == null) return null;
        return new User.Builder()
                .setUserID(userID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPassword(password)
                .setCard(card)
                .setAddress(address)
                .setContact(contact)
                .setRole(role)
                .build();
    }

    public static User createUser(
            int userID,
            int contactID,
            String firstName,
            String lastName,
            String password,
            String cardNumber,
            String cardHolder,
            String expiryDate,
            String street,
            String suburb,
            String flatDetail,
            String city,
            String postalCode,
            String email,
            String phone,
            Role role
    ) {
        Contact contact = ContactFactory.createContact(contactID, phone, email);
        ShippingAddress address = null; //ToDo code
        Card card = null; //ToDo code

        return UserFactory.createUser(userID, firstName, lastName, password, card, address, contact, role);
    }
}
