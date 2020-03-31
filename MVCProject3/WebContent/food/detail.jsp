<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class=container>
		<div class="row">
			<div class="col-md-8">
				<table class="table">
					<tr>
						<c:forTokens items="${vo.image}" delims="^" var="img">
							<td>
								<img src="${img }" width=100%>
							</td>
						</c:forTokens>
					</tr>
					<tr>
						<td class="text-center" colspan="5">
							<h3>${vo.title }&nbsp;<span style="color:#FC6">${vo.score }</span></h3>
					</tr>
					<tr>
						<td class="text-right" width=15%>주소</td>
						<td colspan="4">${vo.address }
						<sub style="color:gray"></sub>
					</td>
					</tr>
					<tr>
						<td class="text-right" width=15%>전화번호</td>
						<td colspan="4">${vo.tel }</td>
					</tr>
					<tr>
						<td class="text-right" width=15%>음식종류</td>
						<td colspan="4">${vo.type }</td>
					</tr>
					<tr>
						<td class="text-right" width=15%>가격대</td>
						<td colspan="4">${vo.price }</td>
					</tr>
					<tr>
						<td class="text-right" colspan="5">
							<a href="category.do" class="btn btn-sm btn-info">목록</a>
						</td>
					</tr>
				</table>	
			</div>
			</div>
	</div>
</body>
</html>