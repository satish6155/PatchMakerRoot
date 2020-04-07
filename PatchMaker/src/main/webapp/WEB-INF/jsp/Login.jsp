 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html lang="en"> 
 <head>
 <title>Patch Maker 1.0</title>

<link rel="icon" type="image/png" href="resources/assets/img/logo.png">

 
<!-- Order of import is very important in below imports, bootstrap.min.css should always be imported first -->
<link href="<c:url value="/resources/assets/style/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/assets/style/login.css" />" rel="stylesheet"> 
 
<!-- Order of import is very important in below imports, jquery.min.js should always be imported first -->
<script src="<c:url value="/resources/assets/script/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/script/bootstrap.min.js" />"></script> 
<script src="<c:url value="/resources/assets/script/login.js" />"></script>
 
 <style>

</style>


<script>
   $(document).ready(function()  {
	   
	   document.querySelector('.user-options').innerHTML ='';

	     var isValidUser;
  
	     if(null != <%=session.getAttribute("isValidUser")%>){

		 isValidUser = <%=session.getAttribute("isValidUser")%>;

     }	     
	    var errorMsg = "<%=session.getAttribute("errorMsg")%>";

    if(isValidUser == false && errorMsg != "null"){
    	 
    	document.getElementById("error").innerHTML=errorMsg;
	   	
	   	<% session.setAttribute("errorMsg",null); 	%>
	  
	 }
});
</script>

</head>

<body>
<jsp:include page='header.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include>


<div class="container"  style="text-align: center;">
<br/><br/>
  <div style="display: inline-block;" id="formContent">
    
    <!-- Login Form -->
    <form action="login" method="post">
    
    <span id="error" style ="color:red"></span>
      <input type="text" id="username" class="fadeIn second" name="username" placeholder="username" required>
      <input type="password" id="password" name="password" placeholder="password" required>
     <input type="submit" class="fadeIn fourth" value="Log In">
      
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Nucleus Software Exports Ltd. 2020</a>
    </div>

  </div>
</div>
</body>