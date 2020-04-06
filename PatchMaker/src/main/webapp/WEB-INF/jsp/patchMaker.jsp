<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Patch Maker 1.0</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>

 html {
  background-color: #D7D9DC;
} 

.center {
  margin: auto;
  width: 70%;
  padding: 10px;
}


/* css of header.jsp */

@import "https://fonts.googleapis.com/css?family=Merienda+One";
@import "https://fonts.googleapis.com/icon?family=Material+Icons";
@import "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css";
@import "https://fonts.googleapis.com/css?family=Merienda+One";
@import "https://fonts.googleapis.com/css?family=Merienda+One";

a {
	text-decoration: none;
	color: #0062cc;
	border-bottom: 0px;
}

.form-inline {
	display: inline-block;
}

.navbar-header.col {
	padding: 0 !important;
}

.navbar {
	background: #fff;
	padding-left: 16px;
	padding-right: 16px;
	border-bottom: 1px solid #d6d6d6;
	box-shadow: 0 0 4px rgba(0, 0, 0, .1);
}

.nav-link img {
	border-radius: 50%;
	width: 36px;
	height: 36px;
	margin: -8px 0;
	float: left;
	margin-right: 10px;
}

.navbar .navbar-brand {
	color: #555;
	padding-left: 0;
	padding-right: 50px;
	font-family: 'Merienda One', sans-serif;
}

.navbar .navbar-brand i {
	font-size: 20px;
	margin-right: 5px;
}

.search-box {
	position: relative;
}

.search-box input {
	box-shadow: none;
	padding-right: 35px;
	border-radius: 3px !important;
}

.search-box .input-group-addon {
	min-width: 35px;
	border: none;
	background: transparent;
	position: absolute;
	right: 0;
	z-index: 9;
	padding: 7px;
	height: 100%;
}

.search-box i {
	color: #a0a5b1;
	font-size: 19px;
}

.navbar .nav-item i {
	font-size: 18px;
}

.navbar .dropdown-item i {
	font-size: 16px;
	min-width: 22px;
}

.navbar .nav-item.open>a {
	background: none !important;
}

.navbar .dropdown-menu {
	border-radius: 1px;
	border-color: #e5e5e5;
	box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
}

.navbar .dropdown-menu li a {
	color: #777;
	padding: 8px 20px;
	line-height: normal;
}

.navbar .dropdown-menu li a:hover, .navbar .dropdown-menu li a:active {
	color: #333;
}

.navbar .dropdown-item .material-icons {
	font-size: 21px;
	line-height: 16px;
	vertical-align: middle;
	margin-top: -2px;
}

.navbar .badge {
	background: #f44336;
	font-size: 11px;
	border-radius: 20px;
	position: absolute;
	min-width: 10px;
	padding: 4px 6px 0;
	min-height: 18px;
	top: 5px;
}

.navbar ul.nav li a.notifications, .navbar ul.nav li a.messages {
	position: relative;
	margin-right: 10px;
}

.navbar ul.nav li a.messages {
	margin-right: 20px;
}

.navbar a.notifications .badge {
	margin-left: -8px;
}

.navbar a.messages .badge {
	margin-left: -4px;
}

.navbar .active a, .navbar .active a:hover, .navbar .active a:focus {
	background: transparent !important;
}

@media ( min-width : 1200px) {
	.form-inline .input-group {
		width: 300px;
		margin-left: 30px;
	}
}

@media ( max-width : 1199px) {
	.form-inline {
		display: block;
		margin-bottom: 10px;
	}
	.input-group {
		width: 100%;
	}
} 

</style>

<body onload="executeAll();">

<script type="text/javascript">

function executeAll(){
	SetDate();
	SetJiraType();
}
function SetDate()
{
var date = new Date();

var day = date.getDate();
var month = date.getMonth() + 1;
var year = date.getFullYear();

if (month < 10) month = "0" + month;
if (day < 10) day = "0" + day;

var today = year + "-" + month + "-" + day;
document.getElementById('date').value = today;
}

