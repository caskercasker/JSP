<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<% 
	//한글 데이터 처리 
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception e){}

	String name = request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	//hidden 변수 
	String no = request.getParameter("no");
	String strPage = request.getParameter("page");
	
	
	//form tag 에서 온 값을 vo에 저장
	BoardVO vo = new BoardVO();
	vo.setNo(Integer.parseInt(no));
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	//DAO 연결
	BoardDAO dao = new BoardDAO();
	//비밀번호 확인 
	boolean bCheck = dao.boardUpdate(vo);
	//이동
	
	if(bCheck==true){
		response.sendRedirect("detail.jsp?no="+no+"&page="+strPage);
	}
	else{
		%>
			<script type="text/javascript">
				alert("비밀번호가 틀립니다!");
				history.back();
			</script>
		<%
	}
%>

<%-- <jsp:usebean id="vo" class="com.sist.dao">
	<jsp:setPropert name="vo" propert="*"/>
</jsp:usebean> --%>
