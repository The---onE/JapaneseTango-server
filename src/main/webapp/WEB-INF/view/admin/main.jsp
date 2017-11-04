<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="image/favicon.ico">
<script src="js/jquery-2.2.4.min.js" type="text/javascript"></script>
<title>后台主页</title>
<script type="text/javascript">
	$(function() {
		
	});
</script>
</head>
<body>
	<div>
		<a href="Admin/addTango">添加</a>
	</div>
</body>
</html>