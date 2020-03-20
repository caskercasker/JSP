<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width:800px;
}
h2 {
	text-align:center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>Java 구구단</h2>
			<table class="table table-hover">
				<tr class="success">
					<%
						for(int i=2; i<=9; i++)
						{
					%>
						<th class="text-center">
							<%=i+"단" %>
						</th>
					<%
					}
					%>
				</tr>
				<%
					for(int i=1; i<9; i++)
					{
				%>
					<tr>
				<% 
						for(int j=2; j<=9; j++)
						{
				%>
							<td class="text-center"><%= j+"*"+i+"="+(j*i) %></td>
				<% 
							
						}
				%>
						</tr>
				<% 
					}
				
				%>
			</table>
		</div>
		<div class="row">
			<h2>JSTL 구구단</h2>
			<table class="table table-hover">
				<tr class="danger">
					<c:forEach var="i" begin="2" end="9">
						<td class="tex-center">${i+="단"}</td>
					</c:forEach>
				</tr>
				<c:forEach var="i" begin="1" end="9">
					<tr>
						<c:forEach var="j" begin="2" end="9">
							<td class="text-center">
								${j}*${i}는 ${i*j }
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
<%-- 	<c:forEach var="i" begin="2" end="9" step="1">
		<c:forEach var="j" begin="1" end="9" step="1">
			${i}*${j}는 ${i*j }
		</c:forEach>	
		<br>
	</c:forEach> --%>
</body>
</html>