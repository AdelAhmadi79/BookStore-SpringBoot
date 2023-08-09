package com.example.book_store.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class BookSrv {


    private Long  id;

    private String title;

    private String author;

    private String publisher;

//    private Book tempBook = bookService.getSingleBook(id);


}
