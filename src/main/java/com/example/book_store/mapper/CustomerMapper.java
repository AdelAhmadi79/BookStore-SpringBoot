package com.example.book_store.mapper;

import com.example.book_store.customer.CustomerRequest;
import com.example.book_store.customer.CustomerSrv;
import com.example.book_store.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerSrv customerToCustomerSrv(Customer customer);

    Customer customerReqToCustomer(CustomerRequest customerRequest);
}
