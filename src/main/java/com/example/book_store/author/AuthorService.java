package com.example.book_store.author;

public interface AuthorService {

    AuthorSrv saveAuthor(AuthorRequest authorRequest);

    AuthorSrv updateAuthor(Long id, AuthorRequest authorRequest);
}
