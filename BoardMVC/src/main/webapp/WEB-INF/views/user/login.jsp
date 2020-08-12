<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<div>${msg }</div>
	<form action="/user/loginPost" method="post">
		<div><input type="text" name="cid" placeholder="아이디"></div>
		<div><input type="password" name="cpw" placeholder="비밀번호"></div>
		<div><input type="submit" value="로그인"></div>
	</form>
	<div><a href="/user/join"><button>회원가입</button></a></div>
	<div>
		<a href="/user/loginKAKAO">카카오 로그인</a>
	</div>
</body>
</html>