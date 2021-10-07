package com.gmail.dev.surovtsev.yaroslav;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final UserList users = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Command commandLogin = Command.fromJSON(bufStr);
        User user = (User) commandLogin.toObject();
        String login = user.getLogin();
        String password = user.getPassword();

        HttpSession session = req.getSession(true);
        String user_login = (String) session.getAttribute("user_login");
        System.out.println("user_login: " + user_login);
        if (user_login != null && user_login.equals(login)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            System.out.println("login ok");
            return;
        }

        Optional<User> resultUserFromDB = users.getUser(login);
        User userFromDB = null;
        if (resultUserFromDB.isPresent()) {
            userFromDB = resultUserFromDB.get();
        }
        if (userFromDB == null || !userFromDB.getPassword().equals(password)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        session.setAttribute("user_login", login);
        System.out.println("setAttribute user_login:" + login);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
