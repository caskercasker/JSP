<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.vo.*"%>
    
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 	List<Sawon> list = new ArrayList<Sawon>();
 	list.add(new Sawon(1,"홍길동","영업부"));
 	list.add(new Sawon(2,"심청이","기획부"));
 	list.add(new Sawon(3,"오자서","인재부"));
 	list.add(new Sawon(4,"굴원","홍보부"));
 	list.add(new Sawon(5,"곽거병","자재부"));
 
 	request.setAttribute("list", list);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자바코딩</h2>
		<table border=1>
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>부서</th>
			</tr>
			<%
				for(Sawon s:list)
				{
			%>
				<tr>
				<td><%=s.getSabun() %></td>
				<td><%=s.getName() %></td>
				<td><%=s.getDept() %></td>
				</tr>
			<%
				}
			%>
		</table>
	<h2>JSTL코딩</h2>
		<table border=1>
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>부서</th>
			</tr>
			<%--	{name} => getXxx() 호출
			
			
			 --%>
			<c:forEach var="s" items="${list}">
				<tr>
					<td>${s.sabun}-${s.getSabun()}</td> <%--s.sabun(s.getSabun()) --%>
					<td>${s.name}-${s.getName()}</td>
					<td>${s.dept}-${s.getDept()}</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>