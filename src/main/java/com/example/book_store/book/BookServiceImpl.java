package com.example.book_store.book;

import com.example.book_store.author.AuthorRepository;
import com.example.book_store.author.AuthorSrvForGetBooks;
import com.example.book_store.customer.CustomerSrv;
import com.example.book_store.domain.Author;
import com.example.book_store.domain.Book;
import com.example.book_store.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepo;

    private final BookValidator bookValidator;

    private final AuthorRepository authorRepo;


    private BookSrv bookToBookSrv(Book book) {
        List<String> penNames = book.getAuthors().stream()
                .map(Author::getPenName)
                .collect(Collectors.toList());
        return BookSrv.builder()
                .id(book.getId())
                .publisher(book.getPublisher())
                .title(book.getTitle())
                .authorsPenName(penNames)
                .build();
    }

    private Book bookReqToBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .publisher(bookRequest.getPublisher())
                .isbn(bookRequest.getIsbn())
                .build();
    }

    private AuthorSrvForGetBooks authorToAuthorSrvForBooks(Author author) {
        return AuthorSrvForGetBooks.builder()
                .authorName(author.getAuthorName())
                .authorLastName(author.getAuthorLastName())
                .penName(author.getPenName())
//                .book(author.getBook())
                .build();
    }

    private CustomerSrv CustomerToCustomerSrv(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerSrv.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .build();

    }

    @Override
    public BookSrv getSingleBookSrv(Long id) {
        Optional<Book> book = bookRepo.findById(id);

        if (!book.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book NOT FOUND!");
        return bookToBookSrv(book.get());
    }


    @Override
    public List<BookSrv> getBookSrvs() {
        List<Book> bookList = bookRepo.findAll();
        List<BookSrv> bookSrvList = new ArrayList<>();
        for (Book book : bookList) {
            bookSrvList.add(bookToBookSrv(book));
        }
        return bookSrvList;
    }

    @Override
    public List<BookSrvWithoutCustomer> getAllBooksWithoutCustomer() {
//        List<Book> books = bookRepo.findAll();
        List<Book> books = bookRepo.getAllBooks();
        List<BookSrvWithoutCustomer> bookSrvWithoutCustomerList = new ArrayList<>();
        for (Book book : books) {

            List<AuthorSrvForGetBooks> authorSrvForGetBooksList = new ArrayList<>();
            for (Author author : book.getAuthors()
            ) {
                authorSrvForGetBooksList.add(authorToAuthorSrvForBooks(author));
            }
            bookSrvWithoutCustomerList.add(BookSrvWithoutCustomer.builder()
                    .id(book.getId())
                    .publisher(book.getPublisher())
                    .title(book.getTitle())
                    .customerSrv(CustomerToCustomerSrv(book.getCustomer()))
                    .authorSrvForGetBooksSet(authorSrvForGetBooksList)
                    .build());
        }
        return bookSrvWithoutCustomerList;
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent()) {
            throw new RuntimeException("Book with ID " + "could not be found.");
        }
        bookRepo.delete(book.get());
    }


    @Override
    public BookSrv saveBook(BookRequest bookReq) {
        bookValidator.validateForCreate(bookReq);
        Book book = bookReqToBook(bookReq);
        Set<Author> authors = new HashSet<>();

        for (Long authorId : bookReq.getAuthorIdList()) {
            Author author = authorRepo.findById(authorId).orElseThrow(() -> new RuntimeException("Author with ID \"" + authorId + "\" could not be found."));
            authors.add(author);
        }
        book.setAuthors(authors);
        bookRepo.save(book);
        return bookToBookSrv(book);
    }


    @Override
    public BookSrv updateBook(Long id, BookRequest bookRequest) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        Book book = bookOptional.orElseThrow(() -> new RuntimeException("Book with ID " + id + "could not be found."));
        bookValidator.validateForUpdate(book, bookRequest);

        if (bookRequest.getTitle() != null)
            book.setTitle(bookRequest.getTitle());

        if (bookRequest.getIsbn() != null)
            book.setIsbn(bookRequest.getIsbn());

        if (bookRequest.getPublisher() != null)
            book.setPublisher(bookRequest.getPublisher());

        for (Long authorId : bookRequest.getAuthorIdList()) {
            if (authorId != null) {
                book.getAuthors().add(authorRepo.findById(authorId).orElseThrow(() -> new RuntimeException("Author with ID " + authorId + " could not be found.")));
            }
        }

        bookRepo.saveAndFlush(book);
        return bookToBookSrv(book);
    }

    @Scheduled(cron = "0 35 12 * * *")
    public void checkReturnDeadLines() {
        List<Book> allBooks = bookRepo.findAll();
        for (Book book : allBooks) {
            if (LocalDate.now().isAfter(book.getReturnDeadLine())){
                book.setIsDeadlineExpired(true);
                bookRepo.saveAndFlush(book);
                System.out.println("The deadline of book with id:" + book.getId() +" has expired");
            }
        }
    }

}
