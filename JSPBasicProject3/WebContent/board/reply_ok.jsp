<%@page import="com.sist.dao.BoardDAO"%>
<%@page import="com.sist.dao.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//답변 달기 
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}

	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	//답변이 달릴 글의 번호 
	String pno=request.getParameter("pno");
	String strPage = request.getParameter("page");
	
	
	
	BoardVO vo = new BoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	//DAO 연결
	BoardDAO dao = new BoardDAO();
	dao.replyInsert(Integer.parseInt(pno), vo);
	
	//이동 => 목록 (list.jsp)
	response.sendRedirect("list.jsp?page="+strPage);
%>