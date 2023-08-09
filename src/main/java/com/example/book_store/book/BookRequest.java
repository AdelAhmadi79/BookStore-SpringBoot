package com.example.book_store.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;



@Setter
@Getter
@Builder
public class BookRequest {

    @NotBlank
    private String title;

    private Long isbn;

    private String author;

    private String publisher;

}

