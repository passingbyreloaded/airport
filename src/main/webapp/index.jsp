<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Airport</title>
  </head>
  <body>
  <%
    if ("wrong".equals(request.getParameter("errorCode"))){
  %>
  <p style="font-color: #FF0000"> Error: wrong login or password</p>
  <%
    }
  %>
  <br>
  <form action="/login" method="post">
    <h1>Login:</h1>
    <input type="text" name="login">
    <h1>Password:</h1>
    <input type="text" name="password">
    <br>
    <br>
    <br>
    <input type="submit">
  </form>
  </body>
</html>
