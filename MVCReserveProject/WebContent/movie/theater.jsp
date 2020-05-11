<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.theater').hover(function(){
		$(this).css('cursor','pointer');
	},function(){
		$(this).css('cursor','none');
	})
	
	$('.theater').click(function(){
		var data = $(this).text().trim();
		var loc = data.substring(1,data.lastIndexOf(']'));
		var name = data.substring(data.indexOf(']')+2);

		var theater = name+"("+loc+")";
		$('#movie-theater2').text(theater);
		$('#tname').val(theater);
		
		var year=$('#year').val();
		var month=$('#month').val();
		var rdate=$(this).attr("data-date");

		$.ajax({
			type:'post',
			url:'date.do',
			data:{"year":year,"month":month,"rdate":rdate},
			success:function(res){
				$('#movie-date').html(res);
			}
		})	
	})
});
</script>
</head>
<body>
<div class="row" style="margin:0px auto; width:350px; height:500px">
		<table class="table">
			<c:forEach var="vo" items="${tlist}">
				<tr>
					<td class="theater" data-date="${vo.tdate}">							<!-- 넘기기 위해 태그안에 값을 가지고 있는다. -->
						[${vo.tloc }]&nbsp;${vo.tname}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>