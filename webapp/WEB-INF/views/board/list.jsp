<%@page import="kr.ac.sungkyul.mysite.vo.UserVo"%>
<%@page import="kr.ac.sungkyul.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/bs?a=list&no=1" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:forEach var='vo' items='${list }' varStatus='s'>

						<tr>
							<td>[${fn:length(list) - s.index }]</td>
							<td style="text-align:left;padding-left:${(vo.depth-1)*10}px">
							<c:if test='${vo.depth >1 }'>
							<h3>→</h3>
							<!--  <img src=""> -->
							</c:if>
							<td><a href="/mysite/bs?a=view&no=${vo.no }">${vo.title}</a></td>
							<td>${vo.name}</td>
							<td>${vo.viewNo}</td>
							<td>${vo.regDate}</td>

							<td><c:if
									test='${not empty authUser && (vo.userNo == authUser.no) }'>
									<a href="/mysite/bs?a=delete&no=${vo.no}" class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>

				</table>

				<!-- begin:paging -->
				<div class="pager">
					<ul>
						<c:if test='${prePage}'>
						<li><a href="/mysite/bs?a=list&no=${sPage-1 }&kwd=${kwd}">◀</a></li>
						</c:if>
						
							<c:forEach begin='${sPage }' end='${ePage }' step='1' var='i' >
								<c:if test='${ param.no==i }'>
								<li class="selected">${i }</li>
								</c:if>
								<c:if test='${param.no != i }'>
								<li><a href="/mysite/bs?a=list&no=${i }&kwd=${kwd}">${i }</a></li>
								</c:if>
							</c:forEach>		
						<c:if test='${nextPage}'>
						<li><a href="/mysite/bs?a=list&no=${ePage+1 }&kwd=${kwd}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<!-- end:paging -->
				<c:if test='${not empty sessionScope.authUser }'>

					<div class="bottom">
						<a href="/mysite/bs?a=writeform" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>


		<c:import url='/WEB-INF/views/include/navi.jsp' />
		<c:import url='/WEB-INF/views/include/footer.jsp' />
	</div>
</body>
</html>