package com.example.book_store.book;

import com.example.book_store.domain.Book;

import java.util.List;

public interface BookService {

    BookSrv getSingleBookSrv(Long id);

    List<BookSrv> getBookSrvs();

    void deleteBook(Long id);

    Book updateBook(Long id);

    Book saveBook(BookRequest bookReq); //why I need this?

}
