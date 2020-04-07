 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Patch Maker 1.0</title>

<link rel="icon" type="image/png" href="resources/assets/img/logo.png">

<!-- Order of import is very important in below imports, bootstrap.min.css should always be imported first -->
<link href="<c:url value="/resources/assets/style/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/assets/style/patchMaker.css" />" rel="stylesheet"> 
 
<!-- Order of import is very important in below imports, jquery.min.js should always be imported first -->
<script src="<c:url value="/resources/assets/script/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/script/bootstrap.min.js" />"></script> 
<script src="<c:url value="/resources/assets/script/patchMaker.js" />"></script>

<body onload="executeAll();">

</head>
<body>
<body style=" background:#C0C0C0;" >


<jsp:include page='header.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include>

<div class="container center" style=" background:#ffffff; border-radius: 6px; margin: auto; padding: 10px;"> 

 
  <h1 class="page-header center" align="center">Patch Maker 1.0 </h1>  
  
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
	                            <label for="date"><h4>Features</h4> </label><br/>
	                              <textarea id="features" rows="2" cols="58"></textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Impact On Existing Functionality</h4></label>
	                            <textarea id="impact" rows="2" cols="61">Not Applicable</textarea>
	                     </div>
					</div>
               </div>
	       </div>
	        <div class="row">  
	     	 <div class="col-md-12">
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>New Functionality Added</h4></label>
	                              <textarea id="newFunctionality" rows="2" cols="58">None</textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Defect Fixed</h4></label>
	                            <textarea id="defectsFixed" rows="2" cols="61"></textarea>
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
							<label for="date"><h4>Output</h4></label><br/>
							<textarea id="installSteps" rows="4" cols="61">
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
							<label for="date"><h4>Output</h4></label><br/>
							<textarea id="rollbackSteps" rows="4" cols="61">
							</textarea>
	                     </div>
               </div>
	       </div>
	       <div class="row">  
	     	 <div class="col-md-12">
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Known Bugs</h4></label>
	                            <textarea id="knownBugs" rows="2" cols="58">None</textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Risk if Any?</h4></label>
	                            <textarea id="riskAny" rows="2" cols="61" >None</textarea>
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