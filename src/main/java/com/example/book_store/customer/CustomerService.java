package com.example.book_store.customer;



import java.util.Set;

public interface CustomerService {

    CustomerSrv reserveBooks(Set<Long> bookIdList, Long customerId);
}
