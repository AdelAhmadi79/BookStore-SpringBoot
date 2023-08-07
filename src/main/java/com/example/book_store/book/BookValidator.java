package com.example.book_store.book;

import org.springframework.beans.factory.annotation.Autowired;

public class BookValidator {
    
    public boolean validationOfTitleOrPublisher(BookRequest bookRequest){
        return (bookRequest.getAuthor() != null || bookRequest.getPublisher() != null);

    }
}
