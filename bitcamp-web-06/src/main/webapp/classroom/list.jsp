<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물 목록2</title>
</head>
<body>
<h1>게시물 목록</h1>
<p><a href='form.html'>새 글</a></p>
<table border='1'>
<tr>
    <th>번호</th><th>강의명</th><th>기간</th><th>강의실</th>
</tr>
<c:forEach items="${list}" var="classroom">
<tr>
    <td>${classroom.no}</td>
    <td><a href='view?no=${classroom.no}'>${classroom.title}</a></td>
    <td>${classroom.startDate}~${classroom.endDate}</td>
    <td>${classroom.room}</td>
</tr>
</c:forEach>
</table>
</body>
</html>
