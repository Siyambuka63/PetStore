package za.ac.cput.factory.user;

import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Card;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.domain.user.User;
import za.ac.cput.util.Helper;

import java.util.List;

public class UserFactory {
    public static User createUser(
            long userID,
            String firstName,
            String middleName,
            String lastName,
            String password,
            List<Product> wishlistItems,
            List<Review> reviews,
            Card card,
            Address shippingAddress,
            Address billingAddress,
            Contact contact
    ) {
        if (Helper.isNullOrEmpty(firstName)) return null;
        if (Helper.isNullOrEmpty(lastName)) return null;
        if (Helper.isNullOrEmpty(password)) return null;
        if (contact == null) return null;
        return new User.Builder()
                .setUserID(userID)
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .setPassword(password)
                .setWishlistItems(wishlistItems)
                .setReviews(reviews)
                .setCard(card)
                .setShippingAddress(shippingAddress)
                .setBillingAddress(billingAddress)
                .setContact(contact)
                .build();
    }
}
