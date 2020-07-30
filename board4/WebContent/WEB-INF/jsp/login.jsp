<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Login Page</title>
</head>
<body>
<div class="wrapper">
	<div>${msg }</div>
	<div class="container" style="display:flex;">
		<div class="col-8">
			<div class="jumbotron" style="padding-top: 20px; margin-top:20px;">
			<form method="post" action="/login">
				<h3 style="text-align:center;">Login</h3>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디" name="cid" maxlength="20" value="${data.cid }">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호" name="cpw" maxlength="20">
				</div>
				<input type="submit" class="btn  btn-primary form-control" value="로그인">
			</form>
			</div>
		</div>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>		
</body>
</html>