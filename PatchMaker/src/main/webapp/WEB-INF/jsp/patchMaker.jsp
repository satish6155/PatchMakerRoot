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
<link href="<c:url value="/resources/assets/style/patchMaker.css"/>" rel="stylesheet"> 

<link href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">
 
<!-- Order of import is very important in below imports, jquery.min.js should always be imported first -->
<script src="<c:url value="/resources/assets/script/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/script/bootstrap.min.js" />"></script> 
<script src="<c:url value="/resources/assets/script/patchMaker.js" />"></script>

<style>

body {
	/* background-color: #D7D9DC !important; */
	
	background-color: #f7f7f7;
}

/* file list style starts here */

/* .clearfix::after {
    content: "";
    display: table;
    clear: both;
}
 */

.btn {
 
 font-weight: bold;
 
 }

.right { float: right; }
.red { color: #FF5049 !important; }
.red-focus:focus { border: 1px solid #FF5049 !important; }


.files {
    float: left;
    width: 475px;
    margin-right: 50px;
}

.item {
    padding: 13px;
    border-bottom: 1px solid #e7e7e7;
}

.item:first-child { border-top: 1px solid #e7e7e7; }
.item:nth-child(even) { background-color: #f7f7f7; }

.item__description {
    float: left;
}

.item__value {
    float: left;
    transition: transform 0.3s;
}

.files .item__value,
.files .item__delete--btn {
    color: #28B9B5;
}


.item__delete {
    float: left;
}

.item__delete--btn {
    font-size: 22px;
    background: none;
    border: none;
    cursor: pointer;
    display: inline-block;
    vertical-align: middle;
    line-height: 1;
    display: block;
    margin-left: 50px;
}

/*  .item__delete--btn:focus { outline: none; }
.item__delete--btn:active { transform: translateY(2px); }

.item:hover .item__delete--btn { display: block; }
.item:hover .item__value { transform: translateX(-20px); }
.item:hover .item__percentage { transform: translateX(-20px); }
 */


/* file list style ends here */

.title {
	color: #555;
	font-family: sans-serif;
	margin-bottom:-15px;
	margin-top: -1px;
}
.testDiv {
	border: 1px;
	border-color: coral;
	border-style: solid;
}
.main {
 background:#ffffff;  
 border-radius: 6px; 
 /*
 border: 1px; 
 border-color: gray; 
 border-style: solid;  
 margin: auto; */
 padding: 10px;
 margin-top:0px;
}

.form-control,textarea {
border: .7px;	
border-color: gray; 
border-style: solid;
border-radius: 3px;
width:100%
}

 .required:after {
    content:" *";
    color: red;
  }

</style>

<body onload="executeAll();">
</head>
<body>


<jsp:include page='header.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include>

<div class="container main">    
  
    <div class="col-md-12 col-sm-6 col-xs-12"> 
    <h1 class="title" align="center"><b>Patch Maker 1.0 </b></h1> 
    <hr style="border-top: 1px solid gray;"/>
	      <div class="alert alert-info alert-dismissable">
	        <a class="panel-close close" data-dismiss="alert">×</a> 
	        <i class="fa fa-coffee"></i>
	        This is an <strong>.alert</strong>. Use this to show important messages to the user.
	      </div>
	  </div>    
	       <form class="form" action="##" method="post" id="registrationForm">
	       
	        <input type="text" id="userId" hidden="true">
	        <div class="row">  
	     	 <div class="col-md-12">
	      		<div class="form-group col-md-4">
							 <label for="first_name"><h4>Project</h4></label>
							  <select class="form-control" id="project">
							    <option>P-1605-HDFC</option>
							   <!--  <option>Manager</option>   --> 							   
							  </select>
				</div>
				<div class="form-group col-md-2">
					<div>
                            <label for="date"><h4>Bank Jira ID</h4></label>
                             <select class="form-control" id="bankProject" onClick="SetJiraType()">
							    <option value="NEOPROD">NEOPROD</option>
							    <option value="NEOSFR">NEOSFR</option>   							   
							  </select>
                     </div>
				</div>
				<div class="form-group col-md-2">      
                              <label for="zipcode"><h4>&nbsp</h4></label>
                              <input type="text" class="form-control" name="bankJiraId" id="bankJiraId" onchange="BankJiraOnChange()">
                         
                 </div>
                 <div class="form-group col-md-2">
					<div>
                            <label for="type"><h4>Patch Type</h4></label>
                             <select class="form-control" id="patchType" onchange="patchTypeOnChange()" >
							    <option value="Fix">Permanent Fix</option>
							    <option value="Data_Repair">Data Repair</option>
							    <option value="Testing">Testing</option>   	
							    <option value="logger">Logger</option> 						   
							  </select>
                     </div>
				</div>
				<div class="form-group col-md-2">
					<div>
                            <label for="date"><h4>Date</h4></label>
                             <input type="date" class="form-control" name="date" id="date" placeholder="Date" title="Date" onchange="">
                     </div>
				</div>
             <!--    
				 <div class="form-group">                          
                          <div class="col-md-4">
                            <label for="date"><h4>Date</h4></label>
                              <input type="date" class="form-control" name="date" id="date" placeholder="Date" title="Date" onchange="">
                          </div>
              	 </div>    --> 
               </div>
	       </div>
	        <div class="row">  
	     	 <div class="col-md-12">
	      		
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
				<div class="form-group col-md-4">
					<div>
                            <label for="date"><h4>Patch Name</h4></label>
                              <input type="text" class="form-control" name="patchName" id="patchName">
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
	                              <textarea id="features" rows="2" style="width: 100%;"></textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Defect Fixed</h4></label>
	                            <textarea id="defectsFixed" rows="2"></textarea>
	                     </div>
					</div>					
               </div>
	       </div>
	        <div class="row">  
	     	 <div class="col-md-12">
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>New Functionality Added</h4></label>
	                              <textarea id="newFunctionality" rows="2">None</textarea>
	                     </div>
					</div>
					<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Impact On Existing Functionality</h4></label>
	                            <textarea id="impact" rows="2" style="width: 100%;">Not Applicable</textarea>
	                     </div>
					</div>
               </div>
	       </div>
	       <div class="row">   
	     	 <div class="col-md-12">
	     	 		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Testing Details</h4></label>
	                            <textarea id="testingDetails" rows="2">None</textarea>
	                     </div>
					</div>
		      		<div class="form-group col-md-6">
						<div>
	                            <label for="date"><h4>Known Bugs & Workaround</h4></label>
	                            <textarea id="knownBugs" rows="2">None</textarea>
	                     </div>
					</div>	
				
               </div>
	       </div>
	       <div class="row">  
	     	 <div class="col-md-6">
		      		 <div class="form-group col-md-5">
						<div>
	                            <label for="date"><h4>Patch Environments</h4></label>
	                            <select class="form-control" id="patchEnvironmentsOptions" onchange="SetPatchEnvironments()">
	                            <option>Select Environments</option>
							    <option>Pre-prod</option>
							    <option>Production</option>   
							    <option>Scale-up</option> 	
							    <option>Onsite-UAT</option> 
							    <option>Onsite-SIT</option> 				   
							  </select>
	                     </div>
					</div>
					<div class="form-group col-md-3">      
                              <label for="zipcode"><h4>&nbsp</h4></label>
                              <input type="button" value="Remove" class="form-control btn btn-danger" style="width:100%;" onClick="removePatchEnvironments()">
                         
                      </div>
						<div class="form-group col-md-4">
							<label for="date"><h4>Output</h4></label><br/>
							<textarea id="patchEnvironments" rows="3" style="width:108%;">
							</textarea>
	                     </div>
               </div>
               <div class="col-md-6" style="margin-left:-16px;">
		      		 <div class="form-group col-md-5 ">
						
	                            <label for="patchModulesOptions"><h4>Patch Modules</h4></label>
	                            <select class="form-control" id="patchModulesOptions" onchange="SetPatchModules()">
	                            <option>Select Modules</option>
							    <option>CAS</option>
							    <option>Common Masters</option>   
							    <option>ECM</option>
							    <option>INTG</option>
							    <option>GCC</option>
							    <option>GCD</option> 	
							    <option>MFIN</option> 
							    <option>Geotracker</option> 
							    <option>SSO</option> 	
							    <option>LMS</option> 				   
							  </select>	                    
					</div>
					<div class="form-group col-md-3">      
                              <label for="zipcode"><h4>&nbsp</h4></label>
                              <input type="button" value="Remove" class="form-control btn btn-danger" style="width:100%;" onClick="removePatchModules()">
                         
                      </div>
						<div class="form-group col-md-4">
							<label for="date"><h4>Output</h4></label><br/>
							<textarea id="patchModules" rows="3" style="width:109%;">
							</textarea>
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
							    <option>Set hbm2ddl.auto=update</option> 
							    <option>Execute provided script</option> 		   
							  </select>
	                     </div>
					</div>
					<div class="form-group col-md-3">      
                              <label for="zipcode"><h4>&nbsp</h4></label>
                              <input type="button" value="Remove" class="form-control btn btn-danger" style="width:113%;" onClick="removeInstallStep()">
                         
                      </div>
				</div>
				<div class="col-md-6">
						<div>
							<label for="date"><h4>Output</h4></label><br/>
							<textarea id="installSteps" rows="4" style="width:98%;">
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
                              <input type="button" value="Remove" class="form-control btn btn-danger" style="width:113%;" onClick="removeRollbackStep()">
                         
                      </div>
				</div>
				<div class="col-md-6">
						<div>
							<label for="date"><h4>Output</h4></label><br/>
							<textarea id="rollbackSteps" rows="4" style="width:98%;">
							</textarea>
					</div>
				</div>				
			</div>
			<div style="padding-left:15px;padding-right:10px; margin-top:50px; margin-bottom:30px;">
				<input type="button" class="form-control btn btn-success" value="Save And Continue" onClick="savePatchDetails()" />
			</div>
		</form>
		
	</div>
	<div class="container main" id="bottomDiv" style="margin-top:40px; margin-bottom:50px; display: none;"> 
<!-- 	<div class="container main" id="bottomDiv" style="margin-top:40px; margin-bottom:50px;">  -->
	
	<div id="uploadDiv" class="col-md-12 col-sm-6 col-xs-12" style="border-style: dashed;">
		<!-- <hr style="border-top: .6px solid gray;" /> -->


		<h4>Upload Patch files</h4>
		<form id="uploadForm" class="uploadFormClass" method="POST" action="#"
			enctype="multipart/form-data">
			<!-- COMPONENT START -->
			<div class="form-group">

					<div class="form-group col-md-3">
						<div>
							<select class="form-control" id="patchFileType" 
								name="patchFile">
								<option value="SELECT">Select Type</option>
								<option value="Class_Files">.Class</option>
								<option value="DB_Scripts">DB_Script</option>
								<option value="Properties">Property</option>
								<option value="Others">Other</option>  
							</select>
						</div>
					</div>
					
					<div class="input-group input-file col-md-9" name="file">
						<div class = "col-md-6">
							<input type="file" name="file" id="file" size="60" multiple="muliple" class="form-control" /> 
						</div>
						<div class = "col-md-2">
							<input type="button" value="Upload" class="btn btn-primary" style="width:100%; margin-left:-20px" onClick="uploadFiles()">
						</div>
						<div class = "col-md-2">
							<button class="btn btn-warning btn-reset" type="reset" style="width:100%; margin-left:-40px">Reset</button>	
						</div>
						<div class = "col-md-2">
							<button class="btn btn-info" type="reset" style="width:100%; float:right" onClick="openFileBrowser()">View Files</button>	
						</div>			
					</div>
			</div>
		</form>
				 <div class="files__list" id="fileListTable">                       
                        
                      <!--   <div class="item clearfix" id="files-0">
                            <div class="item__description" style="width:120px;">Class File</div>
                             <div class="item__description">SLI-Pre-prited Docs Cnfiguration</div>
                            <div class="right clearfix">
                                <div class="item__value">
                                	<input type="text" class="form-control" style="width:600px; margin-top:-5px; margin-bottom:-5px;" placeholder="Enter destination file path">
                                </div>
                                <div class="item__delete">
                                    <button class="item__delete--btn">
                                        <i class="ion-ios-close-outline"></i>
                                    </button>
                                </div>
                            </div>
                        </div> -->
                    

				</div>
				
				<br /> <br />  
				<div style="padding-left:15px;padding-right:10px; margin-top:50px; margin-bottom:30px;">
				<input type="button" class="form-control btn btn-success" value="Check-In Files And Download Patch Zip" onClick="saveFileDetails()" />
			</div>
	

	</div>
		
	</div>
	<div style="height:25px;">
		<jsp:include page='footer.jsp'>
			<jsp:param name="articleId" value="Satish" />
		</jsp:include>
	</div>
</body>
</html>                                		                            