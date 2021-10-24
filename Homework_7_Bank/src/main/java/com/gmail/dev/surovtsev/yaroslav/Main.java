package com.gmail.dev.surovtsev.yaroslav;

public class Main {

    public static void main(String[] args) {
        Currency currency1 = new Currency("UAH");
        Currency currency2 = new Currency("USD");
        Currency currency3 = new Currency("EUR");
        CurrencyDAO currencyDAO = new CurrencyDAOImpl();
        currencyDAO.add(currency1);
        currencyDAO.add(currency2);
        currencyDAO.add(currency3);

        User user1 = new User("User1");
        UserDAO userDAO = new UserDAOImpl();
        userDAO.add(user1);

        Account account1 = new Account("UAH", currency1, user1);
        Account account2 = new Account("USD", currency2, user1);

        user1.addAccount(account1);
        user1.addAccount(account2);
    }
}
