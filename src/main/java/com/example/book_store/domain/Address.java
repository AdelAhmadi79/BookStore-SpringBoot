package com.example.book_store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    private String street;

    @Column
    private String city;

    @OneToOne
    @JoinColumn(name = "authorId")
    private Author author;
}
