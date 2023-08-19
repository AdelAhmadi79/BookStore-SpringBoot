package com.example.book_store.author;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<AuthorSrv>  createAuthor(@RequestBody AuthorRequest authorRequest){
            return new ResponseEntity<>(authorService.saveAuthor(authorRequest), HttpStatus.OK) ;
    }
}
