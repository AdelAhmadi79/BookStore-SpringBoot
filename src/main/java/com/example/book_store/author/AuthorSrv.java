package com.example.book_store.author;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorSrv {

    private Long id;


    private String authorName;


    private String authorLName;


    private String penName;
}
