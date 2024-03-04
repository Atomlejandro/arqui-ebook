package com.apibooks.apibooks.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequestDTO {

    private Long clientId;
    private List<String> bookIsbn;
    private Map<String, Integer> quantities;
}