function SetJiraType(){
	document.getElementById('bankJiraId').value = document.getElementById('bankProject').value+"-";
	SetFeature();
}
var installSeq=1;
var InstallSteps = [];
function SetInstallSteps(){
	var newText = document.getElementById('installStepOptions').value;
	if("Select Steps"!=newText ){
		if(document.getElementById("installSteps").innerHTML=="							" || document.getElementById("installSteps").innerHTML==""){
			document.getElementById("installSteps").innerHTML = installSeq+". " +newText + "\n";
		}
		else{
			document.getElementById("installSteps").innerHTML += installSeq+". " +newText + "\n"  ;
		}
		InstallSteps.push(document.getElementById("installSteps").innerHTML);
	 installSeq++;
	}	
}
function removeInstallStep(){
	InstallSteps.pop();
	if(installSeq>1){
		installSeq--;
		if(InstallSteps.length==0)
			document.getElementById("installSteps").innerHTML ="";
		else
		document.getElementById("installSteps").innerHTML = InstallSteps[InstallSteps.length-1];
	}
	else{
		document.getElementById("installSteps").innerHTML="";
	}		
}

var rollbackSeq=1;
var rollbackSteps = [];
function SetRollbackSteps(){
	var newText = document.getElementById('rollbackStepOptions').value;
	if("Select Steps"!=newText ){
		if(document.getElementById("rollbackSteps").innerHTML=="							" || document.getElementById("rollbackSteps").innerHTML==""){
			document.getElementById("rollbackSteps").innerHTML = rollbackSeq+". " +newText + "\n";
		}
		else{
			document.getElementById("rollbackSteps").innerHTML += rollbackSeq+". " +newText + "\n"  ;
		}
		rollbackSteps.push(document.getElementById("rollbackSteps").innerHTML);
		rollbackSeq++;
	}	
}
function removeRollbackStep(){
	rollbackSteps.pop();
	if(rollbackSeq>1){
		rollbackSeq--;
		if(rollbackSteps.length==0)
			document.getElementById("rollbackSteps").innerHTML ="";
		else
		document.getElementById("rollbackSteps").innerHTML = rollbackSteps[rollbackSteps.length-1];
	}
	else{
		document.getElementById("rollbackSteps").innerHTML="";
	}		
}
function SetFeature(){
	document.getElementById("features").innerHTML ='This patch include fix for jira '+document.getElementById("bankJiraId").value;
	document.getElementById("defectsFixed").innerHTML ='Defect Fixed as per '+document.getElementById("bankJiraId").value;
	
}

</script>

</head>
<body>

<nav class="navbar navbar-default navbar-expand-xl navbar-light">
		<div class="navbar-header d-flex col">
			<a class="navbar-brand" href="#"><i class="fa fa-cube"></i>SVN<b>Patch Maker</b></a>
			<button type="button" data-target="#navbarCollapse"
				data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
				<span class="navbar-toggler-icon"></span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<!-- Collection of nav links, forms, and other content for toggling -->
		<div id="navbarCollapse"
			class="collapse navbar-collapse justify-content-start">
			<ul class="nav navbar-nav">
				<li class="nav-item active"><a href="DashboardNew.jsp"
					class="nav-link">Home</a></li>
				<li class="nav-item"><a href="#" class="nav-link">Log Out</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right ml-auto">
		
				<li class="nav-item dropdown"><a href="#"
					data-toggle="dropdown" class="nav-link dropdown-toggle user-action">
					<img
						src="<%=request.getContextPath()%>/resources/images/MyImage.jpg" class="avatar" alt="User"><%=session.getAttribute("username")%>
						<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="UserProfile.jsp" class="dropdown-item"><i
								class="fa fa-user-o"></i> Profile</a></li>
		
						<li class="divider dropdown-divider"></li>
						<li><a href="<%=request.getContextPath()%>/logout" class="dropdown-item"><i
								class="material-icons">&#xE8AC;</i> Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
