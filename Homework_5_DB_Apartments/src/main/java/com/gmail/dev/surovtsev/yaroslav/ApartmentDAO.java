package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.function.Predicate;

public interface ApartmentDAO {
    void createTable();

    void add(Apartment apartment);

    List<Apartment> getAll();

    List<Apartment> getAll(String[] conditions);

    List<Apartment> getAll(Predicate<Apartment> pred);
}
