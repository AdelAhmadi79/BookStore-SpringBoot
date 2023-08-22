package com.example.book_store.customer;


import com.example.book_store.domain.Customer;

import java.util.Set;

public interface CustomerService {

    CustomerSrv reserveBooks(Set<Long> bookIdList, Long customerId);

    Customer getSingleCustomer(Long id);
}
