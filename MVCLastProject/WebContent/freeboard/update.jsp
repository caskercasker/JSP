<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert the table</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
	margin: 0px auto;
	width: 600px;
}
h2 {
	text-align: center;
}
</style>
</head>
<body>
<div class="wrapper row2">
		<div id="services" class="clear">
			<div class="row text-center">
				<img src="../freeboard/jsp_board.png" style="width:900px; height:200px"/>
			</div>
		<div class="row">
		<form method=post action="../freeboard/update_ok.do">
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right success">이름</th>
					<td width=80%>
						<input type="text" name=name size=15 value="${vo.name }" required />
						<input type="hidden" name=no value="${vo.no }"/>
					</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right success">제목</th>
					<td width=80%>
						<input type="text" name=subject size=50 value="${vo.subject }" required/>
					</td>
				</tr>
				
				<tr>
					<th width=20% class="text-right success">내용</th>
					<td width=80%>
						<textarea rows="8" cols="55" name=content required>${vo.content }</textarea>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right success">비밀번호</th>
					<td width=80%>
						<input type="password" name=pwd size=10 required/> 
					</td>
				</tr>

				<tr>
					<td class="text-center" colspan="2">
						<input type="submit" value="수정" class="btn btn-sm btn-primary"/>
						<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()"/>						 
					</td>
				</tr>				
			</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>