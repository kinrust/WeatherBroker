<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html>
<head>
    <title>Weather Broker created by Rustam Kinzin</title>
</head>

<body>
<form name="name" action="weather" method="post">
    <label class="control-label">Введите название города для получения погоды, и сохранения в БД:</label>
    <input type="text" name="name"/>
    <input type="submit" value="submit"/>
</form>
<p>Например: "Ufa", "London", "Moscow", "Kazan"</p>
<br>
<h4>Прочие возможности сайта (REST сервис):</h4>
<h5>http://localhost:8080/weatherslist   - вывод json массива с погодной информацией по городам в БД</h5>
<h5>http://localhost:8080/weather/{city}/{country}/{region}/{date}  - вывод погоды по выбранному городу в БД</h5>

<c:forEach items="${weathers}" var="weather">
    <tr>
        <td><c:out value="${weather}"/><br></td>
    </tr>
</c:forEach>

</body>
</html>
