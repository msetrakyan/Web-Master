<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

<%
    String username = (String) request.getSession().getAttribute("username");
    if (username == null) {
        request.setAttribute("message", "Login first!!!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>

<h2>Welcome dear <%=request.getSession().getAttribute("username")%>
</h2>

</body>
</html>