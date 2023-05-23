package org.example.domain;

import java.util.Objects;

public class Return extends Entity<Integer>{
    private String librarian_id;
    private Integer book_id;
    private String subscriber_id;

    public Return() {
    }

    public Return(Integer integer, String librarian_id, Integer book_id, String subscriber_id) {
        super(integer);
        this.librarian_id = librarian_id;
        this.book_id = book_id;
        this.subscriber_id = subscriber_id;
    }

    public String getLibrarian_id() {
        return librarian_id;
    }

    public void setLibrarian_id(String librarian_id) {
        this.librarian_id = librarian_id;
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
        Return aReturn = (Return) o;
        return Objects.equals(librarian_id, aReturn.librarian_id) && Objects.equals(book_id, aReturn.book_id) && Objects.equals(subscriber_id, aReturn.subscriber_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), librarian_id, book_id, subscriber_id);
    }
}
