package com.example.book_store.customer.reserv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProReserveRequest {


    private Long bookId;
    private ReservationType reservationType;
}
