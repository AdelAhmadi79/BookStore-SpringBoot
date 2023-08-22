package com.example.book_store.author;

import com.example.book_store.domain.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorSrvWithoutBooks {
    private Long id;


    private String authorName;


    private String authorLastName;


    private String penName;

    private Address address;
}
