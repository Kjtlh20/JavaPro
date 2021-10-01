package com.gmail.dev.surovtsev.yaroslav;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class QuestionnaireServlet extends HttpServlet {
    private final Map<String, Map<String, Integer>> statistics = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int age;
        try {
            age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e) {
            age = 0;
        }
        String q1 = req.getParameter("question1");
        String q2 = req.getParameter("question2");

        addToStatistics("q1", q1);
        addToStatistics("q2", q2);

        HttpSession session = req.getSession(true);
        session.setAttribute("statistics", statistics);
        resp.sendRedirect("statistics.jsp");
    }

    private void addToStatistics(String questionName, String questionResult) {
        if (questionResult == null) {
            return;
        }
        Map<String, Integer> q1Value = statistics.get(questionName);
        if (q1Value == null) {
            statistics.put(questionName, new HashMap<>());
            q1Value = statistics.get(questionName);
        }
        Integer value = q1Value.get(questionResult);
        if (value == null) {
            q1Value.put(questionResult, 1);
        } else {
            q1Value.put(questionResult, value + 1);
        }
        statistics.put(questionName, q1Value);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}