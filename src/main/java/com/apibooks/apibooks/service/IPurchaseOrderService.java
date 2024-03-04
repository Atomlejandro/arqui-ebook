package com.apibooks.apibooks.service;

import com.apibooks.apibooks.controller.dto.PurchaseRequestDTO;
import com.apibooks.apibooks.entities.PurchaseOrder;

public interface IPurchaseOrderService {
    PurchaseOrder createPurchase(PurchaseRequestDTO purchaseRequest);
}