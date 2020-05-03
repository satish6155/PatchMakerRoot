<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"> 
 <head>
 <title>About Us</title>
 <link href="<c:url value="/resources/assets/style/bootstrap.min.css" />" rel="stylesheet"> 

 <style>
 
  #formContent {
  -webkit-border-radius: 10px 10px 10px 10px;
  border-radius: 10px 10px 10px 10px;
  background: #fff;
  padding: 30px;
  width: 90%;
  max-width: 200px;
  position: relative;
  padding: 50px;
  -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
  box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
  text-align: center;
}
 
 .package {
  color: #777;
  padding: 40px;
  border-radius: 7px; }
  
  .text-teal {
  color: #16c3b0; }
  
  /* .package .img-wrap {
    display: block;
    margin: 0 auto 30px auto;
    width: 80px; }
    
    .package .img-wrap img {
      width: 80px; } */
      
  /*     h1, h2, h3, h4, h5,
.h1, .h2, .h3, .h4, .h5 {
  font-weight: 300;
  color: #364d59; } */
  
  .text-success {
  color: #71bc42!important;
}

.text-danger {
  color: #dc3545!important;
}

/* .text-center {
  text-align: center!important;
}  */ 

.bg-white {
  background-color: #fff!important;
}

.img-fluid {
  max-width: 100%;
  height: auto;
}
.solid {border-style: solid;}

  .package h3 {
    font-size: 18px;
    margin-bottom: 30px;
   }
    
/* .package .img-wrap {
    display: block;
    margin: 0 auto 30px auto;
    width: 80px; } */ 
    
/*  .package .img-wrap img {
      width: 50px; } */

 .row {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
/*   margin-right: -15px;*/
  margin-left: 70px; 
 /*  margin-top: -300px; */
} 

.col-lg-4_1 {
    -webkit-box-flex: 0;
    -ms-flex: 0 0 33.33333%;
    flex: 0 0 33.33333%;
    max-width: 25%;
    position: relative;
  	width: 100%;
  	padding-right: 15px;
  	padding-left: 15px;
  	margin-left: 50px;
  	margin-top: 75px;
  }
  
  .mb-4 {
  margin-bottom: 1.5rem!important;
}

.mb-lg-0 {
    margin-bottom: 0!important;
  } 
  
  
 
/*  .middle{
 width:80px; 
 margin:0 auto;
 } */

/* .container{
	/* margin-right: -15px; */
  	/* margin-left: 150px; 
  }  */ 
 </style>
 
 </head>
<body>
<jsp:include page='header.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include>

<div class = "container row solid" align = "center"> <!-- align = "center" -->
<div class="col-lg-4_1 mb-4 mb-lg-0">
<div class="package text-center bg-white solid" id = "formContent">
<span class="img-wrap"><img src="<%=request.getContextPath()%>/resources/assets/img/logo.png" alt="Image" class="img-fluid"></span>
<h3 class="text-teal">Deepali Jain</h3>
<p>Lorem ipsum dolor sit amet. Consequatur aliquam, fuga maiores amet quo corporis distinctio soluta recusandae?</p>
<p>Lorem ipsum dolor sit amet. Consequatur aliquam, fuga maiores amet quo corporis distinctio soluta recusandae?</p>
</div>
</div>
 
<div class="col-lg-4_1 mb-4 mb-lg-0">
<div class="package text-center bg-white solid" id="formContent">
<span class="img-wrap"><img src="<%=request.getContextPath()%>/resources/assets/img/logo.png" alt="Image" class="img-fluid"></span>
<h3 class="text-success">Satish Verma</h3>
<p>Lorem ipsum dolor sit amet. Consequatur aliquam, fuga maiores amet quo corporis distinctio soluta recusandae?</p>
<p>Lorem ipsum dolor sit amet. Consequatur aliquam, fuga maiores amet quo corporis distinctio soluta recusandae?</p>
<!-- <p><a href="https://colorlib.com/preview/theme/kiddy/#" class="btn btn-warning btn-custom-1 mt-4">Learn More</a></p> -->
</div>
</div>

<div class="col-lg-4_1 mb-4 mb-lg-0">
<div class="package text-center bg-white solid" id="formContent">
<span class="img-wrap"><img src="<%=request.getContextPath()%>/resources/assets/img/logo.png" alt="Image" class="img-fluid"></span>
<h3 class="text-danger">Deepika Varshney</h3>
<p>Lorem ipsum dolor sit amet. Consequatur aliquam, fuga maiores amet quo corporis distinctio soluta recusandae?</p>
<p>Lorem ipsum dolor sit amet. Consequatur aliquam, fuga maiores amet quo corporis distinctio soluta recusandae?</p>
<!-- <p><a href="https://colorlib.com/preview/theme/kiddy/#" class="btn btn-success btn-custom-1 mt-4">Learn More</a></p> -->
</div>
</div>

 </div>
 </body>
 </html>