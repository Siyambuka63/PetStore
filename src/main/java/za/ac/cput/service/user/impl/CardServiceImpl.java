package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.Card;
import za.ac.cput.repository.user.CardRepository;
import za.ac.cput.service.user.CardService;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private static CardService  cardService;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card read(Long cardId) {
        return cardRepository.getReferenceById(cardId);
    }

    @Override
    public Card update(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void delete(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findByLastFourDigits(String lastFourDigits) {
        return cardRepository.findByLastFourDigits(lastFourDigits);
    }
}
