package com.gmail.dev.surovtsev.yaroslav;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ApartmentDAOImpl implements ApartmentDAO {

    private final Connection conn;
    private static final String TABLE_NAME = "Apartments";

    public ApartmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createTable() {
        Field[] fields = Apartment.class.getDeclaredFields();
        Field id = getPrimaryKeyField(fields);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(");

        sql.append(id.getName())
                .append(" ")
                .append(" INT AUTO_INCREMENT PRIMARY KEY,");

        for (Field f : fields) {
            if (f != id) {
                f.setAccessible(true);

                sql.append(f.getName()).append(" ");

                if (f.getType() == int.class) {
                    sql.append("INT,");
                } else if (f.getType() == double.class) {
                    sql.append("DOUBLE,");
                } else if (f.getType() == String.class) {
                    sql.append("VARCHAR(100),");
                } else
                    throw new RuntimeException("Wrong type");
            }
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        try {
            try (Statement st = conn.createStatement()) {
                st.execute("DROP TABLE IF EXISTS " + TABLE_NAME);
                st.execute(sql.toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void add(Apartment apartment) {
        try {
            Field[] fields = Apartment.class.getDeclaredFields();
            Field id = getPrimaryKeyField(fields);

            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Field f : fields) {
                if (f != id) {
                    f.setAccessible(true);

                    names.append(f.getName()).append(',');
                    values.append('"').append(f.get(apartment)).append("\",");
                }
            }

            names.deleteCharAt(names.length() - 1);
            values.deleteCharAt(values.length() - 1);

            String sql = "INSERT INTO " + TABLE_NAME + "(" + names.toString() +
                    ") VALUES(" + values.toString() + ")";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Apartment> getAll() {
        List<Apartment> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + TABLE_NAME)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        Apartment ap = new Apartment();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);
                            Field field = Apartment.class.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(ap, rs.getObject(columnName));
                        }

                        res.add(ap);
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Apartment> getAll(String[] conditions) {
        List<Apartment> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                StringBuilder where = new StringBuilder();
                if (conditions.length == 3) {
                    where.append(" WHERE ");
                    where.append(conditions[0]).append(conditions[1]).append(conditions[2]);
                }
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + TABLE_NAME + where)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        Apartment ap = new Apartment();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);
                            Field field = Apartment.class.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(ap, rs.getObject(columnName));
                        }

                        res.add(ap);
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Apartment> getAll(Predicate<Apartment> pred) {
        List<Apartment> list = getAll();
        list.removeIf(pred.negate());
        return list;
    }

    private Field getPrimaryKeyField(Field[] fields) {
        Field result = null;

        for (Field f : fields) {
            if (f.isAnnotationPresent(Id.class)) {
                result = f;
                result.setAccessible(true);
                break;
            }
        }

        if (result == null)
            throw new RuntimeException("No Id field found");

        return result;
    }
}
