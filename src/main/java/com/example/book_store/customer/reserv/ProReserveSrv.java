package com.example.book_store.customer.reserv;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Builder
public class ProReserveSrv {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDay;

    private Date createdAt;

    private Date updatedAt;

    private String bookTitle;

    private ReservationType reservationType;

}
