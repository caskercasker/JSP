<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
    
<%
	//DAO 에서 값 받기
	List<MusicVO> list = MovieDAO.musicAllData();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1 class="text-center">현재 상영 영화</h1>
		<div class="row">
			<%
				for(MusicVO vo:list){
					%>
					
				<!--  boostarp 값 채워 넣기 
					img src - vo.getPoster
					< vo.getTitle();
				 -->
				 <div>
				 	<img src="<%=vo.getPoster() %>" alt="nono" style="width:25%">
				 	<div>
				 		<p><%= vo.getTitle() %></p>
				 	</div>
				 </div>
			
				<% 	
				}
			%>
		</div>
	</div>
</body>
</html>