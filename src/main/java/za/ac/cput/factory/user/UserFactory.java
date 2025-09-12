package za.ac.cput.factory.user;

import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Card;
import za.ac.cput.domain.user.User;
import za.ac.cput.util.Helper;

import java.util.List;

public class UserFactory {
    public static User createUser(
            String firstName,
            String middleName,
            String lastName,
            String password,
            List<Review> reviews,
            Card card,
            Address shippingAddress,
            Address billingAddress,
            String email,
            String phone
    ) {
        if (Helper.isNullOrEmpty(firstName)) return null;
        if (Helper.isNullOrEmpty(lastName)) return null;
        if (Helper.isNullOrEmpty(password)) return null;
        if (!Helper.isValidPhoneNumber(phone)) return null;
        if (!Helper.isValidEmail(email)) return null;
        return new User.Builder()
                .setEmail(email)
                .setPhoneNumber(phone)
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .setPassword(password)
                .setReviews(reviews)
                .setCard(card)
                .setShippingAddress(shippingAddress)
                .setBillingAddress(billingAddress)
                .build();
    }
}
