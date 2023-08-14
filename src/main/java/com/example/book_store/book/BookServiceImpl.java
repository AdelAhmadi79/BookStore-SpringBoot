package com.example.book_store.book;

import com.example.book_store.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepo;

    private final BookValidator bookValidator;



    //
    private BookSrv bookToBookSrv(Book book) {
        return BookSrv.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .title(book.getTitle())
                .build();
    }

    private Book bookReqToBook(BookRequest bookRequest) {
        return Book.builder()
                .author(bookRequest.getAuthor())
                .title(bookRequest.getTitle())
                .publisher(bookRequest.getPublisher())
                .isbn(bookRequest.getIsbn())
                .build();
    }

    @Override
    public BookSrv getSingleBookSrv(Long id) {

        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book NOT FOUND!");
        return bookToBookSrv(book.get());
    }


    @Override
    public List<BookSrv> getBookSrvs() {
        List<Book> bookList = bookRepo.findAll();
        List<BookSrv> bookSrvList = new ArrayList<>();
        for (Book book : bookList) {
            bookSrvList.add(bookToBookSrv(book));
        }
        return bookSrvList;
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent()) {
            throw new RuntimeException("Book with ID " + "could not be found.");
        }
        bookRepo.delete(book.get());
    }


    @Override
    public BookSrv saveBook(BookRequest bookReq) {
        bookValidator.validateForCreate(bookReq);
        return bookToBookSrv(bookRepo.save(bookReqToBook(bookReq)));

    }

    @Override
//    @Transactional
    public BookSrv updateBook(Long id, BookRequest bookRequest) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        Book book = bookOptional.orElseThrow(() -> new RuntimeException("Book with ID " + "could not be found."));
//        if (!book.isPresent()) {
//            throw new RuntimeException("Book with ID " + "could not be found.");
//        }
        bookValidator.validateForUpdate(book, bookRequest);

        if (bookRequest.getTitle() != null)
            book.setTitle(bookRequest.getTitle());

        if (bookRequest.getAuthor() != null)
            book.setAuthor(bookRequest.getAuthor());

        if (bookRequest.getIsbn() != null)
            book.setIsbn(bookRequest.getIsbn());

        if (bookRequest.getPublisher() != null)
            book.setPublisher(bookRequest.getPublisher());


        if (((book.getAuthor() == null) || (book.getAuthor().trim().isEmpty()))
                &&
                ((book.getPublisher() == null) || (book.getPublisher().trim().isEmpty()))) {
            throw new RuntimeException("At least one of the \"Publisher\" or \"Author\" fields should be filled in.");
        }
        bookRepo.saveAndFlush(book);
        return bookToBookSrv(book);
    }


}
