package com.example.book_store.book;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ReserveBookRequest {

    private Set<Long> booksIdList ;
}
