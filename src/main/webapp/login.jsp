<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h2>Welcome to login page</h2>

<%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>

<form action="/login" method="post">
    Username <input type="text" name="username"></br>
    Password <input type="password" name="password"></br>
    <input type="submit" value="login">
    <label>
        <input type="checkbox" name="rememberMe">
    </label>
</form>

</body>
</html>