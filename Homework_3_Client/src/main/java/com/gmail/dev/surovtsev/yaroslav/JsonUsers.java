package com.gmail.dev.surovtsev.yaroslav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUsers {
    private final List<User> list = new ArrayList<>();

    public JsonUsers(List<User> sourceList) {
        list.addAll(sourceList);
    }

    public List<User> getList() {
        return Collections.unmodifiableList(list);
    }
}
