package com.example.book_store.book;

import com.example.book_store.domain.Author;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

import java.util.List;


@Setter
@Getter

public class BookRequest {

    @NotBlank(message = "Title can't be Empty or Null")
    private String title;

    private Long isbn;

//    private String author;

    private String publisher;

    private List<Long> authorIdList;

}

