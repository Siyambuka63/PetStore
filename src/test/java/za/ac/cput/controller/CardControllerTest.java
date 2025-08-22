package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.user.Card;
import za.ac.cput.factory.user.CardFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardControllerTest {

    private static Card card;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/petstore/card";

    @BeforeAll
    static void setUp() {
        card = CardFactory.createCard(987554456,"Ozow","Visa_4456","4456","Visa");
    }

    @Test
    @Order(1)
    void create() {
        String url = BASE_URL + "/create";
        Card createdCard = restTemplate.postForObject(url, card, Card.class);
        assertNotNull(createdCard);
        System.out.println(createdCard);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read" + card.getCardId();
        ResponseEntity<Card> readCard = restTemplate.getForEntity(url, Card.class);
        assertNotNull(readCard);
        System.out.println(readCard);
    }

    @Test
    @Order(3)
    void update() {
        Card updateCard = new Card.Builder().copy(card).setCardID(987554456).build();
        String url = BASE_URL + "/update";
       ResponseEntity<Card>  updatedCard = restTemplate.postForEntity(url, card, Card.class);
        assertNotNull(updatedCard);
        System.out.println(updatedCard);
    }

    @Test
    @Order(5)
    void delete() {
        String url = BASE_URL + "/delete" + card.getCardId();
        restTemplate.delete(url);
        String readUrl = BASE_URL + "/read" + card.getCardId();
        ResponseEntity<Card> readCard = restTemplate.getForEntity(readUrl, Card.class);
        assertEquals(HttpStatus.NOT_FOUND, readCard.getStatusCode());
        System.out.println("true");
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";
        List<Card> cards = restTemplate.getForObject(url, List.class);
        assertNotNull(cards);
        System.out.println(cards);
    }

}