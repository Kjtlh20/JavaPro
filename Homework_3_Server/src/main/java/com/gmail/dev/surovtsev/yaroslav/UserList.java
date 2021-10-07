package com.gmail.dev.surovtsev.yaroslav;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class UserList {
    private static final UserList users = new UserList();

    private final Gson gson;
    private final Map<String, String> map = new HashMap<>();

    public static UserList getInstance() {
        return users;
    }

    private UserList() {
        this.gson = new GsonBuilder().create();
    }

    public synchronized Optional<User> getUser(String login) {
        User result = null;
        String password = map.get(login);
        if (password != null) {
            result = new User(login, password);
        }
        return Optional.ofNullable(result);
    }

    public synchronized void add(String login, String password) {
        map.put(login, password);
    }

    public synchronized String toJSON() {
        List<User> userList = new ArrayList<>();
        for (String login : map.keySet()) {
            userList.add(new User(login, map.get(login)));
        }
        return gson.toJson(new JsonUsers(userList));
    }
}
