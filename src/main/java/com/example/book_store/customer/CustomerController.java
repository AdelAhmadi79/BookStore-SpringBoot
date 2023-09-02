package com.example.book_store.customer;

import com.example.book_store.book.ReserveBookRequest;
import com.example.book_store.customer.reserv.ProReserveRequest;
import com.example.book_store.customer.reserv.ProReserveSrv;
import com.example.book_store.customer.reserv.ReservationService;
import com.example.book_store.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ReservationService reservationService;

    @PatchMapping("customers/{customerId}/reserve")
    public CustomerSrv reserveBooks(@RequestBody ReserveBookRequest reserveBookRequest, @PathVariable Long customerId) {

        return customerService.reserveBooks(reserveBookRequest.getBooksIdList(), customerId);
    }

    @GetMapping("customers/{id}")
    public Customer getSingleCustomer(@PathVariable Long id){
        return customerService.getSingleCustomer(id);
    }

    @PatchMapping("customers/{customerId}/proReserve")
    public ProReserveSrv proReserveBooks(@RequestBody ProReserveRequest proReserveRequest, @PathVariable Long customerId){
        return reservationService.chooseStrategy(proReserveRequest,customerId);
    }

}
