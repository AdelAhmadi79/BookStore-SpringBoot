package com.example.book_store.author;


import com.example.book_store.address.AddressRequest;
import com.example.book_store.domain.Address;
import com.example.book_store.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepo;

    private AuthorSrv authorToAuthorSrv (Author author){
        return AuthorSrv.builder()
                .authorId(author.getId())
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
        Author author = Author.builder()
                .address(address)
                .authorName(authorRequest.getAuthorName())
                .authorLName(authorRequest.getAuthorLName())
                .penName(authorRequest.getPenName())
                .build();
        return author;
    }
    @Override
    public AuthorSrv saveAuthor(AuthorRequest authorRequest) {
        return  authorToAuthorSrv(authorRepo.save(authorReqToAuthor(authorRequest)));
    }
}
