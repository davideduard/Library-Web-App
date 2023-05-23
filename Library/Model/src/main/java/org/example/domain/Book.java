package org.example.domain;

import java.util.Objects;

public class Book extends Entity<Integer>{
    private String title;
    private String author;
    private Integer reserved;

    public Book() {

    }

    public Book(Integer integer, String title, String author, Integer reserved) {
        super(integer);
        this.title = title;
        this.author = author;
        this.reserved = reserved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(reserved, book.reserved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, reserved);
    }
}
