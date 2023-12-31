package com.example.book_store.author;


import com.example.book_store.address.AddressRepository;
import com.example.book_store.address.AddressRequest;
import com.example.book_store.domain.model.Address;
import com.example.book_store.domain.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;
    private final AddressRepository addressRepository;

    private final AuthorValidator authorValidator;

    private AuthorSrv authorToAuthorSrv(Author author) {
        return AuthorSrv.builder()
                .id(author.getId())
                .authorName(author.getAuthorName())
                .authorLastName(author.getAuthorLastName())
                .penName(author.getPenName())
                .build();
    }

    private Address addressReqToAddress(AddressRequest addressRequest) {
        return Address.builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .build();
    }

    private Author authorReqToAuthor(AuthorRequest authorRequest) {
        Address address = addressReqToAddress(authorRequest.getAddressRequest());
        return Author.builder()
                .address(address)
                .authorName(authorRequest.getAuthorName())
                .authorLastName(authorRequest.getAuthorLastName())
                .penName(authorRequest.getPenName())
                .build();
    }
    @Override
    public AuthorSrv saveAuthor(AuthorRequest authorRequest) {
        authorValidator.validateForCreate(authorRequest);
        Author author = authorReqToAuthor(authorRequest);
        addressRepository.save(author.getAddress());
        return authorToAuthorSrv(authorRepo.save(author));
    }

    @Override
    public AuthorSrv updateAuthor(Long id, AuthorRequest authorRequest) {
        Optional<Author> authorOptional = authorRepo.findById(id);
        Author author = authorOptional.orElseThrow(() -> new RuntimeException("author with ID " + id + "could not be found."));
        authorValidator.validateForUpdate(author, authorRequest);

        if (authorRequest.getAddressRequest() != null)
            author.setAddress(addressReqToAddress(authorRequest.getAddressRequest()));

        if (authorRequest.getAuthorLastName() != null)
            author.setAuthorLastName(authorRequest.getAuthorLastName());

        if (authorRequest.getAuthorName() != null)
            author.setAuthorName(authorRequest.getAuthorName());

        if (authorRequest.getPenName() != null)
            author.setPenName(authorRequest.getPenName());


        authorRepo.saveAndFlush(author);
        return authorToAuthorSrv(author);
    }

    @Override
    public Author getSingleAuthor(Long id) {
        return authorRepo.findById(id).orElseThrow(()-> new RuntimeException("Author with id \"" + id + "\" couldn't find."));
    }

    @Override
    public AuthorSrvWithoutBooks getSingleAuthorWithoutBooks(Long id) {
            Author author = authorRepo.findById(id).orElseThrow(
                    ()-> new RuntimeException("Author with id \"" + id + "\" couldn't find."));

            return AuthorSrvWithoutBooks.builder()
                    .id(author.getId())
                    .authorName(author.getAuthorName())
                    .authorLastName(author.getAuthorLastName())
                    .penName(author.getPenName())
                    .address(author.getAddress())
                    .build();

    }


}
