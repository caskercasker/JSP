<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
   <jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
   <%
  		String strPage = request.getParameter("page");
   		if(strPage==null)
   			strPage="1";
   		int curPage = Integer.parseInt(strPage);
   		
   		List<MovieBean> list = dao.movieListData(curPage);
   		int totalPage = dao.movieTotalPage();
   		
   		final int BLOCK= 5;
   		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
   		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
   		/*
   			첫번쨰 시작은 무조건 1 그래서 현재가 13이면 12/10 하면 1 
   			그냥 10부터 출력 하는 방식 
   			
   			curpage 
   			1~10 => 1~10
   			11~20 => 11~20
   			21~30 => 21~30
   			
   			
   			endPage+1 => 5+1 --> curPage=6 => endPage=10+1 curPage=11
   			
   			1~10까지 블록이 10개니까 
   		*/
   		
   		//총 페이지 31라면 마지막 10개 단위는 31만 나와야 됨. 
   		if(endPage>totalPage) {
   			endPage=totalPage;
   		}
   			
/*    			curPage = endPage+1 --> curpage 6이 되면
   			startPage 6 
   			endPage 10 설정 완료
   			
   			다음 버튼을 누르면 curpage = endpage+1 ==>  curPage =11
   			start page =11
   			endPage = 15 	
   			이전 버튼을 누르면 curpage=startPage-1 ==> curPage = 10 
   			 */
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 상위 이미지 출력  -->
	<div class="container">
		<h1 class="text-center">영화 목록</h1>
			<div class="row" style="text-align:right">
				<%=curPage %> page/ <%=totalPage %> pages
			</div>
				<div class="row">
				<%
					for(MovieBean vo:list){
				%>
			
				
					<div class="col-md-4">
					    <div class="panel panel-info">
					      <div class="panel-heading">
					      <%
					      	String temp=vo.getTitle();
					      	if(temp.length()>22){
					      		temp = temp.substring(0, 22)+"...";
					      	}
					      
					      %>
					      <%=temp %> <br>
					      </div>
					      <div class="panel-body">
					      	<img src="<%=vo.getPoster() %>" width=280 height=130>
					      	</div>
						</div>
					</div>
				<%
					}
				%>	
			</div>
			
			
			
<!--  하위 버튼 출력 -->
		<div class="row" style="text-align:center">
			<ul class="pagination">
			<li><a href="movie.jsp?page=1">&lt;&lt;</a></li>	
			  <%
			  	if(curPage>BLOCK){
			%>
				<li><a href="movie.jsp?page=<%=startPage-1%>">&lt;</a></li>	
			<%
				}
			%>		
		    <%
			  	for(int i=startPage;i<=endPage;i++){
			  		if(curPage==i){
			  				
			  %>
			  			<li class="active"><a href="movie.jsp?page=<%=i%>"><%=i %></a></li>
			  <%
			  		}else{
			  %>
			  			<li><a href="movie.jsp?page=<%=i%>"><%=i %></a></li>
			  <% 		
			  		}
			  	}
			  
			  %>
			  <%
			  	if(endPage<totalPage){
			  %>
				  <li><a href="movie.jsp?page=<%=endPage+1%>">&gt;</a></li>
			  <%
			  	}
			%>
			<li><a href="movie.jsp?page=<%=totalPage%>">&gt;&gt;</a></li>
			<li><a href="#"><%=curPage %> page/ <%=totalPage %> pages </a>
			</ul>
		</div>
	</div>
</body>
</html>