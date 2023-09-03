<%--
  Created by IntelliJ IDEA.
  User: msetrakyan
  Date: 02.09.23
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Enter your current password, then your new password</h1>
<%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>


<form action="/changePassword" method="post">
  <input type="text" name="current"/>
  <input type="text" name="new"/>
  <input type="submit" value="change"/>
</form>










</body>
</html>
