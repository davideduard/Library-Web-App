package org.example.domain;

public interface IBorrowRepository extends IRepository<Integer, Borrow>{
    Iterable<Borrow> getAllBorowsForSubscriber(String subscriber_id);
    Borrow deleteByBookAndSubscriber(Integer book_id, String subscriber_id);
}
