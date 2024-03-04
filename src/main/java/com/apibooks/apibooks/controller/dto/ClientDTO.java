package com.apibooks.apibooks.controller.dto;

import com.apibooks.apibooks.entities.Card;
import com.apibooks.apibooks.entities.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

    private Long idClient;
    private String name;
    private String country;
    private String city;
    private String sex;
    private String job;
    private Card card;
    private List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
}
