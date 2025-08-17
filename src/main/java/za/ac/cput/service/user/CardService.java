package za.ac.cput.service.user;

import za.ac.cput.domain.user.Card;
import za.ac.cput.service.IService;

import java.util.List;

public interface CardService extends IService<Card, Long> {
    List<Card> getAll();

    Card findByLastFourDigits(String lastFourDigits);
}
