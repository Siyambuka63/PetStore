package za.ac.cput.factory.user;

import za.ac.cput.domain.user.Card;

public class CardFactory {
    public static Card createCard(long cardId, String provider, String token, String lastFourDigits, String brand) {
     if (provider == null || token == null || lastFourDigits == null || brand == null)
         return null;

     return new Card.Builder()
             .setCardID(cardId)
             .setProvider(provider)
             .setToken(token)
             .setLastFourDigits(lastFourDigits)
             .setBrand(brand)
             .build();

    }


}
