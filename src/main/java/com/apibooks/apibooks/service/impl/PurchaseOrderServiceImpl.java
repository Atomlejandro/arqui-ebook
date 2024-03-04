package com.apibooks.apibooks.service.impl;

import com.apibooks.apibooks.controller.dto.PurchaseRequestDTO;
import com.apibooks.apibooks.entities.Book;
import com.apibooks.apibooks.entities.Client;
import com.apibooks.apibooks.entities.PurchaseOrder;
import com.apibooks.apibooks.repository.BookRepository;
import com.apibooks.apibooks.repository.CardRepository;
import com.apibooks.apibooks.repository.ClientRepository;
import com.apibooks.apibooks.repository.PurchaseOrderRepository;
import com.apibooks.apibooks.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    @Override
    public PurchaseOrder createPurchase(PurchaseRequestDTO purchaseRequest) {
        Client client = clientRepository.findById(purchaseRequest.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        if (client.getCard().getBalance() <= 0) {
            throw new RuntimeException("Insufficient balance");
        }

        List<Book> purchasedBooks = new ArrayList<>();
        double totalCost = 0;

        for (Map.Entry<String, Integer> entry : purchaseRequest.getBookQuantities().entrySet()) {
            Book book = bookRepository.findByIsbn(entry.getKey()).orElseThrow(() -> new RuntimeException("Book not found: " + entry.getKey()));
            int quantity = entry.getValue();

            if (book.getUnits() < quantity) {
                throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
            }

            book.setUnits(book.getUnits() - quantity);
            bookRepository.save(book);

            totalCost += book.getPrice() * quantity;
            purchasedBooks.add(book);
        }

        if (client.getCard().getBalance() < totalCost) {
            throw new RuntimeException("Insufficient balance for the total cost of purchase");
        }

        client.getCard().setBalance(client.getCard().getBalance() - totalCost);
        cardRepository.save(client.getCard());

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setClient(client);
        purchaseOrder.setBooks(purchasedBooks);
        purchaseOrder.setTotalCost(totalCost);
        purchaseOrder.setOrderDate(new Date());

        return purchaseOrderRepository.save(purchaseOrder);
    }
}