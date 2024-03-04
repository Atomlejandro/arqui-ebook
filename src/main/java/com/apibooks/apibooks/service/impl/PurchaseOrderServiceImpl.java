package com.apibooks.apibooks.service.impl;

import com.apibooks.apibooks.controller.dto.PurchaseRequestDTO;
import com.apibooks.apibooks.entities.Book;
import com.apibooks.apibooks.entities.Card;
import com.apibooks.apibooks.entities.Client;
import com.apibooks.apibooks.entities.PurchaseOrder;
import com.apibooks.apibooks.repository.BookRepository;
import com.apibooks.apibooks.repository.CardRepository;
import com.apibooks.apibooks.repository.ClientRepository;
import com.apibooks.apibooks.repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public PurchaseResponseDTO processPurchase(PurchaseRequestDTO purchaseRequestDTO) throws Exception {
        // Validar la existencia del cliente
        Client client = clientRepository.findById(purchaseRequestDTO.getClientId())
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        // Validar la existencia de cada libro y el stock
        double totalCost = 0.0;
        List<Book> booksToPurchase = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : purchaseRequestDTO.getQuantities().entrySet()) {
            String isbn = entry.getKey();
            Integer quantity = entry.getValue();

            Book book = bookRepository.findByIsbn(isbn)
                    .orElseThrow(() -> new Exception("Libro con ISBN " + isbn + " no encontrado"));
            if (book.getUnits() < quantity) {
                throw new Exception("Stock insuficiente para el libro con ISBN: " + isbn);
            }

            totalCost += book.getPrice() * quantity;
            booksToPurchase.add(book);
        }

        // Verificar saldo disponible en la tarjeta de membresía
        Card card = client.getCard();
        if (card.getBalance() < totalCost) {
            throw new Exception("Saldo insuficiente");
        }

        // Actualizar saldo de la tarjeta
        card.setBalance(card.getBalance() - totalCost);
        cardRepository.save(card);

        // Actualizar stock de libros y registrar la compra
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setClient(client);
        purchaseOrder.setOrderDate(new Date());
        purchaseOrder.setTotalCost(totalCost);
        // Aquí se asume que tienes una manera de relacionar los libros con la orden de compra
        purchaseOrderRepository.save(purchaseOrder);

        for (Book book : booksToPurchase) {
            book.setUnits(book.getUnits() - purchaseRequestDTO.getQuantities().get(book.getIsbn()));
            bookRepository.save(book);
        }

        // Crear y retornar el DTO de respuesta
        PurchaseResponseDTO responseDTO = new PurchaseResponseDTO();
        // Configura el DTO de respuesta según tus necesidades
        return responseDTO;
    }
}