package com.example.book_store.author;


import com.example.book_store.address.AddressRequest;
import lombok.*;

@Setter
@Getter
public class AuthorRequest {

    private String authorName;

    private String authorLName;

    private String penName;

    private AddressRequest addressRequest;

}
