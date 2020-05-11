<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${vo.msg=='NOID'}">
	<%-- id가 없는 상태 --%>
		<script>
			alert("ID가 존재 하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${vo.msg=='NOPWD'}">
	<%-- 비밀번호가 틀린 상태 --%>
		<script>
			alert("비밀번호가 틀립니다!!");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<c:if test="${vo.admin=='y'}">
			<c:redirect url="admin.do"/>
		</c:if>
		<c:if test="${vo.admin!='y'}">
			<c:redirect url="reserve.do"/>
		</c:if>
	</c:otherwise>
</c:choose>