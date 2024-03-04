package com.apibooks.apibooks.controller;

import com.apibooks.apibooks.entities.Card;
import com.apibooks.apibooks.service.ICardService;
import com.apibooks.apibooks.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cards")
public class CardController {
/*
    @Autowired
    private CardServiceImpl cardService;

    @PostMapping("/reload")
    public ResponseEntity<String> reloadCard(@RequestBody CardReloadRequestDTO reloadRequest) {
        try {
            cardService.reloadCard(reloadRequest.getClientId(), reloadRequest.getAmount());
            return ResponseEntity.ok("Recarga exitosa");
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
@Autowired
public ICardService cardService;

    @PatchMapping("/{cardId}/recharge")
    public ResponseEntity<Card> rechargeCard(@PathVariable Long cardId, @RequestBody Map<String, Double> rechargeRequest) {
        double amount = rechargeRequest.getOrDefault("amount", 0.0);
        try {
            Card updatedCard = cardService.rechargeCard(cardId, amount);
            return ResponseEntity.ok(updatedCard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}