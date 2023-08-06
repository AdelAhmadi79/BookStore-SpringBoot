package com.example.book_store.book;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class BookRequest {


    private String title;


    private long isbn;


    private String author;

    private String publisher;

}

