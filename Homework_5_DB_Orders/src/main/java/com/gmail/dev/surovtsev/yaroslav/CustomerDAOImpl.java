package com.gmail.dev.surovtsev.yaroslav;

import java.sql.Connection;

public class CustomerDAOImpl extends AbstractDAO<Customer> {
    public CustomerDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
