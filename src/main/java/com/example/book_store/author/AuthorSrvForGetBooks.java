package com.example.book_store.author;


import com.example.book_store.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
