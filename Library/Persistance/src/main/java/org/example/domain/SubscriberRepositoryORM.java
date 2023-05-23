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
public class SubscriberRepositoryORM implements ISubscriberRepository{
    static SessionFactory sessionFactory;

    public SubscriberRepositoryORM() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Exceptie la constructor ORM Subscriber: " + ex.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public Subscriber add(Subscriber entity) {
        return null;
    }

    @Override
    public Subscriber remove(Subscriber entity) {
        return null;
    }

    @Override
    public Subscriber update(Subscriber oldEntity, Subscriber newEntity) {
        return null;
    }

    @Override
    public Iterable<Subscriber> getEntities() {
        Iterable<Subscriber> subscribers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                subscribers = session.createQuery("from Subscriber", Subscriber.class).getResultList();
            } catch (RuntimeException ex) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return subscribers;
    }

    @Override
    public Subscriber search(String s) {
        return null;
    }
}
