package com.example.book_store.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder
public class CustomerSrv {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDay;

    private Date createdAt;

    private Date updatedAt;

    private Set<String> bookTitles;
}
