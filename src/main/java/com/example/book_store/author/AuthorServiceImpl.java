package com.example.book_store.author;


import com.example.book_store.address.AddressRepository;
import com.example.book_store.address.AddressRequest;
import com.example.book_store.domain.Address;
import com.example.book_store.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepo;
    private final AddressRepository addressRepository;

    private AuthorSrv authorToAuthorSrv (Author author){
        return AuthorSrv.builder()
                .id(author.getId())
                .authorName(author.getAuthorName())
                .authorLName(author.getAuthorLName())
                .penName(author.getPenName())
                .build();
    }

    private Address addressReqToAddress(AddressRequest addressRequest){
        return Address.builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .build();
    }

    private Author authorReqToAuthor (AuthorRequest authorRequest){
        Address address = addressReqToAddress(authorRequest.getAddress());
        return Author.builder()
                .address(address)
                .authorName(authorRequest.getAuthorName())
                .authorLName(authorRequest.getAuthorLName())
                .penName(authorRequest.getPenName())
                .build();
    }
    @Override
    public AuthorSrv saveAuthor(AuthorRequest authorRequest) {
        Author author = authorReqToAuthor(authorRequest);
        addressRepository.save(author.getAddress());
        return  authorToAuthorSrv(authorRepo.save(author));
    }
}
