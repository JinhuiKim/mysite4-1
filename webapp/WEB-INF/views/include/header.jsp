<%@page import="kr.ac.sungkyul.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<div id="header">
		<h1>MySite</h1>
		<ul>
			<c:choose>
				<c:when test='${not empty sessionScope.authUser }'>
					<li><a href="/mysite4/user/modifyform">회원정보수정</a></li>
					<li><a href="/mysite4/user/logout">로그아웃</a></li>
					<li>${sessionScope.authUser.name }님안녕하세요 ^^;</li>
				</c:when>
				<c:otherwise>
					<li><a href="/mysite4/user/loginform">로그인</a></li>
					<li><a href="/mysite4/user/joinform">회원가입</a></li>
				</c:otherwise>

			</c:choose>
		</ul>
	</div>




</body>
</html>