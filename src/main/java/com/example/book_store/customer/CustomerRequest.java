package com.example.book_store.customer;


import com.example.book_store.domain.Book;

import java.time.LocalDate;
import java.util.Set;

public class CustomerRequest {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDay;

    private Set<Book> books;
}
