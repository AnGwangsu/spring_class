<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../style/style.css">
<script src="js/bootstrap.js"></script>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<style>
html, body{
	margin:0;
	padding:0;
	width:100%;
	height:100%;
}
#myCmt{
	font-weight:bold;
	color:red;
}
#cmtModContainer{
	visibility:hidden;
	weigth:100%;
	height:100%;
	z-index:10;
	position:absolute;
	background-color:#E6E6E6;
	display:flex;
	justify-content:center;
	align-items:center;
}
#cmtModWin{
	background-color: white;
	width: 200px;
	height: 200px;
}
</style>
<body>
<jsp:include page="/layout/nav.jsp"/>
<div id="cmtModContainer">
	<div id="cmtModWin">
		<form id="cmtfrm" action="/boardCmt" method="post">
			<input type="hidden" name="i_board" value="${data.i_board }">
			<input type="hidden" name="i_cmt">
			<div><textarea name="cmt"></textarea></div>
			<div><input type="submit" value="수정"></div>
		</form>
		<button onclick="closeCmtModWin()">취소</button>
	</div>
</div>


	<div id="userSession">${loginUser.nm}님 환영합니다.</div>
	<div>
	<div>${msg }</div>
			<div>
				제목:${data.title }
			</div>	
			<div>
				글내용:${data.ctnt }
			</div>
			<div>
				${data.r_dt}  
				<span>조회수:${data.cnt }</span>
				<span>
					<button onclick="doLike(${data.i_board}, ${data.likeUser})">
						<span id="markLike">
							<c:if test="${data.likeUser > 0 }">♥</c:if>
							<c:if test="${data.likeUser == 0 }">♡</c:if>
						</span>
					</button>
				</span>
			</div>		
		<div>
		<c:if test="${loginUser.i_user == data.i_user }">
			<a href="/boardDel?i_board=${data.i_board}" onclick="del()"><button>삭제</button></a>
			<a href="/boardDetail?i_board=${data.i_board}&typ=mod"><button>수정</button></a> <!-- detail의 값을 그대로 수정하기위해서 디테일 페이지를 참조하는데 읽기와 수정을 구분하기 위해 typ값을 줬다. -->
		</c:if>	
			<a href="/boardList"><button>목록</button></a>
		</div>
		
		<div>
		<div id="cmtList">
			<table>
				<thead>
				<tr>
					<th>작성번호</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>비고</th>
				</tr>	
				</thead>
			<c:set var="cmtNo" value="1"/>
			<c:forEach var="item" items="${list }">
				<tbody>
				<tr>
					<td id="${item.i_user == loginUser.i_user ? 'myCmt' :''}">${cmtNo}</td>
					<td>내용:${item.cmt }</td>
					<td>작성자:${item.writeNm }</td>
					<td>작성일:${item.r_dt }</td>
					<td>
						<c:if test="${item.i_user == loginUser.i_user }">
						<!-- detail서블릿에서 담았던 data를 쓰는것 , 둘다 맞아야 삭제가 된다.-->
							<a href="/boardCmt?i_board=${data.i_board }&i_cmt=${item.i_cmt}">삭제</a>
							<a href="#" onclick="showCmtModWin(${item.i_cmt},${item.cmt.trim()})">수정</a>
						</c:if>
					</td>
				</tr>
				</tbody>
				<c:set var="cmtNo" value="${cmtNo +1 }"/>
			</c:forEach>
			</table>
		</div>
			<form action="/boardCmt?i_board=${data.i_board }" method="post">
			<div><input type="hidden" name="i_board" value="${data.i_board }"></div>
				<div>
					<textarea name="cmt"></textarea>
					<span>
						<input type="submit" value="댓글달기">
					</span>
				</div>
			</form>
		</div>
	</div>
	
	
<!-- footer 영역 -->
<jsp:include page="/layout/footer.jsp"/>
	
<script>
function showCmtModWin(i_cmt, cmt){
	cmtModContainer.style.visibility = 'visible'
}
function closeCmtModWin() {
	cmtModContainer.style.visibility = 'hidden'
}

function del(){
	alert('삭제되었습니다.');
}

function doLike(i_board, likeUser) { //내부 통신 ,주소가 변하지는 않음 
	var isLike = (markLike.innerHTML.trim() == '♥' ) ? 1:0   //trim은 공백을 없애준다.
	
	axios.get('/boardLike', { //get방식으로 query스트링으로 보냄
		params: {
			'i_board':i_board, //앞에꺼는 변수명,뒤에는 값이 들어갈 변수(실제 변수명이랑 같아야함)
			'isLike':isLike
		}
	}).then(function(res) {
		/* if(res.data.result == 1) { */ //통신이 잘 처리 됨(서버에선 통신이 잘된지만 확인함) 
			/* if(markLike.innerHTML =='♥' ){
				markLike.innerHTML = '♡'
			}else{
				markLike.innerHTML = '♥'
			} */
			markLike.innerHTML = (markLike.innerHTML.trim() == '♥') ? '♡': '♥'	
		/* } */ /* else {
			markLike.innerHTML = '♡'
		} */ 
		//console.log(JSON.stringify(res))
	})
}
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>	
</body>
</html>