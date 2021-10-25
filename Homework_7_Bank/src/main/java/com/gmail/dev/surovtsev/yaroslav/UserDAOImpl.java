package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements AbstractDAO<User> {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("Bank");
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void add(User user) {
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(User user) {
        em.getTransaction().begin();
        try {
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(User user) {
        User userFromDB = em.getReference(User.class, user.getId());
        if (userFromDB == null) {
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
