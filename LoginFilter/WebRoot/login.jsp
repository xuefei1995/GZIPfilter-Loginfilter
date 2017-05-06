<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
  <head>   
    <title></title>   
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/LoginServlet" method="post">
    	用户名<input type="text" name="name"><span>${requestScope.msg }</span><br>
    	密码<input type="password" name="password"><br>
    	<input type="submit" value="登陆">
    </form>
  </body>
</html>
