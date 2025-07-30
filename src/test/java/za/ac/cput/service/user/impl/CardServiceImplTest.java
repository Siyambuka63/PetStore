package za.ac.cput.service.user.impl;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.user.Card;
import za.ac.cput.factory.user.CardFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CardServiceImplTest.class);
    private static Card card;

    @Autowired
    private CardServiceImpl cardService;

    @BeforeAll
    static void setUp() {
        card = CardFactory.createCard(996548990, "Ozow","Visa_8990", "8990", "Visa"  );
    }


    @Test
    @Order(1)
    void create() {
        Card createdCard = cardService.create(card);
        assertNotNull(createdCard);
        System.out.println(createdCard);
    }

    @Test
    @Order(2)
    void read() {
        Card read = cardService.read(card.getCardId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Card updatedCard = new Card.Builder().copy(card).setProvider("Paypal").build();
        Card updated = cardService.update(updatedCard);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        cardService.delete(card.getCardId());
        Card deletedCard = cardService.read(card.getCardId());
        assertNull(deletedCard);
        System.out.println(deletedCard);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Card> cards = cardService.getAll();
        assertNotNull(cards);
        System.out.println(cards);
    }

    @Test
    @Disabled
    void findByCardId() {
        Card foundbyCardId = cardService.findByCardId(card.getCardId());
        assertNotNull(foundbyCardId);
        System.out.println(foundbyCardId);
    }

    @Test
    @Disabled
    void findByLastFourDigits() {
        Card foundLastFourDigits = cardService.findByLastFourDigits(card.getLastFourDigits());
        assertNotNull(foundLastFourDigits);
        System.out.println(foundLastFourDigits);
    }
}
