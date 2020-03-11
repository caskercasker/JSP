<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String no=request.getParameter("no");
	String pwd = request.getParameter("pwd");
	
	//DAO 연동
	BoardDAO dao = new BoardDAO();
	int res=dao.boardDelete(Integer.parseInt(no), pwd);
%>
<%=res %>
