package com.gmail.dev.surovtsev.yaroslav;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {

            ApartmentDAO apartmentDAO = new ApartmentDAOImpl(conn);
            apartmentDAO.createTable();

            for (int i = 0; i < 10; i++) {
                Apartment ap = new Apartment();
                ap.setDistrict("District" + i);
                ap.setAddress("Address" + i);
                ap.setArea(50 + i);
                ap.setNumberOfRooms(i + 1);
                ap.setPrice(50_000.00 + i);
                apartmentDAO.add(ap);
            }

            List<Apartment> ap_list = apartmentDAO.getAll();
            ap_list.forEach(System.out::println);
            System.out.println();

            Predicate<Apartment> filter = a -> a.getArea() < 55;
            List<Apartment> ap_list2 = apartmentDAO.getAll(filter);
            ap_list2.forEach(System.out::println);
            System.out.println();

            String[] conditions = new String[3];
            conditions[0] = "area";
            conditions[1] = "<";
            conditions[2] = "55";
            List<Apartment> ap_list3 = apartmentDAO.getAll(conditions);
            ap_list3.forEach(System.out::println);
        }
    }
}
