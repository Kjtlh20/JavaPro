package com.gmail.dev.surovtsev.yaroslav;

import java.util.Date;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Currency currency1 = new Currency("UAH");
        Currency currency2 = new Currency("USD");
        Currency currency3 = new Currency("EUR");
        AbstractDAO<Currency> currencyDAO = new CurrencyDAOImpl();
        currencyDAO.add(currency1);
        currencyDAO.add(currency2);
        currencyDAO.add(currency3);

        User user1 = new User("User1");
        User user2 = new User("User2");
        AbstractDAO<User> userDAO = new UserDAOImpl();
        userDAO.add(user1);
        userDAO.add(user2);
        userDAO.delete(user2);

        Account account1 = new Account(currency1.getName() + " account", currency1, user1, 1000.0);
        Account account2 = new Account(currency2.getName() + " account", currency2, user1, 1000.0);
        Account account3 = new Account(currency1.getName() + " account", currency1, user1, 1000.0);
        AbstractDAO<Account> accountDAO = new AccountDAOImpl();
        accountDAO.add(account1);
        accountDAO.add(account2);
        accountDAO.add(account3);

        user1.addAccount(account1);
        user1.addAccount(account2);
        userDAO.update(user1);

        Rate rate1 = new Rate(new Date(), currency2, currency1, 26.50);
        Rate rate2 = new Rate(new Date(), currency3, currency1, 31.50);
        AbstractDAO<Rate> rateDAO = new RateDAOImpl();
        rateDAO.add(rate1);
        rateDAO.add(rate2);

        Transaction transaction1 = new Transaction(new Date(), account1, account3, 100.00);
        Transaction transaction2 = new Transaction(new Date(), account1, account3, 150.00);
        AbstractDAO<Transaction> transactionDAO = new TransactionDAOImpl();
        transactionDAO.add(transaction1);
        transactionDAO.add(transaction2);

        BankOperations bankOperations = new BankOperations(account1, 100.0);
        bankOperations.topUp();
        System.out.println("account1 Amount: " + account1.getAmount());

        bankOperations = new BankOperations(account3, account1, 100.0);
        bankOperations.transferBetweenAccounts();
        System.out.println("account1 Amount: " + account1.getAmount());
        System.out.println("account3 Amount: " + account3.getAmount());

        bankOperations = new BankOperations();
        Double amountUser = bankOperations.getAmountUser(user1);
        System.out.println("amount user1: " + amountUser);
    }
}
