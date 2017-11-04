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
<title>添加页</title>
<script type="text/javascript">
	$(function() {
		$('#add').click(function() {
			var writing = $('#writing').val();
			var pronunciation = $('#pronunciation').val();
			var meaning = $('#meaning').val();
			var tone = $('#tone').val();
			var partOfSpeech = $('#partOfSpeech').val();
			var type = $('#type').val();
			$.ajax({
				url : 'Admin/AddTango',
				type : 'post',
				data : {
					writing : writing,
					pronunciation : pronunciation,
					meaning : meaning,
					tone : tone,
					partOfSpeech : partOfSpeech,
					type : type,
				},
				dataType : 'json',
				success : function(result) {
					if (result.status == 1) {
						alert(result.prompt);
						window.location.reload();
					} else {
						alert(result.prompt);
					}
				},
				fail : function(result) {
					alert(result);
				}
			})
		});

		$('#back').click(function() {
			window.location.href = "Admin/main";
		})
	});
</script>
</head>
<body>
	<div>
		<div>
			写法<input id="writing" type="text">
		</div>
		<div>
			发音<input id="pronunciation" type="text">
		</div>
		<div>
			解释<input id="meaning" type="text">
		</div>
		<div>
			音调<input id="tone" type="text">
		</div>
		<div>
			词性<input id="partOfSpeech" type="text">
		</div>
		<div>
			类型<input id="type" type="text">
		</div>
		<div>
			<input id="add" type="button" value="添加"> <input id="back"
				type="button" value="返回">
		</div>
	</div>
</body>
</html>