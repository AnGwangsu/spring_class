<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Join Page</title>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>

<body>
<div class="wrapper">
	<div class="container" style="display:flex;">
		<div class="col-8">
			<div class="jumbotron" style="padding-top: 20px; margin-top:20px;">
			<form id="frm" method="post" action="/join" onsubmit="return chk()">
				<h3 style="text-align:center;">회원가입</h3>
				
				
				<div><input type="hidden2" id="checkId" value="2"></div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디 입력" name="cid" maxlength="20" value="${data.cid }" onchange="defaultCheckIdValue()">
					<button onclick="return chkId()">아이디 중복 확인</button>
				</div>
				<div id="duplicationIdMsg"></div>
				
				
				
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호 입력" name="cpw" maxlength="20" value="${data.cpw }">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호 확인" name="cpw" maxlength="20" value="${data.cpw }">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="이름 입력" name="nm" maxlength="20" value="${data.nm }">
				</div>
					남자<input type="radio" class="radio-check" name="gender" value="mail">
					여자<input type="radio" class="radio-check" name="gender" value="femail">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Email 입력" name="email" maxlength="50" value="${data.email }">
				</div> 
				<input type="submit" class="btn  btn-primary form-control" value="회원가입">
			</form>
			</div>
		</div>
	</div>
</div>

<script>
	function defaultCheckIdValue(){
		checkId.value = 2
		duplicationIdMsg.innerHTML = ''
	}
	function chkId() {
		var cid = frm.cid.value
		console.log('cid : ' + cid)
		if(cid.length == 0) {
			alert('아이디를 입력해 주세요')
			return false
		}
		
		axios.get('/chkId', {
		    params: {
		      cid: cid
		    }
		  }).then(function (response) {
		  		
			  
			checkId.value = response.data.isExist
			var msg =''
		    switch(response.data.isExist){
		    	case 1:
		    		msg = '이미 사용 중 입니다.'
		    		break;
		   		 case 0:
		    		msg ='아이디를 사용할 수 있습니다.'
		    		break;  
		    	}
			duplicationIdMsg.innerHTML=msg
			
		  }).catch(function (error) {
		    console.log(error);
		  })
		
		
		return false
	}
	
	function chk() {
		if(checkId.value == 2){
			alert('아이디 중복 확인을 해주세요')
			return false
		}else if(checkId.value == 1){
			alert('다른 아이디를 사용해 주세요')
			return false
		}else if(frm.cid.value.length == 0) {
			alert('아이디를 입력해 주세요.')
			frm.cid.focus()
			return false
		} else if(frm.cpw.value == '') {
			alert('비밀번호를 입력해 주세요.')
			frm.cpw.focus()
			return false
		} else if(frm.cpw.value != frm.recpw.value) {
			alert('비밀번호를 확인해 주세요.')
			frm.cpw.focus()
			return false
		} else if(frm.nm.value.length == 0) {
			alert('이름을 입력해 주세요.')
			frm.nm.focus()
			return false
		}			
	}
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>	
</body>
</html>