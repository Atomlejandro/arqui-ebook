package com.apibooks.apibooks.entities;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCard;

    @Column(name = "balance", nullable = false)
    private double balance; // Se recomienda usar double para el saldo

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;
}
