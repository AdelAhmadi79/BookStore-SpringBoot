package com.example.book_store.domain.model;

import com.example.book_store.customer.reserv.ReservationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private Long isbn;

    @Column
    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @Column
    private LocalDate returnDeadLine;

    @Column
    private Boolean isDeadlineExpired;


    @Column
    private String publisher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Author> authors;


    @ManyToOne (fetch = FetchType.LAZY)
//in alan chia ro lazy migire?
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
