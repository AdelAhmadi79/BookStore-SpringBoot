package com.example.book_store.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String authorName;

    @Column
    private String authorLName;

    @Column
    private String penName;

    @OneToOne()
    @JoinColumn(name = "address_id")
    Address address;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> book;

}
