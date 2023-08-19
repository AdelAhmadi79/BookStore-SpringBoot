package com.example.book_store.book;

import lombok.*;

import jakarta.validation.constraints.NotBlank;



@Setter
@Getter

public class BookRequest {

    @NotBlank(message = "Title can't be Empty or Null")
    private String title;

    private Long isbn;

    private String author;

    private String publisher;

}

