package com.apibooks.apibooks.service;

import com.apibooks.apibooks.entities.Card;

public interface ICardService {
    Card rechargeCard(Long cardId, double amount) throws Exception;
}
