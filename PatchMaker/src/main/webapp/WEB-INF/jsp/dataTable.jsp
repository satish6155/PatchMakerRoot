<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HDFC JIRA</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<!-- DATA TABlE -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<!-- DATA TABlE export buttons starts -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css">
<!--    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
 -->

<script
	src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
<!-- DATA TABlE export buttons ends -->

<!-- ColReorder starts -->

<link rel="stylesheet"
	href="https://cdn.datatables.net/colreorder/1.5.1/css/colReorder.dataTables.min.css">
<script
	src="https://cdn.datatables.net/colreorder/1.5.1/js/dataTables.colReorder.min.js"></script>

<!-- ColReorder ends here -->

<!-- responsive starts -->
<!--  to adjust with screen -->

<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css">
<script
	src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>

<!-- responsive ends here -->

<!-- date format -->
<script src="http://www.datejs.com/build/date.js" type="text/javascript"></script>


<script>
$(document).ready(function(){

	
	/* datatable column search starts here  */
    $('#example thead tr').clone(true).appendTo( '#example thead' );
    $('#example thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
 
        $( 'input', this ).on( 'keyup change', function () {
            if ( table.column(i).search() !== this.value ) {
                table
                    .column(i)
                    .search( this.value )
                    .draw();
            }
        } );
    } );
    
    var table = $('#example').DataTable( {
        
    	//colReorder: true,    --it allows to shuffle columns on webpage, disabling it because we are getting data by column number
    	responsive: true,
    	orderCellsTop: true,
        fixedHeader: true,
        
    	 "pageLength": 50,
    	 dom: 'Brtlip',
         buttons: [
             'copyHtml5',
            s
             'csvHtml5',
             'pdfHtml5',
             'print'
         ] 
    	
    	
    } );	 
    
    
    /* datatable column search ends here  */
    
   // document.getElementById("ipDiv").innerHTML += '';  
    
    $('#modalLoginAvatar').modal({
    	show: 'true',
		backdrop: 'static',
		keyboard: false
	});
    
    $("#loginButton").on("click", function(){
        if($("#the-form")[0].checkValidity()) {
          //  alert('validated');
         // $("#currentUser").val();       topDiv
         
         document.getElementById("topDiv").innerHTML = 'User : ' + $("#form29").val();
          
            $('#modalLoginAvatar').modal('hide');
        }
        else {
            $("#the-form")[0].reportValidity();
        }
    });
        
    
    $('#example tbody').on('dblclick','tr', function() {
      var currentRowData = table.row(this).data();

      console.log('TD cell textContent 1 : ', currentRowData[0]);
      console.log('TD cell textContent 2 : ', currentRowData[1]);
      
      
     // document.getElementById("modalDiv1").innerHTML = 'NEOSFR : ' + currentRowData[0];
     
      
      
      $("#releaseNoteName").val(currentRowData[0]);      
      $("#pdimp").val(currentRowData[1].substring((currentRowData[1].lastIndexOf("PDIMP")),(currentRowData[1].indexOf("</a>")))); 
      $("#productJira").val(currentRowData[2].substring((currentRowData[2].lastIndexOf("CAS")),(currentRowData[2].indexOf("</a>")))); 
      $("#bankJira").val(currentRowData[3]); 
      
          
      $("#raisedOn").val(currentRowData[4].substring(0,10)); 
                  
      $("#onsiteDeliveryDate").val(currentRowData[5].substring(0,10)); 
      $("#resourceName").val(currentRowData[6]); 
      $("#fileName").val(currentRowData[7]);
      $("#destinationPathName").val(currentRowData[8]);
      
            
      $('#myModal').modal('show'); 
      
      
  });
    
    
    
    
});



</script>

<style>
body {
	background-color: #ccccba;
}

