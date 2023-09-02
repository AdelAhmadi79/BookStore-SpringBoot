package com.example.book_store.customer.reserv;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final OneWeekStrategy oneWeek;
    private final TwoWeekStrategy twoWeek;

    public ProReserveSrv chooseStrategy(ProReserveRequest proReserveRequest, Long customerId)  {
        if (proReserveRequest.getReservationType() == ReservationType.ONE_WEEK) {
            return oneWeek.makeProReservation(proReserveRequest.getBookId(), customerId);
        } else if (proReserveRequest.getReservationType() == ReservationType.TWO_WEEK) {
            return twoWeek.makeProReservation(proReserveRequest.getBookId(), customerId);
        }
       throw new RuntimeException("Unvalid Reserve Type");
    }
}

