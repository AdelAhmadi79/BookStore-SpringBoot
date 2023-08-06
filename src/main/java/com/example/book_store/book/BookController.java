package com.example.book_store.book;


import com.example.book_store.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    ResponseEntity<List<BookSrv>> getBooks() {
        return new  ResponseEntity<>(bookService.getBookSrvs() , HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    ResponseEntity<BookSrv> getBook(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getSingleBookSrv(id),HttpStatus.OK);
    }
//Access level
    @PostMapping("/books")
    private ResponseEntity<Book>  createBook(@RequestBody BookRequest bookReq){
        //The bookService.saveBook(bookReq) returns an object with type Book
        return new  ResponseEntity<>(bookService.saveBook(bookReq), HttpStatus.CREATED) ;
    }
}
