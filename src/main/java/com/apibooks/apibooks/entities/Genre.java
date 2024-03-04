package com.apibooks.apibooks.entities;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTopic;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "idBook")
    private Book book;
}
