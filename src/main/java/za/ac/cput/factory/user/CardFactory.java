package za.ac.cput.factory.user;

import za.ac.cput.domain.user.Card;
import za.ac.cput.util.Helper;

public class CardFactory {

    public static Card createCard(long cardId, String provider, String token, String lastFourDigits, String brand) {
        if (!Helper.isNullOrEmpty(provider) || Helper.isNullOrEmpty(token) || !Helper.isNullOrEmpty(brand))
            return null;

        if (!Helper.isValidLastFourDigits(lastFourDigits)) return null;

        return new Card.Builder()
                .setCardID(cardId)
                .setProvider(provider)
                .setToken(token)
                .setLastFourDigits(lastFourDigits)
                .setBrand(brand)
                .build();

    }
}
