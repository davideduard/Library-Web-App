package org.example.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class LibrarianRepositoryORM implements ILibrarianRepository {
    static SessionFactory sessionFactory;

    public LibrarianRepositoryORM() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Exceptie la constructor ORM Librarian: " + ex.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    @Override
    public Librarian getLibrarianByUsernameAndPassword(String username, String password) {
        Librarian librarian = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                librarian = session.createQuery("from Librarian where id = ?1 and password = ?2", Librarian.class)
                        .setParameter(1, username)
                        .setParameter(2, password)
                        .setMaxResults(1)
                        .uniqueResult();

            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return librarian;
    }

    @Override
    public Librarian add(Librarian entity) {
        return null;
    }

    @Override
    public Librarian remove(Librarian entity) {
        return null;
    }

    @Override
    public Librarian update(Librarian oldEntity, Librarian newEntity) {
        return null;
    }

    @Override
    public Iterable<Librarian> getEntities() {
        return null;
    }

    @Override
    public Librarian search(String s) {
        return null;
    }
}
