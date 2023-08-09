package com.example.book_store.book;


import com.example.book_store.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
  boolean existsBookByTitle(String string);
}
