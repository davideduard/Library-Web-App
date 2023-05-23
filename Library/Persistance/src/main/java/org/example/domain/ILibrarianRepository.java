package org.example.domain;

public interface ILibrarianRepository extends IRepository<String, Librarian> {
    Librarian getLibrarianByUsernameAndPassword(String username, String password);
}
