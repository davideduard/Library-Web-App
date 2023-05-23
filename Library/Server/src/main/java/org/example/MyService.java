package org.example;

import org.example.domain.*;
import org.springframework.stereotype.Service;

@Service
public class MyService implements IService{
    private final LibrarianRepositoryORM librarianRepository;
    private final BookRepositoryORM bookRepository;
    private final SubscriberRepositoryORM subscriberRepository;
    private final BorrowRepositoryORM borrowRepository;
    private final ReturnRepositoryORM returnRepository;

    public MyService(LibrarianRepositoryORM librarianRepository, BookRepositoryORM bookRepository, SubscriberRepositoryORM subscriberRepository
                        ,BorrowRepositoryORM borrowRepository, ReturnRepositoryORM returnRepository){
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.subscriberRepository = subscriberRepository;
        this.borrowRepository = borrowRepository;
        this.returnRepository = returnRepository;
    }

    @Override
    public synchronized void login(String username, String password) throws Exception {
        Librarian librarian = librarianRepository.getLibrarianByUsernameAndPassword(username, password);
        if (librarian == null) {
            throw new Exception("Invalid Login");
        }
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return bookRepository.getEntities();
    }

    @Override
    public Book searchBookById(Integer id) {
        return bookRepository.search(id);
    }

    @Override
    public Book searchUnreservedBookByTitle(String title) {
        Book book = bookRepository.getBookByTitle(title);
        if (book.getReserved() == 0){
            return book;
        }
        return null;
    }

    @Override
    public Borrow addNewBorrow(Borrow borrow) {
        return borrowRepository.add(borrow);
    }

    @Override
    public Borrow removeBorrowByBookAndSubscriber(Integer book_id, String subscriber_id) {
        return borrowRepository.deleteByBookAndSubscriber(book_id, subscriber_id);
    }

    @Override
    public Iterable<Book> getAllAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    @Override
    public Book updateReservedStatus(Book book) {
        return bookRepository.updateReservedStatus(book);
    }

    @Override
    public Iterable<Subscriber> getAllSubscribers() {
        return subscriberRepository.getEntities();
    }

    @Override
    public Iterable<Borrow> getAllBorrows() {
        return borrowRepository.getEntities();
    }

    @Override
    public Iterable<Borrow> getAllBorrowsForSubscriber(String subscriber_id) {
        return borrowRepository.getAllBorowsForSubscriber(subscriber_id);
    }

    @Override
    public Return addNewReturn(Return entity) {
        return returnRepository.add(entity);
    }
}
