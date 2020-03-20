<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL : 화면에 출력하는 언어
	=> 사용법
		${출력물} ==> { } 일반 자바변수 (X) 	
		
		<%= 일반 자바 변수 %>
		${ } => getParameter("id") ==> ${param.id}
		${ } => request.getAttribute("id")
				=> ${requestScope.id} = ${id}  		key 명칭만 들어간다. 
		${ } => session.getAttribute("name","홍길동")
				=> ${sessionScope.name} => ${name}
		예 )
			String id= "admin";
			${id} (x)
			request.setAttribute("id",id)
			${id}	==> 출력 


			request.setAttribute("id", "admin")
			session.setAttribute("id", "hong");
			
			${id} => admin 을 출력함
			${sessioScope.id} = > hong
			
			
 --%>    
 
 <%
 	String name="홍길동";
 	request.setAttribute("name", name);
 %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : ${name }
</body>
</html>