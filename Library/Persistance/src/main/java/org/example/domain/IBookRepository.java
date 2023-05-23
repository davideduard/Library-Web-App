package org.example.domain;

public interface IBookRepository extends IRepository<Integer, Book>{
    Book getBookByTitle(String title);
    Iterable<Book> getAvailableBooks();

    Book updateReservedStatus(Book book);
}
