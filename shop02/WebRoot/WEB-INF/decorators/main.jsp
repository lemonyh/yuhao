<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title default="欢迎使用网上商城"/></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<decorator:head/>
</head>
<body>
<a href="">用户管理</a>
<hr/>
<h3 align="center" ><decorator:title default="商城管理"></decorator:title></h3>
<decorator:body/>
<hr/>
<div align="center" style="width:100%;border-top:1px solid; float:left;margin-top:10px;">
	CopyRight@2014-2015<br/>
	Demoyuhao
</div>
</body>
</html>