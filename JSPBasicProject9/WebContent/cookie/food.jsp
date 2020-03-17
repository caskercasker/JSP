<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"></jsp:useBean>
<%
	String cateno=request.getParameter("cateno");
	CategoryBean cb=dao.categoryInfoData(Integer.parseInt(cateno));
	List<FoodHouseBean> list=dao.foodCategoryList(Integer.parseInt(cateno));
	
	for(FoodHouseBean vo2:list){
		System.out.println(vo2.getNo());
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h1 class="text-center"><%=cb.getTitle() %></h1>
				<h3 class="text-center"><%=cb.getSubject() %></h3>
			</div>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<%
							for(FoodHouseBean vo:list){
						%>
							<table class="table table-hover">
								<tr>
									<td width=30% class="text-center" rowspan="3">
										<a href="detail.jsp?no=<%=vo.getNo()%>"><img src="<%=vo.getImage() %>" width=350 height=180 class="img-thumbnail"></a>
									</td>
									<td width=70%>
										<h3><a href="detail.jsp?no=<%=vo.getNo()%>"><%=vo.getTitle()%></a>&nbsp;<span style="color:#FC6"><%=vo.getScore() %></span></h3>
									</td>
								</tr>
								<tr>
									<td width=70%>
									<%
										
										String temp=vo.getAddress();
										String a1=temp.substring(0,temp.indexOf("지번"));
										String a2=temp.substring(temp.indexOf("지")+3);
										//"지" 이후 공백 까지 삭제
									%>
									<%=a1 %><br>
									지번)<%=a2 %>
									</td>
								</tr>
								<tr>
									<td width=70%><%=vo.getTel() %></td>
								</tr>
							</table>
						<%
							}
						%>
					</td>
				</tr>
			</table>
		</div>
		<!-- 카테고리 목록으로 BACK -->
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-right">
						<a href="category.jsp" class="btn btn-lg btn-primary">목록</a>
					</td>		
				</tr>
			</table>
		</div>
	</div>
</body>
</html>