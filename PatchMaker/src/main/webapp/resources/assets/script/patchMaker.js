/**
 * 
 */

/*  $(document).ready(function()  {
	  executeAll();
  });*/

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
var patchEnvironmentSeq=1;
var patchEnvironmentOptions = [];
function SetPatchEnvironments(){
	var newText = document.getElementById('patchEnvironmentsOptions').value;
	if("Select Environments"!=newText ){
		if(document.getElementById("patchEnvironments").innerHTML=="							" || document.getElementById("patchEnvironments").innerHTML==""){
			document.getElementById("patchEnvironments").innerHTML = patchEnvironmentSeq+". " +newText + "\n";
		}
		else{
			document.getElementById("patchEnvironments").innerHTML += patchEnvironmentSeq+". " +newText + "\n"  ;
		}
		patchEnvironmentOptions.push(document.getElementById("patchEnvironments").innerHTML);
		patchEnvironmentSeq++;
	}	
}
function removePatchEnvironments(){
	patchEnvironmentOptions.pop();
	if(patchEnvironmentSeq>1){
		patchEnvironmentSeq--;
		if(patchEnvironmentOptions.length==0)
			document.getElementById("patchEnvironments").innerHTML ="";
		else
		document.getElementById("patchEnvironments").innerHTML = patchEnvironmentOptions[patchEnvironmentOptions.length-1];
	}
	else{
		document.getElementById("patchEnvironments").innerHTML="";
	}		
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