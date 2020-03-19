<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session.invalidate(); 전체 삭제 
	session.removeAttribute("name"); //일부 삭제 (장바구니,찜)
	response.sendRedirect("login.jsp");
	
	/*
		Session : 서버에 일부 데이터 저장 => 모든 JSP 에서 공유할 목적으로 사용. ...
		주요기능
		
		=SetAttribute(key,Object) : 세션 공간에 저장
		=getAttribute(key) : 저장된 값을 읽어올 때
		=invalidate() : 저장된 모든 데이터를 
		
		=removeAttrubute(key) : 한개씩 삭제
		=setMaxActiveInterval() : 저장기간 설정 
	*/
%>