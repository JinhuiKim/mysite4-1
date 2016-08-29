<%@page import="kr.ac.sungkyul.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">

<c:if test='${param.res =="success" }'>
	<script>
		alert("성공적으로 사용자 정보를 수정하였습니다.");
	</script>
</c:if>

</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/include/header.jsp' />
		<div id="content">
			<div id="user">

				<form id="join-form" name="modifyForm" method="post"
					action="/mysite/user?a=modify">
					<input type="hidden" name="a" value="modify"> <label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${userVo.name}"> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<c:choose>

							<c:when test='${userVo.gender =="female" }'>
								<label>여</label>
								<input type="radio" name="gender" value="female" checked="checked">
							</c:when>
							<c:otherwise>
								<label>남</label>
								<input type="radio" name="gender" value="male">
							</c:otherwise>
						</c:choose>

					</fieldset>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp' />
		<c:import url='/WEB-INF/views/include/footer.jsp' />
	</div>
</body>
</html>