package com.example.book_store.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String authorLastName;

    @Column
    private String penName;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    Address address;


    @ManyToMany(mappedBy = "authors" , fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Book> book;

}
