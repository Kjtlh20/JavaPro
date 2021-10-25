package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class BankOperations {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank");
    private EntityManager em = emf.createEntityManager();
    private Account accountFrom;
    private Account accountTo;
    private Double amount;

    public BankOperations() {
    }

    public BankOperations(Account accountTo, Double amount) {
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public BankOperations(Account accountFrom, Account accountTo, Double amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void topUp() {
        em.getTransaction().begin();
        try {

            Transaction transaction = new Transaction(new Date(), accountFrom, accountTo, amount);
            AbstractDAO<Transaction> transactionDAO = new TransactionDAOImpl();
            transactionDAO.add(transaction);
            AbstractDAO<Account> accountDAO = new AccountDAOImpl();

            Account accountTo = transaction.getAccountTo();
            if (accountTo != null) {
                Double currAmount = accountTo.getAmount();
                accountTo.setAmount(currAmount + this.amount);
                accountDAO.update(accountTo);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public void transferBetweenAccounts() {
        em.getTransaction().begin();
        try {

            Transaction transaction = new Transaction(new Date(), accountFrom, accountTo, amount);
            AbstractDAO<Transaction> transactionDAO = new TransactionDAOImpl();
            transactionDAO.add(transaction);
            AbstractDAO<Account> accountDAO = new AccountDAOImpl();

            Account accountFrom = transaction.getAccountFrom();
            if (accountFrom != null) {
                Double currAmount = accountFrom.getAmount();
                accountFrom.setAmount(currAmount - this.amount);
                accountDAO.update(accountFrom);
            }

            Account accountTo = transaction.getAccountTo();
            if (accountTo != null) {
                Double currAmount = accountTo.getAmount();
                accountTo.setAmount(currAmount + this.amount);
                accountDAO.update(accountTo);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public Double getAmountUser(User user) {
        Double result = 0.0;
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.user = :user", Account.class);
        query.setParameter("user", user);
        List<Account> accounts = query.getResultList();
        for (Account account : accounts) {
            if (account.getCurrency().getName().equals("UAH")) {
                result = result + account.getAmount();
            }
        }
        return result;
    }
}
