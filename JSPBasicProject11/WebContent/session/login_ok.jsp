<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");

	//DAO 연결
	String res=dao.isLogin(id, pwd);
	//이동
	if(res.equals("NoID")){
%>
		<script>
		alert("아이디가 존재 하지 않습니다.");
		history.back();
		</script>
<% 
	}else if(res.equals("WRONG_PWD")){
%>
		<script>
		alert("비밀번호가 틀립니다.\n 다시 입력하세요");
		history.back();
		</script>
<%
	}else{
		//클라이언트가 서버에 연결되면서 session 에 저장한다. 
		session.setAttribute("id", id);
		session.setAttribute("name",res);
		response.sendRedirect("diary.jsp");
	}
%>
