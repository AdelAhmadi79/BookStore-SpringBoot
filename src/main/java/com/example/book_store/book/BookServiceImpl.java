package com.example.book_store.book;

import com.example.book_store.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;


//
    private BookSrv bookToBookSrv(Book book){
        BookSrv bookSrv = new BookSrv();
        bookSrv.setTitle(book.getTitle());
        bookSrv.setAuthor(book.getAuthor());
        bookSrv.setPublisher(book.getPublisher());
        return bookSrv;
    }

    private Book bookReqToBook(BookRequest bookRequest){
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());
        book.setPublisher(bookRequest.getPublisher());
        book.setIsbn(bookRequest.getIsbn());
        return book;
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

    //ToDO; override this
    @Override
    public Book updateBook(Long id) {
        return null;
    }

    //ToDo: Override this method and find out why it needs to use entityManager
    @Override
    public Book saveBook(BookRequest bookReq) {
        return bookRepo.save(bookReqToBook(bookReq));
    }

    //
}
