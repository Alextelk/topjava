<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
            width: 600px;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>Date</td>
        <td>Descrition</td>
        <td>Calories</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach var="meal" items="${mealsList}">
        <tr style="color:${meal.excess?'red':'green'}">
            <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="">update</a></td>
            <td><a href="">delete</a></td>
        </tr>
    </c:forEach>
    </tr>
</table>
<h3><a href="index.html">Home</a></h3>
</body>
</html>