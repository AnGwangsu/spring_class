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
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	 <a class="navbar-brand" href="index.jsp">Talent Donation</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="/index">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/lecture">Lecture</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/boardList">Community</a>
	      </li>
	     </ul>
	   	 <ul class="nav navbar-nav navbar-right">
      		<li class="dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="dropdown-togle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          		MemberShip<span class="caret"></span>
        		</a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
         	<li class="dropdown-item">
         		<c:if test="${loginUser.cid eq null }"><a href="/login" style="text-decoration:none ; color:black;">Login</a></c:if>
         		<c:if test="${loginUser.cid ne null }"><a href="/logout" style="text-decoration:none ; color:black;">Logout</a></c:if>
         	<li>
          	<li class="dropdown-item">
          		<c:if test="${loginUser.cid eq null }"><a href="/join" style="text-decoration:none; color:black;">Join</a></c:if>
         		<c:if test="${loginUser.cid ne null }"><a href="/myLecture" style="text-decoration:none; color:black;">My Lecture</a></c:if>
          	<li>
          </ul>
      	</li>
    	</ul>
 	  <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
	


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>		
</body>
</html>