<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
	<head>


		<title>用户列表</title>



	</head>

	<body>
	<jsp:include page="inc.jsp"></jsp:include>
		<table width="800" align="center" cellpadding="0" cellspacing="0" class="thin-border">
			<tr>
				<td>
					用户标识
				</td>
				<td>
					用户名称
				</td>
				<td>
					用户密码
				</td>
				<td>
					用户昵称
				</td>
				<td>
					用户类型
				</td>
				<td>
					操作
				</td>
			</tr>
			<c:forEach items="${users.datas}" var="u">
				<tr>
					<td>
						${u.id}
					</td>
					<td>
						${u.username}
					</td>
					<td>
						${u.password}
					</td>
					<td>
						${u.nickname}
					</td>
					<td>
						<c:if test="${u.type eq 0}">普通</c:if>
						<c:if test="${u.type eq 1}">管理员</c:if>
						<a  href="user.do?method=changType&id=${u.id} ">变更</a>
					</td>
					<td>
						<a href="user.do?method=updateInput&id=${u.id}">修改 </a>
						<a href="user.do?method=delete&id=${u.id}">删除 </a>
					</td>
				</tr>
			</c:forEach>
			<td colspan="6">
				<jsp:include page="/inc/pager.jsp">
					<jsp:param value="user.do" name="url" />
					<jsp:param value="${user.totalRecord}" name="items" />
					<jsp:param value="method" name="params" />
				</jsp:include>
			</td>
		</table>
	</body>
</html>
