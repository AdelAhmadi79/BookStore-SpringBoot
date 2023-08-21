package com.example.book_store.customer;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder
public class CustomerSrv {
    private long CustomerID;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDay;

    private Date createdAt;

    private Date updatedAt;

    private Set<String> bookTitles;
}
