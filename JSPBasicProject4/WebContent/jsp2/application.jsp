<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	application 객체 ==> ServletContext
	=> Servelt (Server+let) let(가벼운 프로그램) => web
		MIDlet, Applet	
		
	Java SE : Application
	Java EE : 기업용 환경 (WEB)
	Java ME : 모바일
	
	Servlet(연결) & JSP(화면출력)
 
	=============

	브라우저 ==> 사용자가 데이터 전송 ==> 받을수 있는 파일 (Servlet, JSP)
	
	======================================================
	Data ====>Servlet(Model) ====> JAVA(Controller) =======> JSP 			=====> MVC 구조   (개발, 유지보수(확장성, 재사용))
	======================================================

	Application 웹 어플리케이션 Context의 정보를 저장하고 있는 객체 
	서버와 관련 
		1) 서버 정보 
			= getServerInfo() 			=> 3.1			1.7 		1.8_01(라이브러리 추가) 	1.12
			= 버전정보 getMajorVersion()
					getMinorVersion()
			
		2) 자원 정보
			= getRealPath()
		3) web.xml을 읽어서 처리 == > log 파일
			= getInitParamter() 
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>		<%= application.getRealPath("/jsp2/application.jsp") %></h4><br>
	서버이름 : <h4><%= application.getServerInfo()%></h4><br>
	버전 : <h4>	<%= application.getMajorVersion()%></h4> <br>
				<%= application.getMinorVersion()%>

	<%		String driver=application.getInitParameter("driver");
			String url=application.getInitParameter("url");
			String username=application.getInitParameter("username");
			String password=application.getInitParameter("password");
			application.log("Driver:"+driver);
			application.log("URL:"+url);
			application.log("Username:"+username);
			application.log("password:"+password);
	%>
</body>
</html>