<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('h1#h').css("color","red").css("background-color","green");
	$('h1#h').css("color","blue").css("background-color","pink");
	$('h1#h').css("color","red").css("background-color","green");
})
</script>
</head>
<body>
	<h1>Java</h1>
	<h1 id="h">Oracle</h1>
	<h1>Jsp</h1>
	<h1 class="a">Spring</h1>
	<h1 class="a">Kotlin</h1>
</body>
</html>