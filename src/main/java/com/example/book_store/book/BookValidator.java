package com.example.book_store.book;

import com.example.book_store.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;

//    public void validateForCreate(BookRequest bookRequest) {
//        if (bookRepository.existsBookByTitle(bookRequest.getTitle())) {
//            throw new RuntimeException("This title already exists.");
//        }
//        if (((bookRequest.getAuthor() == null) || (bookRequest.getAuthor().trim().isEmpty()))
//                &&
//                ((bookRequest.getPublisher() == null) || (bookRequest.getPublisher().trim().isEmpty()))) {
//            throw new RuntimeException("At least one of the \"Publisher\" or \"Author\" fields should be filled in. ");
//        }
//    }

    public void validateForUpdate(Book book, BookRequest bookRequest){
        if ((bookRequest.getTitle()!=null) && (!Objects.equals(bookRequest.getTitle(), book.getTitle()))){
            if (bookRepository.existsBookByTitle(bookRequest.getTitle())) {
                throw new RuntimeException("This title already exists.");
            }
        }
    }
}
