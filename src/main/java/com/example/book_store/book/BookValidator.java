package com.example.book_store.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;
    public void validate(BookRequest bookRequest) {
        if (bookRepository.existsBookByTitle(bookRequest.getTitle())){
            throw new RuntimeException("This title already exists.");
        }
        if (bookRequest.getAuthor() == null && bookRequest.getPublisher() == null) {
            throw new RuntimeException("At least one of the \"Publisher\" or \"Author\" fields should be filled in. ");
        }
    }
}
