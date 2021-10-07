package com.gmail.dev.surovtsev.yaroslav;

import java.util.ArrayList;
import java.util.List;

public class JsonUsers {
    private final List<User> list = new ArrayList<>();

    public JsonUsers(List<User> sourceList) {
        for (int i = 0; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }
}
