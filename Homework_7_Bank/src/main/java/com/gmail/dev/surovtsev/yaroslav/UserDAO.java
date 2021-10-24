package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> get(Long id);

    List<User> getAll();

    void add(User user);

    void update(User user);

    void delete(User user);
}
