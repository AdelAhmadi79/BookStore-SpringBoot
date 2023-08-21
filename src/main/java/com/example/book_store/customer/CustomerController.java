package com.example.book_store.customer;

import com.example.book_store.book.ReserveBookRequest;
import com.example.book_store.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PatchMapping("customers/{customerId}/reserve")
    public CustomerSrv reserveBooks(@RequestBody ReserveBookRequest reserveBookRequest, @PathVariable Long customerId){

        return  customerService.reserveBooks(reserveBookRequest.getBooksIdList(),customerId);
    }

}
