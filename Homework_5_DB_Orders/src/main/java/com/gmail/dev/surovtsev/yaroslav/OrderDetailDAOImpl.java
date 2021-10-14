package com.gmail.dev.surovtsev.yaroslav;

import java.sql.Connection;

public class OrderDetailDAOImpl extends AbstractDAO<OrderDetail> {
    public OrderDetailDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
