package com.gmail.dev.surovtsev.yaroslav;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/Homework_5_DB_Apartments?serverTimezone=Europe/Kiev";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "11111111";

    private static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initDB();
                while (true) {
                    System.out.println("1: add random apartments");
                    System.out.println("2: view apartments");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            insertRandomApartments();
                            break;
                        case "2":
                            viewApartments(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }

    }

    private static void initDB() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Apartments");
            st.execute("CREATE TABLE Apartments " +
                    "(id INT NOT NULL  AUTO_INCREMENT PRIMARY KEY, " +
                    "district VARCHAR(100) NOT NULL, " +
                    "address VARCHAR(100) NOT NULL, " +
                    "area DOUBLE, " +
                    "numberOfRooms INT, " +
                    "price DOUBLE)");
        }
    }

    private static void insertRandomApartments() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Apartments (district, address, area, " +
                    "numberOfRooms, price) VALUES(?, ?, ?, ?, ?)")) {
                try {
                    ps.setString(1, "district" + i);
                    ps.setString(2, "address" + i);
                    ps.setDouble(3, 50.50 + i);
                    ps.setInt(4, 1 + i);
                    ps.setDouble(5, 1_500_000 + i);
                    ps.executeUpdate();
                } finally {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void viewApartments(Scanner sc) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments WHERE area > 55.0")) {
            try {
                // table of data representing a database result set,
                ResultSet rs = ps.executeQuery();

                try {
                    // can be used to get information about the types and properties of the columns in a ResultSet object
                    ResultSetMetaData md = rs.getMetaData();

                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.print(md.getColumnName(i) + "\t\t");
                    System.out.println();

                    while (rs.next()) {
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }
                } finally {
                    rs.close(); // rs can't be null according to the docs
                }
            } finally {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
