package com.apibooks.apibooks.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchase;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "purchase_order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Column(name = "totalCost", nullable = false)
    private double totalCost;
}
