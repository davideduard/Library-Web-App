package org.example.domain;

import java.util.Objects;

public class Borrow extends Entity<Integer>{
    private Integer book_id;
    private String subscriber_id;

    public Borrow() {
    }

    public Borrow(Integer integer, Integer book_id, String subscriber_id) {
        super(integer);
        this.book_id = book_id;
        this.subscriber_id = subscriber_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getSubscriber_id() {
        return subscriber_id;
    }

    public void setSubscriber_id(String subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(book_id, borrow.book_id) && Objects.equals(subscriber_id, borrow.subscriber_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), book_id, subscriber_id);
    }
}
