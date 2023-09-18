package com.example.book_store.book;

import com.example.book_store.author.AuthorSrvForGetBooks;
import com.example.book_store.customer.CustomerSrv;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class BookSrvWithoutCustomer {

    private long id;

    private String title;

    private Long isbn;

    private String publisher;

    private List<AuthorSrvForGetBooks> authorSrvForGetBooksSet;

    private CustomerSrv customerSrv;
}
