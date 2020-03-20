<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	${연산자} 	
	==> 산술연산자
		+ , - , *, / (div) , % (mod)
						==> ${5/2}, ${5 div 2 }
				${"10"+1} 자바가 아니라 순수한 산술 연산자만 한다. 
				=====
					Ingeger.parseInt("10" => 10+1 => 11
		================ <%= 10%3 %> (${10/3}:실수)
		+ : 산술연산, 문자 결합
			"Hello"+10 ==> Hello10
			
		+ : 순수하게 산술만 가능  (차이점***) 
			"Hello"+10 ==> error
		자바 : <%= "100"+"10"%> => 10010
				${"100"+"10"} => 110
				==> 문자열 결합 => (+=)
				<%= "count:"+100>
				<%= null+10 %>
					====
						0으로 변경........
						
				${sessionScope.id} ==> null 		예매, 댓글 
													세션이 없다면. 로그인 하시오. 
				
				
		비교 연산자
			== | eq (문자열, 숫자) ==> ${requestScrope.id="admin") , ${requestScrope.id eq "admin")
			!= | ne (문자열, 숫자) ==> ${requestScrope.id="admin") , ${requestScrope.id ne "admin")
			<  | lt {10<5}
			>  | gt {10>5}
			<= | le {10<=5}
			>= | ge {10>=5}
			
		논리 연산자 ===> true/false
			&& | and
			|| | or 	
			!  | not
			
			String id; ==> null
			String id=""; ==> ""
		
		empty연산자 = 빈 공백, (null 아님)
			${empty list}
		
		삼항연산자
			${조건?값1:값2}
		문자열 결합: +=
		<%= %>
		
		=========> JSP 안에서는 <% %> (X)는 사용하지 말라. 
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%= "110"+"10" %><br>
	${"110"+"10"}<br>
	${"hello"+=10 }
</body>
</html>