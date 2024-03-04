package com.apibooks.apibooks.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "sex")
    private String sex;
    @Column(name = "job")
    private String job;

    @OneToOne
    @JoinColumn(name = "idCard")
    private Card card;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
}
