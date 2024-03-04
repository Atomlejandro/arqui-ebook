package com.apibooks.apibooks.controller;


import com.apibooks.apibooks.controller.dto.PurchaseRequestDTO;
import com.apibooks.apibooks.controller.dto.PurchaseResponseDTO;
import com.apibooks.apibooks.entities.PurchaseOrder;
import com.apibooks.apibooks.service.impl.PurchaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
/*
    @Autowired
    private IPurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> makePurchase(@RequestBody PurchaseRequestDTO purchaseRequestDTO) {
        PurchaseResponseDTO responseDTO = purchaseService.processPurchase(purchaseRequestDTO);
        if (responseDTO.isSuccess()) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }*/

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequest) {
        PurchaseOrder purchaseOrder = purchaseOrderService.createPurchase(purchaseRequest);
        return ResponseEntity.ok(purchaseOrder);
    }
}