<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시물 목록</title>
</head>
<body>


<h1>게시물 목록</h1>
<p><a href='form'>새 글</a></p>
<table border='1'>
<tr>
    <th>번호</th>
    <th>제목</th>
    <th>등록일</th>
</tr>
<c:forEach items="${list}" var="board">  
<tr>
    <td>${board.no}</td>
    <td><a href='view/${board.no}'>${board.title}</a></td>
    <td>${board.createdDate}</td>
</tr>
</c:forEach>
</table>
    <div>
        <c:if test="${page >1}">
            <a href="list?page=${page - 1}&size=${size}">[이전]</a>
        </c:if>
        <c:if test="${page <= 1 }">
[이전]</c:if>

        <span>${page}</span>
        <c:if test="${page < totalPage }">
            <a href="list?page=${page + 1}&size=${size}">[다음]</a>
        </c:if>
        <c:if test="${page >= totalPage}">
    [다음]
    </c:if>
    </div>
</body>
</html>