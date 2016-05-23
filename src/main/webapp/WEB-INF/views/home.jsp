<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>sample home</title>
    <style rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></style>
</head>
<body>
    <p><h1>home</h1></p>
    <p><h1>${h1str}</h1></p>
    <p>All rows of users table is ${rowCount}</p>
    <p>All rows of users table is ${rowCountUsingInterface} (using sqlMapper implements interface)</p>
    <p>All rows of users table is ${fn:length(userList)} (using hibernate for JPA)</p>
    <p>http response is ${httpResponse}</p>
</body>
</html>