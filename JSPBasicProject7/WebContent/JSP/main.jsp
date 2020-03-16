<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--  
	jsp 액션 태그 (7장,8장)
	1) <jsp:include>	: 	jsp안에 특정부분에 다른 jsp를 첨부를 할 때	=> 모듈화
		pageContext.include("jsp파일명")	
	2) <jsp:forward>	:	request를 계속 유지할 경우 (MVC 구조)
	3) <jsp:useBean>	:	메모리 할당
		<jsp:useBean id="vo" class="com.sist.dao.MemberVO">
					 ======
			=> MemberVO vo = new MemberVO();
						== 
			동일한 이름으로 같이 사용 할수 있다. 
	4) <jsp:setProperty> : setXxxx()에 값을 채운다. 
		=====> VO, DTO, Bean(JSP)
		Bean
			1) 데이터 관리 빈 => 데이터를 모아서 관리
							읽기/쓰기 		=======> getter/setter
			2) 데이터 액션 빈  => 빈(VO) 값을 설정하는 클래스
				========
							데이터베이스 연결 ~DAO, ~Manager, (DAO - DAO) ~Service 연결
							
		
		================== 자바 빈 
		ex> public class SawonVO{
			private int sabun;
			private String name;
			private String dept;
			
			public int getSabun(){
				return sabun;
			}
			public void setSabun (int sabun)
				this.sabun= sabun;
			}
			.....  (필드 갯수 만큼)
		}
		
		=> SawonVO vo = new SawonVO();
			vo.setSabun(1);
			vo.setName("홍길동");
			vo.setDept("개발부");
			
			========> jsp 액션 태그 사용시 
			<jsp:useBean id="vo" class="SawonVO">
				<jsp:setProperty name="vo"	property="sabun" value="1"/>
				<jsp:setProperty name="vo"	property="name" value="홍길동"/>
				<jsp:setProperty name="vo"	property="dept" value="개발부"/>
			</jsp:useBean>
				-- 자바 코딩 보다 jsp태그를 통해서 관리하는 형식.
			
			==> 다른 JSP에서 값을 보낸다. 
			기존 [
			String sabun = request.getParameter("sabun");
			String sabun = request.getParameter("sabun");
			String sabun = request.getParameter("sabun");

			SawonVO vo = new SawonVO();
			vo.setSabun(Integer.parseInt(sabun));
			vo.setName("name");
			vo.setDept("dept");
	
			]
			액션태그 사용시 
			<jsp:useBean id="vo" class="SawonVO">
				<jsp:setProperty name="vo" property="*"/>
			<jsp:useBean>
			-->
		

			대입시 
			액션태그
			<jsp:getProperty name="vo" property="name">
			
			-> <%=vo.getName() %>
			
			--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>