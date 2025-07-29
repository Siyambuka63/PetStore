package za.ac.cput.service.user;

import za.ac.cput.domain.user.Card;

import java.util.List;

public interface ICardService {
    List<Card> getAll();

    Card findByCardId(Long contactId);
    Card findByLastFourDigits(String lastFourDigits);

}
