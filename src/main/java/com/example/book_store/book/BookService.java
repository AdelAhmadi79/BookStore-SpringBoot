package com.example.book_store.book;



import com.example.book_store.domain.Book;

import java.util.List;

public interface BookService {

    BookSrv getSingleBookSrv(Long id);

    List<BookSrv> getBookSrvs();

    List<BookSrvWithoutCustomer> getAllBooksWithoutCustomer();

    void deleteBook(Long id);

    BookSrv updateBook(Long id,BookRequest bookRequest);

    BookSrv saveBook(BookRequest bookReq);

}
