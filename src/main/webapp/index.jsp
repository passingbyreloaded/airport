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
    <h2>Login:</h2>
    <input type="text" name="login">
    <h2>Password:</h2>
    <input type="text" name="password">
    <br>
    <br>
    <br>
    <input type="submit">
  </form>
  </body>
</html>
