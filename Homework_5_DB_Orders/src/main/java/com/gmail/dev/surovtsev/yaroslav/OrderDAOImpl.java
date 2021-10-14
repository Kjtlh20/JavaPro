package com.gmail.dev.surovtsev.yaroslav;

import java.sql.Connection;

public class OrderDAOImpl extends AbstractDAO<Orders> {
    public OrderDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
