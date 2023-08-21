package com.example.book_store.author;

import com.example.book_store.author.AuthorRepository;
import com.example.book_store.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository authorRepository;

    public void validateForCreate(AuthorRequest authorRequest) {
        if (authorRepository.existsAuthorByPenName(authorRequest.getPenName())) {
            throw new RuntimeException("This Pen Name already exists.");
        }

    }

    public void validateForUpdate(Author author, AuthorRequest authorRequest){
        if ((authorRequest.getPenName()!=null) && (!Objects.equals(authorRequest.getPenName(), author.getPenName()))){
            if (authorRepository.existsAuthorByPenName(authorRequest.getPenName())) {
                throw new RuntimeException("This Pen Name already exists.");
            }
        }
    }
}
