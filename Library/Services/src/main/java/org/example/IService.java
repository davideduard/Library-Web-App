package org.example;

import org.example.domain.Book;
import org.example.domain.Borrow;
import org.example.domain.Return;
import org.example.domain.Subscriber;

public interface IService {
    void login(String username, String password) throws Exception;
    Iterable<Book> getAllBooks();
    Book searchBookById(Integer id);
    Book searchUnreservedBookByTitle(String title);
    Borrow addNewBorrow(Borrow borrow);
    Borrow removeBorrowByBookAndSubscriber(Integer book_id, String subscriber_id);
    Iterable<Book> getAllAvailableBooks();
    Book updateReservedStatus(Book book);
    Iterable<Subscriber> getAllSubscribers();
    Iterable<Borrow> getAllBorrows();
    Iterable<Borrow> getAllBorrowsForSubscriber(String subscriber_id);
    Return addNewReturn(Return entity);
}