<%-- <jsp:include page='header.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include> --%>
<div class="container center" style="border:.5px solid red"> 
 
  <h1 class="page-header center" align="center">Patch Maker 1.0</h1>  
  
    <div class="col-md-12 col-sm-6 col-xs-12"> 
    <br/>
	      <div class="alert alert-info alert-dismissable">
	        <a class="panel-close close" data-dismiss="alert">×</a> 
	        <i class="fa fa-coffee"></i>
	        This is an <strong>.alert</strong>. Use this to show important messages to the user.
	      </div>
	  </div>    
	       <form class="form" action="##" method="post" id="registrationForm">
	        <div class="row">  
	     	 <div class="col-md-12">
	      		<div class="form-group col-md-4">
							 <label for="first_name"><h4>Project</h4></label>
							  <select class="form-control" id="project">
							    <option>P-1605-HDFC</option>
							   <!--  <option>Manager</option>   --> 							   
							  </select>
				</div>
				<div class="form-group col-md-4">
							 <label for="first_name"><h4>Environment</h4></label>
							  <select class="form-control" id="bankProject" onClick="SetJiraType()">
							    <option value="NEOPROD">NEOPROD</option>
							    <option value="NEOSFR">NEOSFR</option>   							   
							  </select>
				</div>
				 <div class="form-group">                          
                          <div class="col-md-4">
                            <label for="date"><h4>Date</h4></label>
                              <input type="date" class="form-control" name="date" id="date" placeholder="Date" title="Date">
                          </div>
              	 </div>
               </div>
	       </div>
	        <div class="row">  
	     	 <div class="col-md-12">
	      		<div class="form-group col-md-4">
					<div>
                            <label for="date"><h4>Bank Jira ID</h4></label>
                              <input type="text" class="form-control" name="bankJiraId" id="bankJiraId" onchange="SetFeature()">
                     </div>
				</div>
				<div class="form-group col-md-4">
					<div>
                            <label for="date"><h4>Product Jira ID</h4></label>
                              <input type="text" class="form-control" name="productJiraId" id="productJiraId"  value="CAS-">
                     </div>
				</div>
				<div class="form-group col-md-4">
					<div>
                            <label for="date"><h4>Internal Jira ID</h4></label>
                              <input type="text" class="form-control" name="internalJiraId" id="internalJiraId"   value="PDIMP-">
                     </div>
				</div>
               </div>
	       </div>
	       <br/>
	       <div class="row">  
	     	 <div class="col-md-12">
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Features</h4></label>
	                              <textarea id="features" rows="2" cols="50"></textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Impact On Existing Functionality</h4></label>
	                            <textarea id="impact" rows="2" cols="52">Not Applicable</textarea>
	                     </div>
					</div>
               </div>
	       </div>
	        <div class="row">  
	     	 <div class="col-md-12">
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>New Functionality Added</h4></label>
	                              <textarea id="newFunctionality" rows="2" cols="50">None</textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Defect Fixed</h4></label>
	                            <textarea id="defectsFixed" rows="2" cols="52"></textarea>
	                     </div>
					</div>
               </div>
	       </div>
	       <div class="row">  
	     	 <div class="col-md-6">
		      		 <div class="form-group col-md-9">
						<div>
	                            <label for="date"><h4>Installations Steps</h4></label>
	                            <select class="form-control" id="installStepOptions" onchange="SetInstallSteps()">
	                            <option>Select Steps</option>
							    <option>Take back up of running war</option>
							    <option>Merge class files</option>   
							    <option>Take restart</option> 	
							    <option>Set hbm2ddl=update</option> 
							    <option>Execute provide script</option> 
							    <option>Custom</option> 						   
							  </select>
	                     </div>
					</div>
					<div class="form-group  col-md-3">      
                              <label for="zipcode"><h4>&nbsp</h4></label>
                              <input type="button" value="Remove" class="form-control btn-danger" onClick="removeInstallStep()">
                         
                      </div>
				</div>
				<div class="col-md-6">
						<div>
							<label for="date"><h4>Output</h4></label>
							<textarea id="installSteps" rows="4" cols="52">
							</textarea>
	                     </div>
               </div>
	       </div>
	       <div class="row">  
	     	 <div class="col-md-6">
		      		 <div class="form-group col-md-9">
						<div>
	                            <label for="date"><h4>Rollback Steps</h4></label>
	                            <select class="form-control" id="rollbackStepOptions" onchange="SetRollbackSteps()">
	                            <option>Select Steps</option>
							    <option>Replace with previous version of War</option>
							    <option>Execute the rollback script(s)</option>   
							  </select>
	                     </div>
					</div>
					<div class="form-group  col-md-3">      
                              <label for="zipcode"><h4>&nbsp</h4></label>
                              <input type="button" value="Remove" class="form-control btn-danger" onClick="removeRollbackStep()">
                         
                      </div>
				</div>
				<div class="col-md-6">
						<div>
							<label for="date"><h4>Output</h4></label>
							<textarea id="rollbackSteps" rows="4" cols="52">
							</textarea>
	                     </div>
               </div>
	       </div>
	       <div class="row">  
	     	 <div class="col-md-12">
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Known Bugs</h4></label>
	                            <textarea id="knownBugs" rows="2" cols="50">None</textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Risk if Any?</h4></label>
	                            <textarea id="riskAny" rows="2" cols="52" >None</textarea>
	                     </div>
					</div>
               </div>
	       </div>
	              	 
	      	 </form>
	      	 <a>Guru File Upload:</a>
Select file: <br />
<form action="UploadFile.jsp" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
     
     	
    </div>
<jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include>
</body>
</html>                                		                            