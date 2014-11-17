<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
  <head>
  
    
    <title>My JSP 'list.jsp' starting page</title>
    
	

  </head>
  
  <body>
    <table width="800" align="center" cellpadding="0" cellspacing="0">
    <tr><td>用户标识</td><td>用户姓名</td><td>用户密码</td><td>用户别名</td><td>用户权限</td><td>用户标识</td></tr>
    <c:forEach items="$user.datas" var="u">
    <tr>
    <td>${u.id}</td>
     <td>${u.username}</td>
      <td>${u.password}</td>
       <td>${u.nickname}</td>
        <td>
        <c:if test="${u.type eq 0}">普通</c:if>
         <c:if test="${u.type eq 0}">管理</c:if>
        </td>
        <td>修改  </td>
    </tr>
    </c:forEach>
    </table>
  </body>
</html>
