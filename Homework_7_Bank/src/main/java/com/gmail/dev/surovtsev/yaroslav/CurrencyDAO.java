package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.Optional;

public interface CurrencyDAO {
    Optional<Currency> get(Long id);

    List<Currency> getAll();

    void add(Currency currency);

    void update(Currency currency);

    void delete(Currency currency);
}