.dataTables_wrapper .dt-buttons {
	float: none;
	text-align: center;
}
</style>
</head>
<body>
	<br>

	<hr>



	<table id="example" class="display" style="width: 100%">

		<thead>
			<tr>
				<th>Patch Release Date</th>
				<th>Release note name</th>
				<th>PDIMP</th>
				<th>Bank Jira</th>
				<th>Product Jira</th>
				<th>Resource Name</th>
				<th>Raised On (YYYY-MM-DD)</th>
				<th>File Name</th>
				<th>Destination Path</th>
			</tr>
		</thead>
		<tbody>


			<c:forEach items="${jiras}" var="jira">
				<tr>
					<td><c:out value="${jira.value.neosfr}" /></td>
					<td><a
						href="https://jira.nucleussoftware.com/browse/${jira.value.pdimp}"
						target="_blank">${jira.value.pdimp}</a></td>
					<td><a
						href="https://jira.nucleussoftware.com/browse/${jira.value.productJira}"
						target="_blank">${jira.value.productJira}</a></td>
					<td><c:out value="${jira.value.summary}" /></td>
					<td><c:out
							value="${fn:substring(jira.value.loggedDate, 0, 10)}" /></td>
					<td><c:out
							value="${fn:substring(jira.value.patchReleaseDate, 0, 10)}" /></td>
					<td><c:out value="${jira.value.loggedBy}" /></td>
					<td><c:out value="${jira.value.fileName}" /></td>
					<td><c:out value="${jira.value.destinationPathName}" /></td>


				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>




	<!--Modal: Login with Avatar Form-->
	<div class="modal fade" id="modalLoginAvatar" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog cascading-modal modal-avatar modal-sm"
			role="document">
			<!--Content-->
			<div class="modal-content">

				<!--Header-->
				<div class="modal-header">
					<div align="center">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSELomnDZwI9Q6oEyKPDRtLuRx-MGgE1_-3mwPrS_I1VHflh0y1"
							alt="avatar2" class="rounded-circle img-responsive">
					</div>

				</div>
				<!--Body-->
				<div class="modal-body text-center mb-1">

					<label data-error="wrong" data-success="right" for="form29"
						class="ml-0">Hi, Enter Employee ID</label>

					<div class="md-form ml-0 mr-0">

						<form id="the-form" action="#">
							<input type="number" name="userId" id="form29" value="1234"
								class="form-control form-control-sm validate ml-0" min="1000"
								max="100000" required
								oninvalid="this.setCustomValidity('Enter Employee Id Here')"
								oninput="this.setCustomValidity('')">

						</form>

					</div>

					<div class="text-center mt-4">
						<br>
						<button class="btn btn-cyan mt-1" id="loginButton">
							Login <i class="fas fa-sign-in ml-1"></i>
						</button>
					</div>
				</div>

			</div>
			<!--/.Content-->
		</div>
	</div>

	<!--EDIT Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Details</h4>
				</div>
				<div class="modal-body">
					<p id="modalDiv1"></p>

					<form id="editJiraForm" action="#">

						<label for="First_Name">Release note name</label> <input
							name="releaseNoteName" id="releaseNoteName" type="text"
							class="form-control validate" /> <label for="pdimp">PDIMP</label>
						<input name="pdimp" id="pdimp" type="text"
							class="form-control validate" /> <label for="productJira">Product
							JIRA</label> <input name="productJira" id="productJira" type="text"
							class="form-control validate" /> <label for="summary">Summary</label>
						<input name="bankJira" id="bankJira" type="text"
							class="form-control validate" /> <label for="raisedOn">Raised
							On Date</label> <input name="raisedOn" id="raisedOn" type="date"
							class="form-control validate" /> <label for="onsiteDeliveryDate">Onsite
							Delivery Date</label> <input name="onsiteDeliveryDate"
							id="onsiteDeliveryDate" type="date" class="form-control validate" />

						<label for="resourceName">Resource Name</label> <input
							name="resourceName" id="resourceName" type="text"
							class="form-control validate" /> <label for="assignedTo">Assigned
							To</label> <input name="fileName" id="fileName" type="text"
							class="form-control validate" /> <label for="remarks">Remarks</label>
						<input name="destinationPathName" id="destinationPathName"
							type="text" class="form-control validate" />
				</div>
				<div class="modal-footer">
					<input type="submit" value="Update">
					</form>
					<button type="button" class="btn btn-default" data-dismiss="modal">Update
						Jira</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close</button>
					<button type="button" class="btn btn-primary">Save changes
					</button>
				</div>
			</div>

		</div>
	</div>

	</div>



	<div class="fluid">

		<button type="button" class="btn btn-default btn-rounded"
			data-toggle="modal" data-target="#myModal">Add JIRA</button>
		<a href="" class="btn btn-default btn-rounded" data-toggle="modal"
			data-target="#modalLoginAvatar">Change User </a> <a
			href="codeforgeeks">Click here to go to website</a>

	</div>

	<div id="topDiv" text-align="right"></div>

</body>







<script>

function updateJira(){


//$('selectRM').val()
 

//alert(x);

$.ajax({

                     url: "${pageContext.request.contextPath}/app/customDashboardController/listRM/" +x,

                     type : "GET",

                     data : "",

                     dataType : "json",

                     contentType : "application/json; charset=utf-8",

                     success : function(data) {

                        //alert("An error has occured!!!" + checked);
						},

                     error : function() {

                           //alert("An error has occured!!!" + checked);

                     }

              });


}

 

</script>


</html>