package com.example.book_store.domain;


import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "author_id")
    Address address;

}
