package com.example.book_store.book;

import com.example.book_store.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;

    public void validateForCreate(BookRequest bookRequest) {
        if (bookRepository.existsBookByTitle(bookRequest.getTitle())) {
            throw new RuntimeException("This title already exists.");
        }

    }

    public void validateForUpdate(Book book, BookRequest bookRequest){
        if ((bookRequest.getTitle()!=null) && (!Objects.equals(bookRequest.getTitle(), book.getTitle()))){
            if (bookRepository.existsBookByTitle(bookRequest.getTitle())) {
                throw new RuntimeException("This title already exists.");
            }
        }
    }
}
