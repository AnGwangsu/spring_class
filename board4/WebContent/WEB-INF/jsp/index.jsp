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
<title>Main</title>
</head>
<body>
<c:import url = "/layout/nav.jsp" var = "navbar">
</c:import>
${navbar }

<!-- 메인 화면 슬라이드 쇼 -->
 <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item item1 active">
        <div class="helper"></div>
        <div class="intro">
          <h2>나만의 멋진 아이템을 만들어보세요</h2>
          <h3>주변의 멋진 전문가들이 많습니다.</h3>
          <a href="/lecture">수업 찾기</a>
        </div>
      </div>
      <div class="carousel-item item2">
        <div class="helper"></div>
        <div class="intro">
          <h2>메이크업 배워보세요</h2>
          <h3>멋진 메이크업 아티스트들이 주변에 있습니다.</h3>
          <a href="/lecture">수업 찾기</a>
        </div>
      </div>
      <div class="carousel-item item3">
        <div class="helper"></div>
        <div class="intro">
          <h2>아이돌 댄스를 정복해보세요</h2>
          <h3>열정하나면 충분히 가능합니다.</h3>
          <a href="/lecture">수업 찾기</a>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>

<!-- preview content -->
<section>
	<section>
		
	</section>
	<section>
		
	</section>
</section>

<!-- footer 영역 -->
<c:import url = "/layout/footer.jsp" var = "footer">
</c:import>
${footer }

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>	
</body>
</html>