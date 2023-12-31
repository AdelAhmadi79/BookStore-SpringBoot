package com.example.book_store.author;

import com.example.book_store.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository <Author,Long> {

    boolean existsAuthorByPenName(String penName);
}
