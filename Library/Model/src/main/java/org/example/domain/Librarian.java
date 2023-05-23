package org.example.domain;

import java.util.Objects;

public class Librarian extends Entity<String>{
    private String password;
    private String phoneNumber;

    public Librarian() {

    }

    public Librarian(String s, String password, String phoneNumber) {
        super(s);
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Librarian librarian = (Librarian) o;
        return Objects.equals(password, librarian.password) && Objects.equals(phoneNumber, librarian.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password, phoneNumber);
    }
}
