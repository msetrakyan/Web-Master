<%@ page import="com.smartcode.web.repository.comment.CommentRepository" %>
<%@ page import="com.smartcode.web.repository.comment.impl.CommentRepositoryJDBCImpl" %>
<%@ page import="com.smartcode.web.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.smartcode.web.repository.user.UserRepository" %>
<%@ page import="com.smartcode.web.repository.user.impl.UserRepositoryJDBCImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

<form action="/home" method="get">
      <input type="submit" value="logout">
</form>

<form action="changePassword.jsp" method="get">
    <input type="submit" value="change">
</form>







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

<form action="/home" method="post">
    Title <input type="text" name="title"></br>
    Description <input type="text" name="description"></br>
    <input type="submit" value="submit">
</form>






<table>

    <tr>
        <th>Comment table</th>
        <th>Description</th>
    </tr>

<%
    CommentRepository commentRepository = new CommentRepositoryJDBCImpl();

    UserRepository userRepository = new UserRepositoryJDBCImpl();

    List<Comment> list = commentRepository.getAll((int)request.getSession().getAttribute("id"));

    for(int i = 0; i < list.size(); i++) {
%>
        <tr>
            <td>
              <%=list.get(i).getTitle()%>
            </td>
            <td>
                <%=list.get(i).getDescription()%>
            </td>
            <td>
                <form action="/comment" method="post">
                    <input type="hidden" name="title" value="<%=list.get(i).getTitle()%>" />
                    <input type="submit" value="delete">
                </form>

            </td>
            <td>
                <form action="/comment" method="get">
                    <input type="hidden" name="title" value="<%=list.get(i).getTitle()%>" />
                    <input type="submit" value="update">
                </form>
            </td>

        </tr>

<%
    }
%>

    </table>


</body>
</html>