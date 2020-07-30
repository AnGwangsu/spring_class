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
<title>Lecture</title>
</head>
<body>

<jsp:include page="/layout/nav.jsp"/>
<!-- lecture page-->
<div class="row">
      <div class="col-12">
      <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <strong>튜터 모집!</strong>재능을 나누어 봐요.
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      </div>
 </div>
<section>
	<div class="card" style="width: 18rem;">
	  <img src="images/cooking.jpg" class="card-img-top" alt="쿠킹클래스">
	  <div class="card-body">
	    <h5 class="card-title">Cooking Class</h5>
	    <p class="card-text">훌륭한 쉐프를 주변에서 만나보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/course5.jpg" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">MakeUp Class</h5>
	    <p class="card-text">멋진 메이크업 아티스트를 주변에서 만나보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/DIY.jpg" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">DIY Class</h5>
	    <p class="card-text">나만의 가구를 만드는것을 배워보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/sports.jpg" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">Sports Class</h5>
	    <p class="card-text">다같이 땀을 흘리며 재밌는 스포츠를 즐겨보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/design.jpg" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">Design Class</h5>
	    <p class="card-text">나만의 브랜드를 디자인 해보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/guitar.jpg" class="card-img-top" alt="기타 클래스">
	  <div class="card-body">
	    <h5 class="card-title">Guitar Class</h5>
	    <p class="card-text">멋진 기타 아티스트가 되어보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/vocal.jpg" class="card-img-top" alt="보컬 클래스">
	  <div class="card-body">
	    <h5 class="card-title">Vocal Class</h5>
	    <p class="card-text">3단고음이 가능한 보컬리스트가 되어보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/course4.jpg" class="card-img-top" alt="웹 프로그래밍 클래스">
	  <div class="card-body">
	    <h5 class="card-title">Web Programming Class</h5>
	    <p class="card-text">코딩 이제 어렵지 않아요 유능한 웹 프로그래머가 되어보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>
	<div class="card" style="width: 18rem;">
	  <img src="images/course8.jpg" class="card-img-top" alt="꽃꽂이 클래스">
	  <div class="card-body">
	    <h5 class="card-title">Flower Class</h5>
	    <p class="card-text">예쁜 꽃을 만들어 소중한 사람에게 선물해 보세요.</p>
	    <a href="/lectureDetail" class="btn btn-primary">수강 신청</a>
	  </div>
	</div>	
</section>
	


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>		
</body>
</html>