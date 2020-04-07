<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
<style type="text/css">
.row {
	margin:0px auto;
	width: 500px;
}
</style>
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 * 	<input type="text"id="id"> 	==> $('#id').val()     ( val() =  getter 형태 값을 가져오는 형태 )
 * 	<td>aaa</td>				==> $('td').text()		//사이의 값만 읽어오기 aaa
 	<td><span>aaa</span></td>   ==> $('td').html()		//tag 까지 가져오기 <span>aaa</span>
 	<a href="aaa">				==> $('a').attr("href") //tag 사이의 속성 가져오기
 	
 	<input type="text" id="id>" ==> $('#id').val('admin') (setter 새로운 값을 넣기 )
 	$('td').append() 			==> 값을 변경(setter) 가 아닌 하나씩 추가 
 */
$(function() {
	$('#findBtn').click(function(){
		var dong=$('#dong').val();
		if(dong.trim()=="")	{
			$('#dong').focus();
			return;
		}
		//alert(dong);
				$.ajax({
					type:'POST',
					url:'../member/postfind_result.do',
					data:{"dong":dong},					//json 형태로 데이터 전송
					success:function(result){			//200이면
						$('#result').html(result);
						alert(result);
					}, 
					error:function(e){					//200이 아니라면
						alert("ERROR");
					}  
					/*
					dataType (default: Intelligent Guess (xml, json, script, or html))
					Type: String
					서버에서 받을 데이터 형식을 지적한다.
					지정하지 않으면 MIME 타입을 참고하여 자동 파싱된다.
					
					출처: https://cofs.tistory.com/404 [CofS]
					
					*/
				})
			})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력: <input type=text id="dong" size=15 class="input-sm"/>
						<input type=button id="findBtn" class="btn btn-sm btn-danger" value="입력"/>
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<sub style="color:red">※동/읍/면을 입력하세요 </sub>
					</td>
				</tr>
			</table>
			<div id="result"></div>
		</div>
	</div>
</body>
</html>