package com.example.book_store.customer;

import com.example.book_store.book.ReserveBookRequest;
import com.example.book_store.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PatchMapping("customers/{customerId}/reserve")
    public CustomerSrv reserveBooks(@RequestBody ReserveBookRequest reserveBookRequest, @PathVariable Long customerId) {

        return customerService.reserveBooks(reserveBookRequest.getBooksIdList(), customerId);
    }

    @GetMapping("customers/{id}")
    public Customer getSingleCustomer(@PathVariable Long id){
        return customerService.getSingleCustomer(id);
    }

}
