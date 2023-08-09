package com.example.book_store.book;

import com.example.book_store.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        bookRepo.deleteById(id);
    }

    //ToDO; put change to patch, SaveAndFlush to SavendUpdate , search what does refresh do
    @Override
    @Transactional
    public BookSrv updateBook(Long id, BookRequest bookRequest) {
//        Book managedBook = bookRepo.saveAndFlush(bookReqToBook(id,bookRequest));
//        entityManager.refresh(managedBook);
//        return bookToBookSrv(bookRepo.findById(id).get());
        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent()) {
            throw new RuntimeException("Book with id:" + id + " doesn't exist");
        }
        if (bookRequest.getTitle() != null)
            book.get().setTitle(bookRequest.getTitle());
        if (bookRequest.getAuthor() != null)
            book.get().setTitle(bookRequest.getAuthor());
        if (bookRequest.getIsbn() != null)
            book.get().setIsbn(bookRequest.getIsbn());
        if (bookRequest.getPublisher() != null)
            book.get().setPublisher(bookRequest.getPublisher());
        bookRepo.saveAndFlush(book.get());
        return bookToBookSrv(book.get());
    }


    @Override
    public BookSrv saveBook(BookRequest bookReq) {
        bookValidator.validate(bookReq);
        return bookToBookSrv(bookRepo.save(bookReqToBook(bookReq)));

    }


}
