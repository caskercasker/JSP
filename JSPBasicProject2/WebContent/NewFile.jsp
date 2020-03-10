<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js">
$(function(){
	$('#btn').click(function(){
		var name = $('#name').val();
		if(name.trim()=""){
			$('#name').focus();
			return;
		}
	
	$('#frm').submit();
});
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>