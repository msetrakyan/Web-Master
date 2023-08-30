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

<h3>Write a comment</h3>


<form action="/home" method="get">
    Title <input type="text" name="title"></br>
    Description <input type="text" name="description"></br>
    <input type="submit" value="submit">
</form>

<form action="/comment" method="post">
<input type="submit" value="CommentSection">
</form>




</body>
</html>