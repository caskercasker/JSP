<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!-- 
		session : 서버에서 사용자의 일부 정보를 저장
		=> HTTPSession (각 클라이언트 마다 1개 배정)  ==> request 를 이용해서 session 을 얻어온다.
		=> 관련된 메소드 (기능)
			1) 저장					=======> setAttribute(key,값)
															  == Object
											Cookie cookie= new Cookic(	key	,	값)
																		String	String
			2) 저장된 읽기				=======> Object getAttribute("key") => 반드시 형변환.(제네릭스가 존재 하지 않는다.)
			3) 기간 설정				=======> default(30분)	: 초(1800)
									=======> setMaxactiveInterval(60)
									
			4) 저장된 내용 삭제
				= 한개를 삭제		==> 장바구니 removeAttribute("key")
				= 전체를 삭제  		==> 로그인 일부 정보 	==> 로그아웃 invalidate()
			=================================== Cookie(본인 컴퓨터)/Session(접속한 서버) => 임시저장
			차이점... 쿠키는 로컬에 생성된 데이터를 response객체에 실어야 한다.


			MVC 
			클라이언트 	(데이터 전송)	=== 	Controller(servlet)		==> Model 		==>			 	JSP(View)
																				Request,Session
																					
 -->    
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
	String today=sdf.format(date);
	
	StringTokenizer st = new StringTokenizer(today,"-");
	String strYear=st.nextToken();
	String strMonth=st.nextToken();
	String strDay=st.nextToken();
	
	String sy=request.getParameter("year");
	if(sy==null)
		sy=strYear;
	String sm=request.getParameter("month");
	if(sm==null)
		sm=strMonth;
	
	int year=Integer.parseInt(sy);
	int month=Integer.parseInt(sm);
	int day=Integer.parseInt(strDay);
	
	//1일자의 요일 
	//1년도 1월 1일 ~ 2019년도 12월 31일 ==> 총날수 
	int total=(year-1)*365
			+(year/4)/4
			-(year/100)
			+(year-1)/400;
	
	// 2020년도 2월 29일 ==> 전달
	int[] lastDay={31,28,31,30,31,30,
					31,31,30,31,30,31};
	
	if((year%4==0 && year%100!=0)|| (year/400==0))
			lastDay[1]=29;
	else
			lastDay[1]=28;
	for(int i=0; i<month-1;i++){
		total+=lastDay[i];
	}
	total++;

	int week=total%7; // 1일 자의 요일 구하기 완료
	String[] strWeek={"일","월","화","수","목","금","토"};
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.row{
	margin:0px auto;
	width: 800px;
}
</style>
<script type="text/javascript">
function change() {
 	var f=document.frm;
	f.submit(); 	
/* 	var fn = document.frm;
	fn.submit(); */
}
</script>	
</head>
<body>
	<div class="container">
		<h1 class="text-center"><%=session.getAttribute("name")%>(<%=session.getAttribute("id")%>)&nbsp;<%=sy %>년<%=sm %>월 일정</h1>
		<div class="row">
			<form method="post" name="frm" action="diary.jsp">
				<table class="table">
					<tr>
						<td class="text-left">
							<select name="year" onchange="change()">
								<%
									for(int i=2020;i<=2030;i++)
									{
								%>
									<option <%=i==year?"selected":"" %>><%=i %></option>
								<% 	
									}
								%>
							</select>년도&nbsp;
							<select name="month" onchange="change()">
								<%
									for(int i=1;i<=12;i++)
									{
								%>
									<option <%=i==month?"selected":"" %>><%=i %></option>
								<% 	
									}
								%>
							</select>월&nbsp;
						</td>
					</tr>
				</table>
			</form>
			<table class="table table-bordered">
				<tr class="danger" height="15">
					<%
						String color="";
						int k=0;
						for(String s:strWeek)
						{
							if(k==0)
								color="red";
							else if(k==6)
								color="blue";
							else
								color="black";
					%>
							<th class="text-center" height="25"><h3><font color="<%=color%>"><%=s %></font></h3></th>
					<%	
							k++;
						}
					%>
				</tr>
				<%
					color="black";
					for(int i=1;i<lastDay[month-1];i++)
					{
						if(week==0)
							color="red";
						else if(week==6)
							color="blue";
						else
							color="black";
						if(i==1)
						{
				%>
						<tr>
						<% 
							for(int j=0; j<week; j++)
							{
						%>
							<td height="150" class="text-left" width=100 valign="top">&nbsp;</td>
						<% 
							}
						%>
						
				<%
								
						}
						String back="white";
						if(i==day)
							back="success";
				%>
					<td height="100" class="text-left <%=back %>" width=100 valign="top"><h4><font color="<%=color%>">
					<a href="diary_insert.jsp"><%=i %></a></font></h4></td>	
				<% 		
						week++;
						if(week>6){
							week=0;
				%>
							</tr>
							<tr>
				<% 
						}
					}
				%>
				</tr>
			</table>
		</div>
	</div>
<script type="text/javascript">
function change() {
 	var f=document.frm;
	f.submit(); 	
}
</script>
</body>
</html>