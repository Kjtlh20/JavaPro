package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.Optional;

public interface MenuDAO {
    Optional<Menu> get(long id);

    List<Menu> getAll();

    List<Menu> getAll(double priceFrom, double priceTo);

    List<Menu> getAll(boolean isDiscountAvailable);

    List<Menu> getAll(double weightTo);

    void add(Menu menu);

    void update(Menu menu);

    void delete(Menu menu);
}
