<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<%--
	기본객체 (내장객체)
		1) request : *사용자 요청 정보, 사용자의 정보 ==> session 생성, cookie 생성
			HttpServletRequest
		2) response : 응답정보, header 정보, ***이동 정보(페이지) (sendRedirect())
							  ==========
							   파일 다운로드 (파일명, 파일크기를 서버에 먼저 전송 => 파일전송)
			=> 데이터 전송 (header, data)
			HttpServletResponse
		3) out 객체  => HTML을 출력하는 메모리(출력버퍼)				(.class 파일을 메모리에 적재 하고 실행하게 된다.)
			
			=>JspWriter
			JSP 실행 과정
				a.jsp 				          자바로 변경(서블릿)  a_jsp.java(한번만 수행)			bytecode(JVM) 
	사용자 요청 =========> 톰켓이 파일을 가지고온다 ====================================> 컴파일 ==> a_jsp.class ==> 실행 				
										  변경된 자바 파일이 존재
										  								====> 출력 버퍼에 HTML 을 출력	=======> 사용자에게 전송 
			1. 메모리에 출력 => print(), println(), 
							getBufferSize()=> 8kb, getRemaining() => 남아있는 버퍼 크기 
				=> 개발자 보다 관리자가 주로 사용 
		4) application : 서버정보, 버전, *** 로그파일 관리 (log), web.xml을 제어.....
								 ===
								 3.1
		***5) session (******) 
		
		
		6) pageContext : JSP 관리 	==> include(), forward()
		======================================================
		7) config = web.xml
		8) page = this											(page라는 객체로 접근 가능 하다 ) JSP를 활용한다 누가? 톰캣이? 아파치가? 브라우저가? 
		
		9) exception => try ~ catch
			*cookie 는 내장객체가 아니다. ( 내 컴퓨터에 저장)
			ex> 최근 방문 기록/ 
		======================================================
		
		
		OUT => 메모리 관리 (출력 버퍼) 관리 
				= 메모리 출력(HTML, XML) => out.println() , out.print(), <%= %>
				= 메모리 크기 확인  getBufferSize		==> default : 8kb
				= 남아 있는 메모리 확인 : getRemaining()
				= 버퍼 지우기 : flush(), clear()  ====> JSP (autoFlush())  
--%>			
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	현재 메모리 크기: <%= out.getBufferSize() %><br>
	남아 있는 메모리 크기 : <%= out.getRemaining() %><br>
	현재 사용중인 메모리 크기 : <%= out.getBufferSize()-out.getRemaining() %><br>

	현재 메모리 크기: <% out.println(out.getBufferSize()); %><br>
	남아 있는 메모리 크기 : <% out.println(out.getRemaining()); %><br>
	현재 사용중인 메모리 크기 : <% out.println(out.getBufferSize()-out.getRemaining()); %><br>
		
</body>
</html>