package com.gmail.dev.surovtsev.yaroslav;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Command {
    private String name;
    private String[] params;

    public Command() {
        super();
    }

    public Command(String name, String[] params) {
        super();
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Command fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Command.class);
    }

    public Object toObject() {
        Object result = null;
        if ("/reg".equals(name) || "/login".equals(name)) {
            result = new User(params[0], params[1]);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
