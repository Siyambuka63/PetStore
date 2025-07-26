package za.ac.cput.factory.user;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.user.Card;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    private static Card validCard = CardFactory.createCard(987654456,"Ozow", "Visa_4456","4456","Visa");
    private static Card cardWithNullProvider = CardFactory.createCard(987654456,"","Visa_4456","4456","Visa");
    private static Card cardWithNullToken = CardFactory.createCard(987654456, "Ozow", "","4456","Visa");
    private static Card cardWithNullLastFourDigits = CardFactory.createCard(987654456, "Ozow", "Visa_4456","","Visa");
    private static Card cardWithNullBrand = CardFactory.createCard(987654456, "Ozow", "Visa_4456","4456","");


    @Test
    void createValidCard() {
    assertNotNull(validCard);
    System.out.println(validCard);
    }

    @Test
    void createCardWithNullProvider() {
    assertNull(cardWithNullProvider);
    System.out.println(cardWithNullProvider);
    }

    @Test
    void createCardWithNullToken() {
        assertNull(cardWithNullToken);
        System.out.println(cardWithNullToken);

    }

    @Test
    void createCardWithNullLastFourDigits() {
        assertNull(cardWithNullLastFourDigits);
        System.out.println(cardWithNullLastFourDigits);
    }

    @Test
    void createCardWithNullBrand() {
        assertNull(cardWithNullBrand);
        System.out.println(cardWithNullBrand);
    }


}