package com.example.book_store.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Builder
public class BookSrv {


    private Long  id;

    private String title;

//    private String author;

    private String publisher;

    private List<String> authorsPenName;

}
