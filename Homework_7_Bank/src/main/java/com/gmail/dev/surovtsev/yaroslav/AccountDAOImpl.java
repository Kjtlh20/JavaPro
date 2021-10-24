package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public AccountDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("Bank");
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<Account> get(Long id) {
        return Optional.ofNullable(em.find(Account.class, id));
    }

    @Override
    public List<Account> getAll() {
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a", Account.class);
        List<Account> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void add(Account account) {
        em.getTransaction().begin();
        try {
            em.persist(account);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Account account) {
        em.getTransaction().begin();
        try {
            em.merge(account);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Account account) {
        Account accountFromDB = em.getReference(Account.class, account.getId());
        if (accountFromDB == null) {
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(account);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
