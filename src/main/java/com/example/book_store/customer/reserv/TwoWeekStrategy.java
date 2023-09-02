package com.example.book_store.customer.reserv;

import com.example.book_store.book.BookRepository;
import com.example.book_store.customer.CustomerRepository;
import com.example.book_store.domain.Book;
import com.example.book_store.domain.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@RequiredArgsConstructor
@Component
public class TwoWeekStrategy implements ReservationStrategy{

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @Override
    public ProReserveSrv makeProReservation(Long bookId, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with Id \"" + customerId + "\" could not be found."));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book with Id \"" + bookId + "\" could not be found."));
        book.setCustomer(customer);
        book.setReservationType(ReservationType.ONE_WEEK);
        customerRepository.saveAndFlush(customer);
        return CustomerToProReserveSrv(customer, book.getTitle());
    }

    private ProReserveSrv CustomerToProReserveSrv(Customer customer , String bookTitle) {
        ProReserveSrv proReserveSrv = ProReserveSrv.builder()
                .id(customer.getId())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .bookTitle(bookTitle)
                .build();
        if (customer.getFirstName() != null)
            proReserveSrv.setFirstName(customer.getFirstName());
        if (customer.getLastName() != null)
            proReserveSrv.setLastName(customer.getLastName());

        if (customer.getBirthDay() != null)
            proReserveSrv.setBirthDay(customer.getBirthDay());

        if (customer.getEmail() != null)
            proReserveSrv.setEmail(customer.getEmail());
        return proReserveSrv;
    }

}
