package com.gmail.dev.surovtsev.yaroslav;

import java.sql.Connection;

public class ProductDAOImpl extends AbstractDAO<Product> {
    public ProductDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
