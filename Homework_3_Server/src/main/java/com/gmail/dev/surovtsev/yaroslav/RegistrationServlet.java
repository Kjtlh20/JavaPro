package com.gmail.dev.surovtsev.yaroslav;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RegistrationServlet extends HttpServlet {

    private final UserList users = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Command commandReg = Command.fromJSON(bufStr);
        User user = (User) commandReg.toObject();
        String login = user.getLogin();
        String password = user.getPassword();
        if (login == null || password == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        users.add(login, password);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
