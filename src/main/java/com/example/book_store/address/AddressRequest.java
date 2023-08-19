package com.example.book_store.address;


import com.example.book_store.domain.Author;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AddressRequest {

    private String street;


    private String city;


}
