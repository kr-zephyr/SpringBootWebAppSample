<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <p>Current Selected database is ${selectedDbName}</p>
    <p>Current Selected database is ${selectedDbNameUsingInterface} (using sqlMapper implements interface)</p>
    <p>http response is ${httpResponse}</p>
</body>
</html>