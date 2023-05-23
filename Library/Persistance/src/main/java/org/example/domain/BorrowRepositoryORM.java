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
public class BorrowRepositoryORM implements IBorrowRepository{
    static SessionFactory sessionFactory;

    public BorrowRepositoryORM() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Exceptie la constructor ORM Borrow: " + ex.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public Borrow add(Borrow entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Borrow attachedEntity = (Borrow) session.merge(entity);
                tx.commit();
                return attachedEntity;
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
                throw ex;
            }
        }
    }

    @Override
    public Borrow remove(Borrow entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.remove(entity);
                tx.commit();
                return entity;
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
                throw ex;
            }
        }
    }


    @Override
    public Borrow update(Borrow oldEntity, Borrow newEntity) {
        return null;
    }

    @Override
    public Iterable<Borrow> getEntities() {
        Iterable<Borrow> borrows = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                borrows = session.createQuery("from Borrow", Borrow.class).getResultList();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return borrows;
    }

    @Override
    public Borrow search(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Borrow> getAllBorowsForSubscriber(String subscriber_id) {
        Iterable<Borrow> borrows = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                borrows = session.createQuery("from Borrow where subscriber_id = ?1", Borrow.class)
                        .setParameter(1, subscriber_id).getResultList();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return borrows;
    }

    @Override
    public Borrow deleteByBookAndSubscriber(Integer bookId, String subscriberId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Borrow borrow = session.createQuery("FROM Borrow WHERE book_id = ?1 AND subscriber_id = ?2", Borrow.class)
                        .setParameter(1, bookId)
                        .setParameter(2, subscriberId)
                        .setMaxResults(1)
                        .uniqueResult();

                if (borrow != null) {
                    session.remove(borrow);
                    tx.commit();
                    return borrow;
                } else {
                    throw new IllegalArgumentException("Borrow not found for the specified book ID and subscriber ID.");
                }
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
                throw ex;
            }
        }
    }

}
