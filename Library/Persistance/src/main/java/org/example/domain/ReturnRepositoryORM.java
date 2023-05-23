package org.example.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnRepositoryORM implements IReturnRepository{
    static SessionFactory sessionFactory;

    public ReturnRepositoryORM() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Exceptie la constructor ORM Return: " + ex.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public Return add(Return entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Return attachedEntity = (Return) session.merge(entity);
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
    public Return remove(Return entity) {
        return null;
    }

    @Override
    public Return update(Return oldEntity, Return newEntity) {
        return null;
    }

    @Override
    public Iterable<Return> getEntities() {
        return null;
    }

    @Override
    public Return search(Integer integer) {
        return null;
    }
}
