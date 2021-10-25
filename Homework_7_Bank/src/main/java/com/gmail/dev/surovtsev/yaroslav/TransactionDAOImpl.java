package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class TransactionDAOImpl implements AbstractDAO<Transaction> {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TransactionDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("Bank");
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<Transaction> get(Long id) {
        return Optional.ofNullable(em.find(Transaction.class, id));
    }

    @Override
    public List<Transaction> getAll() {
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t", Transaction.class);
        List<Transaction> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void add(Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Transaction transaction) {
        em.getTransaction().begin();
        try {
            em.merge(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Transaction transaction) {
        Transaction transactionFromDB = em.getReference(Transaction.class, transaction.getId());
        if (transactionFromDB == null) {
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(transaction);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
