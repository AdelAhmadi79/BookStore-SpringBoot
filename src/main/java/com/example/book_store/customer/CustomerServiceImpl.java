package com.example.book_store.customer;

import com.example.book_store.book.BookRepository;
import com.example.book_store.domain.model.Book;
import com.example.book_store.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepo;
    private final BookRepository bookRepo;

    @Override
    public CustomerSrv reserveBooks(Set<Long> bookIdList, Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with Id \"" + customerId + "\" could not be found."));
        for (Long bookId : bookIdList) {
            Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book with Id \"" + bookId + "\" could not be found."));
            book.setCustomer(customer);
        }
        //ToDo: I don't remember Why I need this?
        customerRepo.saveAndFlush(customer);
        //here without entity manager updateAt shouldn't get updated(should be null)
        return customerToCustomerSrvForReserve(customer);
    }

    @Override
    public Customer getSingleCustomer(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer with Id \"" + id + "\" could not be found."));
    }

    public CustomerSrv customerToCustomerSrvForReserve(Customer customer) {
        CustomerSrv customerSrv = CustomerSrv.builder()
                .id(customer.getId())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
        Set<String> bookTitle = new HashSet<>();
        for (Book book : customer.getBooks()) {
            bookTitle.add(book.getTitle());
        }
        customerSrv.setBookTitles(bookTitle);
        if (customer.getFirstName() != null)
            customerSrv.setFirstName(customer.getFirstName());

        if (customer.getLastName() != null)
            customerSrv.setLastName(customer.getLastName());

        if (customer.getBirthDay() != null)
            customerSrv.setBirthDay(customer.getBirthDay());

        if (customer.getEmail() != null)
            customerSrv.setEmail(customer.getEmail());

        return customerSrv;
    }

}
