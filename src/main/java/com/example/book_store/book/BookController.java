package com.example.book_store.book;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private static  Logger logger = LogManager.getLogger(BookController.class);
    private final BookService bookService;



    @GetMapping("/books")
    @PreAuthorize("hasAnyRole('REGULAR')")
    public ResponseEntity<List<BookSrv>> getAllBookSrvs() {
        return new ResponseEntity<>(bookService.getBookSrvs(), HttpStatus.OK);
    }

    @GetMapping("/booksFullVersion")
    public ResponseEntity<List<BookSrvWithoutCustomer>> getAllBooksFull(){
        return new ResponseEntity<>(bookService.getAllBooksWithoutCustomer(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookSrv> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getSingleBookSrv(id), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<BookSrv> createBook(@Valid @RequestBody BookRequest bookReq) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String bookReqJson = mapper.writeValueAsString(bookReq);
        //The bookService.saveBook(bookReq) returns an object with type BookSrv
        logger.warn("Received book create request: {}", bookReqJson);
        return new ResponseEntity<>(bookService.saveBook(bookReq), HttpStatus.CREATED);
    }


    @PatchMapping("/books/{id}")
    private ResponseEntity<BookSrv> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.updateBook(id, bookRequest), HttpStatus.OK);
    }


    @DeleteMapping("/books/{id}")
    private ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
