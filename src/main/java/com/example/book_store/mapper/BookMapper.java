package com.example.book_store.mapper;

import com.example.book_store.author.AuthorRepository;
import com.example.book_store.book.BookRequest;
import com.example.book_store.book.BookSrv;
import com.example.book_store.domain.model.Author;
import com.example.book_store.domain.model.Book;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;

@Mapper
public interface BookMapper {
    @Mapping(target = "authors", ignore = true)
    Book bookReqToBook(BookRequest bookRequest);

    @AfterMapping
    default void mapAuthorIdsToAuthors(BookRequest bookRequest, @MappingTarget Book book ,@Context AuthorRepository authorRepository) {
        if (bookRequest.getAuthorIdList() == null){
            return;
        }
        Set<Author> authors = new HashSet<>();
        for (Long authorID: bookRequest.getAuthorIdList()) {
           Author author = authorRepository.findById(authorID).orElseThrow(()-> new RuntimeException("author with ID " + authorID + "could not be found."));
            authors.add(author);
        }
        book.setAuthors(authors);
    }


    BookSrv bookToBookSrv(Book book);
}
