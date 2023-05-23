package org.example.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BookRepositoryORM implements IBookRepository {
    static SessionFactory sessionFactory;

    public BookRepositoryORM() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Exceptie la constructor ORM Book: " + ex.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public Book add(Book entity) {
        return null;
    }

    @Override
    public Book remove(Book entity) {
        return null;
    }

    @Override
    public Book update(Book oldEntity, Book newEntity) {
        return null;
    }

    @Override
    public Iterable<Book> getEntities() {
        Iterable<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                books = session.createQuery("from Book", Book.class).getResultList();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return books;
    }

    @Override
    public Book search(Integer id) {
        Book book = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                book = session.createQuery("from Book where id = ?1", Book.class)
                        .setParameter(1, id)
                        .setMaxResults(1)
                        .uniqueResult();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return book;
    }

    @Override
    public Book getBookByTitle(String title) {
        Book book = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                book = session.createQuery("from Book where title = ?1", Book.class)
                        .setParameter(1, title)
                        .setMaxResults(1)
                        .uniqueResult();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return book;
    }

    @Override
    public Iterable<Book> getAvailableBooks() {
        Iterable<Book> books = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                books = session.createQuery("from Book where reserved = 0", Book.class).getResultList();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return books;
    }

    @Override
    public Book updateReservedStatus(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                if (book.getReserved() == 0) {
                    book.setReserved(1);
                } else {
                    book.setReserved(0);
                }

                Book updatedEntity = (Book) session.merge(book);
                tx.commit();
                return updatedEntity;
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
                throw ex;
            }
        }
    }
}

