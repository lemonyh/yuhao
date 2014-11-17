<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加用户</title>

  </head>
  
  <body>
  	<jsp:include page="inc.jsp"></jsp:include>
  	<form action="user.do?method=add" method="post">
  	<table width="600" class="thin-border">
  	<tr>
  	<td>用户名</td> <td><input type="text"name="username" value="${ parms.username}"> ${errors.username}</td>
  	</tr>
  	<tr>
  	<td>密码</td> <td><input type="password"name="password" value="${ parms.password}">${errors.password}</td>
  	</tr>
  	<tr>
  	<td>匿名</td> <td><input type="text"name="nickname" value="${parms.nickname}">${errors.nickname}</td>
  	</tr>
  	<tr>
  	<td colspan="2">
  	<input type="submit" value="用户注册"/><input type="reset"/>
  	</td>	
  	</tr>
  	</table>
  	</form>
  </body>
</html>
