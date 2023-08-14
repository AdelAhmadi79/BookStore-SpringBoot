package com.example.book_store.book;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookSrv>> getBooks() {
        return new  ResponseEntity<>(bookService.getBookSrvs() , HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookSrv> getBook(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getSingleBookSrv(id),HttpStatus.OK);
    }
//Access level
    @PostMapping("/books")
    public ResponseEntity<BookSrv>  createBook(@Valid @RequestBody BookRequest bookReq){
        //The bookService.saveBook(bookReq) returns an object with type BookSrv
        return new  ResponseEntity<>(bookService.saveBook(bookReq), HttpStatus.CREATED) ;
    }

    @PatchMapping("/books/{id}")
    private ResponseEntity<BookSrv> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest){
        return new ResponseEntity<>(bookService.updateBook(id,bookRequest),HttpStatus.OK);
    }


    @DeleteMapping("/books/{id}")
    private ResponseEntity<Long> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
