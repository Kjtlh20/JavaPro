<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Statistics</title>
</head>
<body>
<% Map<String, Map<String, Integer>> statistics = (Map<String, Map<String, Integer>>) session.getAttribute("statistics"); %>
<% List<Map<String, String>> people = (List<Map<String, String>>) session.getAttribute("people"); %>
Question 1:
<table border="1">
    <tr>
        <td>Java</td>
        <td>C</td>
        <td>Fortran</td>
        <td>Python</td>
    </tr>
    <tr>
        <td><%= statistics.get("q1").getOrDefault("Java", 0)%>
        </td>
        <td><%= statistics.get("q1").getOrDefault("C", 0)%>
        </td>
        <td><%= statistics.get("q1").getOrDefault("Fortran", 0)%>
        </td>
        <td><%= statistics.get("q1").getOrDefault("Python", 0)%>
        </td>
    </tr>
</table>
<br>
Question 2:
<table border="1">
    <tr>
        <td>Full time</td>
        <td>Part time</td>
        <td>Flexible-hours basis</td>
    </tr>
    <tr>
        <td><%= statistics.get("q2").getOrDefault("Full time", 0)%>
        </td>
        <td><%= statistics.get("q2").getOrDefault("Part time", 0)%>
        </td>
        <td><%= statistics.get("q2").getOrDefault("Flexible-hours basis", 0)%>
        </td>
    </tr>
</table>
<br>
Click this link to <a href="/questionnaire">take a survey again</a>
</body>
</html>