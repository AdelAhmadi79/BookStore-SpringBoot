package com.example.book_store.author;

import com.example.book_store.domain.model.Author;

public interface AuthorService {

    AuthorSrv saveAuthor(AuthorRequest authorRequest);

    AuthorSrv updateAuthor(Long id, AuthorRequest authorRequest);

    Author getSingleAuthor(Long id);

    AuthorSrvWithoutBooks getSingleAuthorWithoutBooks(Long id);
}
