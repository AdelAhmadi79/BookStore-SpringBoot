package com.example.book_store.book;



import java.util.List;

public interface BookService {

    BookSrv getSingleBookSrv(Long id);

    List<BookSrv> getBookSrvs();

    void deleteBook(Long id);

    BookSrv updateBook(Long id,BookRequest bookRequest);

    BookSrv saveBook(BookRequest bookReq);

}
