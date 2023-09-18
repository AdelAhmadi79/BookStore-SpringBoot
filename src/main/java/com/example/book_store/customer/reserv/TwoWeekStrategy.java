package com.example.book_store.customer.reserv;

import com.example.book_store.book.BookRepository;
import com.example.book_store.customer.CustomerRepository;
import com.example.book_store.domain.model.Book;
import com.example.book_store.domain.model.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        book.setReturnDeadLine(LocalDate.now().plusDays(14));
        book.setCustomer(customer);
        book.setReservationType(ReservationType.TWO_WEEK);
        bookRepository.saveAndFlush(book);
        return CustomerToProReserveSrv(customer, book);
    }

    private ProReserveSrv CustomerToProReserveSrv(Customer customer , Book book) {
        ProReserveSrv proReserveSrv = ProReserveSrv.builder()
                .id(customer.getId())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .bookTitle(book.getTitle())
                .build();
        if (book.getReservationType() != null){
            proReserveSrv.setReservationType(book.getReservationType());
        }
        else {
            throw new RuntimeException("Reservation type could not be null!");
        }
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
