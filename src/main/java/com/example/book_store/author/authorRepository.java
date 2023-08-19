package com.example.book_store.author;

import com.example.book_store.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface authorRepository extends JpaRepository <Author,Long> {
}
