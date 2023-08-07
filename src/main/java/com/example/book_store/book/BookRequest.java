package com.example.book_store.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
public class BookRequest {

    @NotBlank
    private String title;

    private long isbn;

    private String author;

    private String publisher;

}

