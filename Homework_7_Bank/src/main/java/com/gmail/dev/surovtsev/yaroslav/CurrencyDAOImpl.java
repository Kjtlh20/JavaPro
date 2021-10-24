package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class CurrencyDAOImpl implements CurrencyDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public CurrencyDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("Bank");
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<Currency> get(Long id) {
        return Optional.ofNullable(em.find(Currency.class, id));
    }

    @Override
    public List<Currency> getAll() {
        TypedQuery<Currency> query = em.createQuery("SELECT c FROM Currency c", Currency.class);
        List<Currency> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void add(Currency currency) {
        em.getTransaction().begin();
        try {
            em.persist(currency);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Currency currency) {
        em.getTransaction().begin();
        try {
            em.merge(currency);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Currency currency) {
        Currency currencyFromDB = em.getReference(Currency.class, currency.getId());
        if (currency == null) {
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(currency);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
