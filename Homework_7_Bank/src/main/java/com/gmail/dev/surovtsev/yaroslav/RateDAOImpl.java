package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class RateDAOImpl implements AbstractDAO<Rate> {
    private EntityManagerFactory emf;
    private EntityManager em;

    public RateDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("Bank");
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<Rate> get(Long id) {
        return Optional.ofNullable(em.find(Rate.class, id));
    }

    @Override
    public List<Rate> getAll() {
        TypedQuery<Rate> query = em.createQuery("SELECT r FROM Rate r", Rate.class);
        List<Rate> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void add(Rate rate) {
        em.getTransaction().begin();
        try {
            em.persist(rate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Rate rate) {
        em.getTransaction().begin();
        try {
            em.merge(rate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Rate rate) {
        Rate rateFromDB = em.getReference(Rate.class, rate.getId());
        if (rateFromDB == null) {
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(rate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
