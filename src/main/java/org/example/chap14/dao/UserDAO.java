package org.example.chap14.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.example.chap14.entity.User;
import org.example.chap14.util.JPAUtil;

public class UserDAO {

    public User findByEmail(String email) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT u FROM User u " +
                            "WHERE u.email = :email";

            TypedQuery<User> query =
                    em.createQuery(
                            jpql,
                            User.class
                    );

            query.setParameter("email", email);

            try {
                return query.getSingleResult();
            } catch (Exception e) {
                return null;
            }

        } finally {
            em.close();
        }
    }

    public void insert(User user) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    public void update(User user) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(user);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }
}