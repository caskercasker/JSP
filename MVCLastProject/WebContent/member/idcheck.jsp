<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 
			success : 200
			error : 404,500
			
			시스템에 의해서 자동 호출 ==> callback
			
			1) AJAX
				XMLHttpRequest request => 얻어온다 (각 브라우저에 내장)
			2) 연결
				request.open('type' , 'URL' , true) 	//get or post '/' ../member/idcheck_result.do '/'  비동기/동기
											  ====
											비동기/동기
			3) 결과값을 가지고 오는 자동 호출 함수를 제작
				request.onreadstatechange=sendMessage
				
			4) 전송할 데이터 설정
				request.send(id=admin&pwd=1234)
				콜백함수 설정
				function sendMessage() {
					0 : open 전 = 연결중
					1 : open 후 = 연결완료
					2 : send 준비
					3 : send 완료
					4 : 정상연결, send완료 확인
				if(request.readystate==4){
					if(request.status==200){
						
					}else {
						400,404,500	
					}
				}
			}
			
			dataType : JSON		,XML		,HTML
						getJSON,
--> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
	margin:0px auto;
	width: 350px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#id').focus();
	$('#checkBtn').click(function(){
		let id=$('#id').val();
		if(id.trim()==""){
			$('#id').focus();
			return;
		}
		//MemberModel => id를 전송
		// data {"id":"admin","pwd":"1234"} => idcheck_result.do?id=admin&pwd=1234 로 넘어가준다. 
		$.ajax({
			type:'post',
			url : '../member/idcheck_result.do',
			data: {"id":id},  //key 와 값  ==> ?id=somthing으로 넘어감
			success:function(result){
				let count = result.trim();
				//정수형 변환 Number,parseInt
				// 0 사용가능
				// 1 사용 불가능 (이미사용중)
				if(count==0){
					let msg='<font color=blue><b>'+id+'는(은) 사용 가능합니다.<b></font>';
/* 					$('result').val("사용가능한 아이디입니다."); //html을 작성하지 않을 것이기 때문에 필요 없다.
 */					
 					$('#result').html(msg);
 					$('#ok').html(
 						'<input type=button value=확인 class="btn btn-sm btn-success" onclick=ok()>'
 					);
 				}else if(count!=0){
					let msg='<font color=red><b>'+id+'는(은) 이미 사용중입니다..<b></font>';
					$('#result').html(msg);
 				}
				
			},
			error:function(e){
				console.log(e);
				alert(e);
			}
		})
		//결과값을 읽어서 처리 
	})
});
function ok() {
	let id = $('#id').val();
	parent.frm.id.value = id;
	parent.Shadowbox.close();
}

</script>
</head>
<body>
	<div class="container">
		<h2 class="text-center"></h2>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력:<input type=text id=id class="input-sm" size=15>
						<input type=button value="아이디체크" class="btn btn-sm btn-primary" id="checkBtn">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<!-- idcheck_result.jsp의 결과값이 처리될 곳 -->
						<span id="result"></span>
					</td>
				</tr>
				<tr>
					<!-- 확인버튼 -->
					<td class="text-center" id="ok">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>


