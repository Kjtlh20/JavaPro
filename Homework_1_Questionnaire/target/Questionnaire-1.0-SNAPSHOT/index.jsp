<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>
<form action="/questionnaire" method="POST">
    Firstname: <input type="text" name="firstname"><br>
    Lastname: <input type="text" name="lastname"><br>
    Age: <input type="number" name="age"><br>
    1. Choose your favorite programming language<br>
    <input type="radio" name="question1" value="Java"> Java<br>
    <input type="radio" name="question1" value="C"> C<br>
    <input type="radio" name="question1" value="Fortran"> Fortran<br>
    <input type="radio" name="question1" value="Python"> Python<br>
    2. Choose your work schedule<br>
    <input type="radio" name="question2" value="Full time"> Full time<br>
    <input type="radio" name="question2" value="Part time"> Part time<br>
    <input type="radio" name="question2" value="Flexible-hours basis"> Flexible-hours basis<br>
    <input type="submit"/>
</form>
</body>
</html>