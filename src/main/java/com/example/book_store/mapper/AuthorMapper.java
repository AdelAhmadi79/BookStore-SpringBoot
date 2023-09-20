package com.example.book_store.mapper;

import com.example.book_store.author.AuthorRequest;
import com.example.book_store.author.AuthorSrv;
import com.example.book_store.domain.model.Author;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {
    Author authorReqToAuthor(AuthorRequest authorRequest);

    AuthorSrv authorToAuthorSrv(Author author);
}
