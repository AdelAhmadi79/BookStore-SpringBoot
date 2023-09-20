package com.example.book_store.mapper;

import com.example.book_store.address.AddressRequest;
import com.example.book_store.domain.model.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    Address addressReqToAddress(AddressRequest addressRequest);
}
