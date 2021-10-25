package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.Optional;

public interface AbstractDAO<T> {
    Optional<T> get(Long id);

    List<T> getAll();

    void add(T t);

    void update(T t);

    void delete(T t);
}
