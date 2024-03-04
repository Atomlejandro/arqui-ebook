package com.apibooks.apibooks.controller.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseResponseDTO {
    private boolean success;
    private String message;
    private Double totalCost;
    private List<BookDTO> purchasedBooks;
    private Double remainingBalance;
}
