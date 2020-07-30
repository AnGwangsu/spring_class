<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../style/style.css">
<script src="js/bootstrap.js"></script>
<title>BoardList</title>
</head>
<style>
	#myCtnt{
		color:#1abc9c;
	}
	.boardItem:hover{
	background-color:#bdc3c7;
	cursor:pointer;
}
#userSession{
	text-align:right;
}
</style>
<body>
<%-- <c:import url="/layout/nav.jsp" var="navbar">
</c:import>
${navbar } --%>
		<div id="userSession">
			${loginUser.nm}님 환영합니다.
		</div>
		<a href="/myPage?typ=1"><button>비밀번호 수정</button></a>
		<form action="/boardList" method="post">
			<input type="submit" value="로그아웃">			
		</form>
		<div class="container">
		<div class = "row"> <!-- 테이블이 들어가도록  -->
			<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">   
				<thead> <!-- 테이블의 제목 -->
					<tr><!-- 하나의 행(한줄) -->
						<th style="background-color: #eeeeee; text-align: center;">번호</th> 
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회수</th>
						<th style="background-color: #eeeeee; text-align: center;">Like</th>
						<th style="background-color: #eeeeee; text-align: center;">댓글 수</th>
					</tr>
				</thead>
				<tbody>	<!-- 실제로 보이게 될 게시판 글들 -->
				<c:forEach var="item" items="${data }"> <!-- jstl에서 forEach는 반복문 -->
					<tr class="boardItem" onclick="moveToDetail(${item.i_board})">
						<td id="${item.i_user == loginUser.i_user ?'myCtnt':''}">${item.i_board }</td>
						<td>${item.title }</td>
						<td>${item.userNm }</td><!-- 변수명이랑 같이 적어줘야 호출을 할 수 있다. -->
						<td>${item.r_dt }</td>
						<td>${item.cnt }</td>
						<td>
							<c:if test="${item.likeUser == 0}">♡</c:if>
							<c:if test="${item.likeUser >0}">♥</c:if>
						</td>
						<td></td>
					</tr>	
				</c:forEach>	
				</tbody>
			</table>	
			<div id="writeButton"><a href="/boardRegMod"><button>글쓰기</button></a></div>
		</div>
	</div>
	<div style="text-align:center; margin-top:10px;">
	 <!-- i가 totalPageCnt값과 같아질때까지 for문을 돌림 -->
		<c:forEach begin="1" end="${totalPageCnt }" var="i">
		<span style="margin-right:5px; font-weight:bold; font-size:20px;">
			<a href="/boardList?page=${i}" style="text-decoration:none; color:black;">
				${i }
			</a>
		</span>
		</c:forEach>
	</div>
<script>
	function moveToDetail(i_board){
		location.href='/boardDetail?i_board='+i_board
	}
	
</script>	
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>	
</body>	
</body>
</html>