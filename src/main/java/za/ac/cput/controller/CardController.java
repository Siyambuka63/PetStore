package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.Card;
import za.ac.cput.service.user.CardService;
import za.ac.cput.service.user.impl.CardServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    private CardServiceImpl service;

    @Autowired
    public CardController(CardServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Card create(@RequestBody Card card) {
      return service.create(card);
    }

    @GetMapping("/read/{cardId}")
    public Card read(@PathVariable Long cardId) {
    return service.read(cardId);
    }

    @PutMapping("/update")
    public Card update(@RequestBody Card card) {
        return service.update(card);
    }

    @DeleteMapping("/delete/{cardId}")
    public void delete(@PathVariable Long cardId) {
        service.delete(cardId);
    }

   @GetMapping("/getAll")
    public List<Card> getAll() {
        return service.getAll();
   }

   @GetMapping("/findByLastFourDigits/{lastFourDigits}")
    public Card findByLastFourDigits(@PathVariable String lastFourDigits) {
        return this.service.findByLastFourDigits(lastFourDigits);

   }

   @GetMapping("/findByCardId/{cardId}")
    public Card findByCardId(@PathVariable Long cardId) {
        return this.service.findByCardId(cardId);
    }
}
