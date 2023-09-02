package com.example.book_store.customer.reserv;

public interface ReservationStrategy {
    ProReserveSrv makeProReservation(Long bookId, Long customerId);
}
