package com.example.book_store.author;


import com.example.book_store.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<AuthorSrv> createAuthor(@RequestBody AuthorRequest authorRequest) {
        return new ResponseEntity<>(authorService.saveAuthor(authorRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/authors/{id}")
    private ResponseEntity<AuthorSrv> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest authorRequest) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorRequest), HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    private ResponseEntity<Author> getSingleAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getSingleAuthor(id), HttpStatus.OK);
    }

    @GetMapping("/authors/without/{id}")
    private ResponseEntity<AuthorSrvWithoutBooks> getSingleAuthorWithoutBooks(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getSingleAuthorWithoutBooks(id), HttpStatus.OK);
    }

}
