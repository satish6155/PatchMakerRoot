
/*
 * ---- TO-DO
 * When deleting an file from UI, same should get deleted from folder*/

var patchId;
var allFileItems = [];
var bottomDivHeight = 500;

var patchBean = function(id, fileDetails) {
	this.id = id;
	this.fileDetails = fileDetails;
}

var fileDetail = function(id, type, name, path) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.path = path;
};
function executeAll(){
	SetDate();
	SetJiraType();
	disableUpload();	
	document.querySelector('.files__list').addEventListener('click', deleteFileRow);
	document.querySelector('.files__list').addEventListener('change', saveFilePath);
}
function disableUpload(){
	//console.log('Inside disableUpload');
	var nodes = document.getElementById("uploadDiv").getElementsByTagName('*');
	for(var i = 0; i < nodes.length; i++){
	     nodes[i].disabled = true;
	}
}
function enableUpload(){
	//console.log('Inside enableUpload');
	var nodes = document.getElementById("uploadDiv").getElementsByTagName('*');
	for(var i = 0; i < nodes.length; i++){
	     nodes[i].disabled = false;
	}
}
function BankJiraOnChange() {
	setPatchName();
	SetFeature();	
}
function patchTypeOnChange() {
	setPatchName();
}

function setPatchName(){
	var project,bankJira,date,dateString, patchType;
	project = document.getElementById('project').value ;
	bankJira  = document.getElementById('bankJiraId').value ;
	date = ( document.getElementById('date').value).split('-') ;
	patchType = document.getElementById('patchType').value ;
	//console.log(patchType);
	
	dateString = date[2]+'-'+date[1]+'-'+date[0];
	
	
	document.getElementById('patchName').value = project + '_' + bankJira + '_' + patchType +'_'+ dateString;
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
	setPatchName();
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
var patchModuleSeq=1;
var patchModuleOptions = [];

function SetPatchModules(){
	var newText = document.getElementById('patchModulesOptions').value;
	if("Select Modules"!=newText ){
		if(document.getElementById("patchModules").innerHTML=="							" || document.getElementById("patchModules").innerHTML==""){
			document.getElementById("patchModules").innerHTML = patchModuleSeq+". " +newText + "\n";
		}
		else{
			document.getElementById("patchModules").innerHTML += patchModuleSeq+". " +newText + "\n"  ;
		}
		patchModuleOptions.push(document.getElementById("patchModules").innerHTML);
		patchModuleSeq++;
	}	
}
function removePatchModules(){
	patchModuleOptions.pop();
	if(patchModuleSeq>1){
		patchModuleSeq--;
		if(patchModuleOptions.length==0)
			document.getElementById("patchModules").innerHTML ="";
		else
		document.getElementById("patchModules").innerHTML = patchModuleOptions[patchModuleOptions.length-1];
	}
	else{
		document.getElementById("patchModules").innerHTML="";
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
function savePatchDetails(){
	//console.log('Inside savePatchDetails.onCLick()');

	var formData = 
			{
			
			 id	: patchId,
			 patchName : $("#patchName").val()  ,
			 bankJira : $("#bankJiraId").val(),
			 productJira : $("#productJiraId").val(),
			 internalJira : $("#internalJiraId").val(),
			 patchDate : $("#date").val(),
			 defectsFixed : $("#defectsFixed").val(),
			 modules : $("#patchModules").val(),
			 features : $("#features").val(),
			 impact : $("#impact").val(),
			 installSteps : $("#installSteps").val(),
			 knownBugs : $("#knownBugs").val(),
			 newFunctionality : $("#newFunctionality").val(),
			 environments : $("#patchEnvironments").val(),
			 rollbackSteps : $("#rollbackSteps").val(),
			 svnRevisions : $("#svnRevisions").val(),
			 testingDetails : $("#testingDetails").val(),
			 createdBy : $("#createdBy").val(),
			 project :$("#project").val()
				
			};
	    

	 $.ajax({
	     url: "savePatchDetails",
	      type: "post",
	      data:formData,
	      
	     success: function(response) {
	    	 //console.log('response :' +response);
	    	 patchId = Number (response);
	    	 //console.log(patchId);
	    	 enableUpload();   /* Enabling File Upload Divisions */
	    	 
	    	 document.getElementById("bottomDiv").style.display = null;  //show file upload div
	    	 scrollToFileUpload();
	     }	
	   });	
	
}

function uploadFiles() {
	var patchFileType = document.getElementById('patchFileType').value;
	
	if(document.getElementById("file").value == "") {
		alert("Please select files to upload!");
		} 
	else if(patchFileType != "SELECT") {
		
		var form = $("#uploadForm")[0];
		var data = new FormData(form);
		
		/*for(var pair of data.entries()) {
			   //console.log(pair[0]+ ', '+ pair[1]); 
			}
		*/
		
		 $.ajax({
			type : "POST",
			encType : "multipart/form-data",
			url : "uploadFiles/"+patchFileType,
			cache : false,
			processData : false,
			contentType : false,
			data : data,
			success : function(response) {
				
				resetFilesSelected();	
				
				response = response.substring(0, response.length - 1);			
				
				addFilesToList(response);
				
				},
			error : function(response) {
				alert("Couldn't upload file"+JSON.parse(JSON.stringify(response)));
			}
		}); 
	}
	else {
		alert("Please choose file type!");
	}
		
	
}

function resetFilesSelected() {
	var f = document.getElementById('file');
	
	 if(f.value){
         try{
             f.value = ''; //for IE11, latest Chrome/Firefox/Opera...
         }catch(err){
         }
         if(f.value){ //for IE5 ~ IE10
             var form = document.createElement('form'), ref = f.nextSibling;
             form.appendChild(f);
             form.reset();
             ref.parentNode.insertBefore(f,ref);
         }
	 }	
}

function openFileBrowser() {
	window.open('http://localhost:1011/PatchMaker/servlet/FileManager?d:/PatchMakerRoot/FileBrowserProperties', '_blank');
}



function renderFileList() {
	
	var htmlStringBase = `<div class="item clearfix" id="files-%id%">
	    <div class="item__description" style="width:120px;">%type%</div>
	     <div class="item__description">%name%</div>
	    <div class="right clearfix">
	        <div class="item__value">
	        	<input type="text" class="form-control" id="file-path-%id%" style="width:600px; margin-top:-5px; margin-bottom:-5px;" 
	        		placeholder="Enter destination file path or database schema detail" value="%path%">
	        </div>
	        <div class="item__delete">
	            <button class="item__delete--btn">
	                <i class="ion-ios-close-outline"></i>
	            </button>
	        </div>
	    </div>
	</div>`;
	
	document.getElementById('fileListTable').innerHTML='';
	
	allFileItems.forEach(function(obj) {
		
		var nameTrimmed = obj.name;
		
		if(obj.name.length>30){
			nameTrimmed = obj.name.substring(0,30)+' ...';
		}
							
		htmlString =  htmlStringBase.replace('%id%', obj.id).replace('%id%', obj.id).replace('%type%', obj.type).replace('%name%', nameTrimmed).replace('%path%', obj.path);
		console.log(htmlString);
		var d1 = document.getElementById('fileListTable');
		d1.insertAdjacentHTML('beforeend', htmlString);			
			
	});		
	
	if(allFileItems.length<6){		
		document.getElementById("bottomDiv").style.height = bottomDivHeight+"px";
	} else {
		document.getElementById("bottomDiv").style.height = null;
	}
	
}

function addFilesToList(response){
	
	var filesArray = response.split(";");

	filesArray.forEach(function(cur) {
		
		var ID;
				
		if (allFileItems.length >0) {
			ID = allFileItems[allFileItems.length-1].id + 1;
        } else {        	
            ID = 0;
        }
		var newFileItem = new fileDetail(ID, cur.split(":")[0], cur.split(":")[1], '');
		
		allFileItems.push(newFileItem);
		
	});			
	renderFileList();	
}

function removeFileFromUI(selectorID) {
    
    var el = document.getElementById(selectorID);
    el.parentNode.removeChild(el);
    
    if(allFileItems.length<6){		
		document.getElementById("bottomDiv").style.height = bottomDivHeight+"px";
	}
    
}
function removeFileItem(itemID){
	
        var ids, index, id;
        
        id = parseInt((itemID.split('-')[1]),10);
        
        ids = allFileItems.map(function(current) {
        	console.log(current.id);
            return current.id;
        });

        index = ids.indexOf(id);
        if (index !== -1) {
            allFileItems.splice(index, 1);
            removeFileFromUI(itemID);
        } 
}

var deleteFileRow = function(event) {
    var itemID, splitID, type, ID;
    
    itemID = event.target.parentNode.parentNode.parentNode.parentNode.id;

    if (itemID && itemID.includes('files-')) {
    	removeFileItem(itemID);
    }
    else{
    	console.log('ID NOT DEFINED');
    }    
	
};

function saveFilePath(event){
	
	var itemID, index, filePath, id, ids;
    itemID = event.target.id;
    
    filePath = document.getElementById(itemID).value ;
    
    id = parseInt((itemID.split('-')[2]),10);
    
    ids = allFileItems.map(function(current) {
        return current.id;
    });

    index = ids.indexOf(id);
    
    if (index !== -1) {
        allFileItems[index].path = filePath;       
    }     
}

function scrollToFileUpload(){
	 document.getElementById("bottomDiv").style.height = bottomDivHeight+"px";
	var target = $('.uploadFormClass');
    if (target.length) {
        $('html,body').animate({
            scrollTop: target.offset().top
        }, 1000);
        return false;
    }   
}

function saveFileDetails() {
	
	 console.log('here');
	 
	 var filesJson = JSON.stringify({

         'files' : allFileItems 

     });   

	$.ajax({
	 url: "saveFileDetails",
	  type: "post",
	  data:filesJson,
	  dataType: "html",          
      contentType: 'application/json',
      mimeType: 'application/json',
	  
	 success: function(response) {
		 console.log('response :' +response);
	
	 }	
	});	
}

