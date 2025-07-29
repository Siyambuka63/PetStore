package za.ac.cput.factory.user;

import za.ac.cput.domain.user.Card;

public class CardFactory {

    private static String extractLastFourDigits(long cardId) {
        String cardIdStr = String.valueOf(cardId);
        if (cardIdStr.length() >= 4) {
            return cardIdStr.substring(cardIdStr.length() - 4);
        }
        return "####";
    }

    private static String generateToken(String provider, long cardId) {
        return provider + "_" + Math.abs(Long.valueOf(cardId).hashCode());
    }

    public static Card createCard(long cardId, String provider, String token, String lastFourDigits, String brand) {
        if (provider == null || token == null || lastFourDigits == null || brand == null)
            return null;

        token = generateToken(provider, cardId);
        lastFourDigits = extractLastFourDigits(cardId);

        return new Card.Builder()
                .setCardID(cardId)
                .setProvider(provider)
                .setToken(token)
                .setLastFourDigits(lastFourDigits)
                .setBrand(brand)
                .build();

    }

}
