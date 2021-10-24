package com.gmail.dev.surovtsev.yaroslav;

import java.util.List;
import java.util.Optional;

public interface AccountDAO {
    Optional<Account> get(Long id);

    List<Account> getAll();

    void add(Account account);

    void update(Account account);

    void delete(Account account);
}
