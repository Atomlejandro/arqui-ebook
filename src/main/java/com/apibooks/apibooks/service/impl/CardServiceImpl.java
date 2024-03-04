package com.apibooks.apibooks.service.impl;

import com.apibooks.apibooks.entities.Card;
import com.apibooks.apibooks.entities.Client;
import com.apibooks.apibooks.repository.CardRepository;
import com.apibooks.apibooks.repository.ClientRepository;
import com.apibooks.apibooks.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;

public class CardServiceImpl implements ICardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card rechargeCard(Long cardId, double amount) throws Exception {
        if (amount < 50000 || amount > 200000) {
            throw new IllegalArgumentException("Recharge amount must be between $50,000 and $200,000.");
        }

        Card card = cardRepository.findById(cardId).orElseThrow(() -> new Exception("Card not found with id: " + cardId));
        card.setBalance(card.getBalance() + amount);
        return cardRepository.save(card);
    }
}
