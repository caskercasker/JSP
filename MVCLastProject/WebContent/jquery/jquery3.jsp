<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,java.util.*"%>
<%
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn = DriverManager.getConnection(url,"hr","happy");
		String sql="SELECT rank,title,singer,poster FROM music_genie";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
%>   
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->
 <html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#keyword').keyup(function(){
		let k=$(this).val();
		console.log(k);
		$('#user-table > tbody > tr').hide();
		let temp=$('#user-table > tbody > tr > td:nth-child(4n+3):contains("'+k+'")');
		
		$(temp).parent().show();
		//		========
		// 	Node 구성상 화면을그릴  tr은 td에서 상위 에 있다. 
		/*
			td중에 겁색어를 적용 시킬 td의 요소에 접근하기 위해 (곡명) child를 특정지어 준다. 
		*/
		
		/*	
			====================================================
					1		2		3		4		
			====================================================
					5		6		7		8
			====================================================
					9		10		11		12				
			====================================================
		*/
	});
});


/* $(function a() {
	
})
var a = function() {
	
}
var a = () => {
	
}
a() */
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">뮤직 Top50</h1>
			<table class="table">
				<tr>
					<td>
						<input type=text size=30 class="input-sm" placeholder="검색어 입력" id="keyword">
					</td>
				</tr>
			</table>
		
			<table class="table table-hover" id="user-table">
				<tr class="success">
					<th>순위</th>
					<th></th>
					<th>곡명</th>
					<th>가수명</th>
				</tr>
				<tbody>
				<%
					while(rs.next())
					{
				%>
					<tr>
						<td><%=rs.getInt(1) %></td>
						<td><img src="<%=rs.getString(4) %>" width=50 height=30/></td>
						<td><%=rs.getString(2) %></td>
						<td><%=rs.getString(3) %></td>
					</tr>	
				<% 		
					}
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<%
	}catch(Exception ex){}
%>