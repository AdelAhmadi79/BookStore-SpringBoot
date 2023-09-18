package com.example.book_store.author;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorSrvForGetBooks {

    private Long id;


    private String authorName;


    private String authorLastName;


    private String penName;

//    private Set<Book> book;
}
