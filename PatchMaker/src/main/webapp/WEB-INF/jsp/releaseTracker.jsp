 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Release Tracker</title>
<link rel="icon" type="image/png" href="resources/assets/img/logo.png">


<link href="<c:url value="/resources/assets/style/releaseTracker.css"/>" rel="stylesheet"> 

<!-- Order of import is very important in below imports, jquery.min.js should always be imported first -->
<script src="<c:url value="/resources/assets/script/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/script/bootstrap.min.js" />"></script> 
<script src="<c:url value="/resources/assets/script/releaseTracker.js" />"></script>

<!--  Data table imports -->
<script src="<c:url value="https://code.jquery.com/jquery-3.3.1.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap.min.js" />"></script>

<!-- Export buttons -->
<script src="<c:url value="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js" />"></script>
 <script src="<c:url value="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js" />"></script>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" />"></script>


</head>
<body>

<jsp:include page='header.jsp'>
    <jsp:param name="articleId" value="Satish"/>
</jsp:include>
 <div class="container" >
  
<table id="example" class="cell-border" >
        <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Modules</th>
                <th>Patch Name</th>
                <th>Bank Jira</th>
                <th>Product Jira</th>
                <th>Internal Jira</th>
                <th>Resource</th>
                <th>Environments</th>
            </tr>
        </thead>
        <tbody>
        
         <c:forEach items="${patches}" var="patch">

    <tr>

       <td><c:out value="${patch.value.id}" /></td>
       <td><c:out value="${patch.value.date}" /></td>
       <td><c:out value="${patch.value.modules}" /></td>
       <td><c:out value="${patch.value.patchName}" /></td>
       <td><c:out value="${patch.value.bankJira}" /></td>
       <td><c:out value="${patch.value.productJira}" /></td>
       <td><c:out value="${patch.value.internalJira}" /></td>
       <td><c:out value="${patch.value.createdBy}" /></td>
       <td><c:out value="${patch.value.environments}" /></td>        
       </tr>
       
        </c:forEach>
            <tr><td>1</td><td>20/02/2020</td><td>MFIN</td><td>P-1605-HDFC-UnableToSubmitMemberAtBRE_20.02.2020_469</td><td>NEOPROD-112</td><td>MCAS-8595</td><td>MCAS-8595</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>2</td><td>20/02/2020</td><td>MFIN</td><td>P-1605-HDFC-NEOPROD-112_20.02.2020_470</td><td>NEOPROD-112</td><td>MCAS-8595</td><td>MCAS-8595</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>3</td><td>26/02/2020</td><td>CAS,CM</td><td>P-1605-HDFC-NEOPROD-88_473_26_02_2020</td><td>NEOPROD-88</td><td>CAS-103241</td><td>CAS-103241</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>4</td><td>28/02/2020</td><td>CAS & CM</td><td>P-1605-HDFC_NEOPROD-113_NEOSFR-2006_479_28_02_2020</td><td>NEOPROD-113 & NEOSFR-2006</td><td>CAS-104989</td><td>CAS-104989</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>5</td><td>04/03/20</td><td>CM</td><td>P-1605-HDFC_NEOPROD-119_483</td><td>NEOPRD-119</td><td>NA</td><td>NA</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>6</td><td>05/03/20</td><td>CAS,CM</td><td>P-1605-HDFC_NEOPROD-132_05_03_2020_486</td><td>NEOPRD-132</td><td>NA</td><td>NA</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>7</td><td>05/03/20</td><td>INTG</td><td>P-1605-HDFC-NEOPROD-136_BAO_LoggerPatch_05_03_2020_487</td><td>NEOPRD-136</td><td>NA</td><td>NA</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>8</td><td>12/03/20</td><td>INTG</td><td>P-1605-HDFC-ImageConversionBPP_490</td><td>NEOPROD-120</td><td>NA</td><td>NA</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>9</td><td>16/03/2020</td><td>CAS,CM</td><td>P-1605-HDFC-NEOPROD-128-NEOPROD-96_LoggerPatch_491</td><td>NEOPROD-96,NEOPROD-128</td><td>CAS-104650</td><td>CAS-104650</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>10</td><td>17/03/2020</td><td>CAS,CM</td><td>P-1605-HDFC-NEOPROD-128-NEOPROD-96_493</td><td>NEOPROD-96,NEOPROD-128</td><td>CAS-104650</td><td>CAS-104650</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>11</td><td>18/03/2020</td><td>CAS</td><td>P-1605-HDFC-NEOPROD-128_LoggerPatch-18.03.2020_495</td><td>NEOPRD-128</td><td>CAS-104650</td><td>CAS-104650</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>12</td><td>19/03/2020</td><td>CAS</td><td>P-1605-HDFC-NEOPROD-128_LoggerPatch2-19.03.2020_499</td><td>NEOPRD-128</td><td>CAS-106261</td><td>CAS-106261</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>13</td><td>19/03/2020</td><td>MFIN</td><td>P-1605-HDFC-NEOPROD-115-168-97_19.03.2020_500</td><td>NEOPROD-115-168-97</td><td>MCAS-8885</td><td>MCAS-8885</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>14</td><td>10/02/20</td><td>CAS</td><td>P-1605-HDFC-VillageBranchUploadReleaseNote464</td><td>NA</td><td>NA</td><td>NA</td><td>Deepika</td><td>Production</td></tr>
<tr><td>15</td><td>12/02/20</td><td>CAS</td><td>P-1605-HDFC-VillageBranchUploadReleaseNote466</td><td>NA</td><td>NA</td><td>NA</td><td>Deepika</td><td>Production</td></tr>
<tr><td>16</td><td>24/01/2020</td><td>CAS</td><td>P-1605-HDFC_NEOPROD-81_ReleaseNote_453</td><td>NEOPROD-81</td><td>CAS-102443</td><td>CAS-102443</td><td>Amir</td><td>Production</td></tr>
<tr><td>17</td><td>28/01/2020</td><td>CAS</td><td>P-1605-HDFC_NEOSFR-82_ReleaseNote_454</td><td>NEOSFR-82</td><td>CAS-102708</td><td>CAS-102708</td><td>Amir</td><td>Production</td></tr>
<tr><td>18</td><td>28/01/2020</td><td>mFIN</td><td>P-1605-HDFC_NEOPROD-78_ReleaseNote_455</td><td>NEOPROD-78</td><td>MCAS-8346</td><td>MCAS-8346</td><td>Amir</td><td>Production</td></tr>
<tr><td>19</td><td>29/01/2019</td><td>CAS</td><td>P-1605-HDFC_NEOPROD-74_ReleaseNote_457</td><td>NEOPROD-74</td><td>CAS-102603</td><td>CAS-102603</td><td>Amir</td><td>Production</td></tr>
<tr><td>20</td><td>05/02/19</td><td>CAS</td><td>P-1605-HDFC_NEOPROD-66_ReleaseNote_460</td><td>NEOPROD-66</td><td></td><td></td><td>Amir</td><td>Production</td></tr>
<tr><td>21</td><td>13/02/2020</td><td>CAS</td><td>P-1605-HDFC-DaysForFinalReJection-NEOPROD-96</td><td>NEOPROD-96</td><td>CAS-103383</td><td>CAS-103383</td><td>Satish</td><td>Production</td></tr>
<tr><td>22</td><td>20/02/2020</td><td>CAS</td><td>P-1605-HDFC-NEOPROD-41-CAS_login issue_471</td><td>NEOPROD-43</td><td>PDDEV-26158</td><td>PDDEV-26158</td><td>Satish</td><td>Production</td></tr>
<tr><td>23</td><td>03/03/20</td><td>CAS & Neo Stage</td><td>P-1605-HDFC-Application_ID_Sequence_Change_481</td><td>NEOSFR-2229</td><td>NA</td><td>NA</td><td>Satish</td><td>Production</td></tr>
<tr><td>24</td><td>03/03/20</td><td>GCC</td><td>P-1605-HDFC-NEOPROD-129_Loan_movement_482</td><td>NEOPRD-129</td><td></td><td></td><td>Satish</td><td>Production</td></tr>
<tr><td>25</td><td>05/03/20</td><td>GCC</td><td>P-1605-HDFC-NEOPROD-138_Loan_movement_485</td><td>NEOPRD-138</td><td></td><td></td><td>Satish</td><td>Production</td></tr>
<tr><td>26</td><td>09/03/20</td><td>INTG</td><td>P-1605-HDFC-NEOPROD-145_FC_ACCOUNT_NO_489</td><td>NEOPRD-145</td><td></td><td></td><td>Satish</td><td>Production</td></tr>
<tr><td>27</td><td>17/03/2020</td><td>GCC</td><td>P-1605-HDFC-NEOPROD-156_LM_Charges_Issue_492</td><td>NEOPROD-156,NEOPROD-159</td><td></td><td></td><td>Satish</td><td>Production</td></tr>
<tr><td>28</td><td>02/03/2020</td><td>CAS & CM</td><td>P-1605-HDFC_NEOPRD-128_CAS-105056</td><td>NEOPRD-128</td><td>CAS-105056</td><td>CAS-105056</td><td>Sanchit</td><td>Production</td></tr>
<tr><td>29</td><td>26/02/2020</td><td>CAS</td><td>P-1605-CAS-104641,104688 NEOPROD-105 NEOSFR-2006 NEOPROD-113</td><td>NEOPROD-105,NEOSFR-2006,NEOPROD-113</td><td>CAS-104641,104688</td><td>CAS-104641,104688</td><td>Sanchit</td><td>Production</td></tr>
<tr><td>30</td><td>26/02/2020</td><td>MFIN</td><td>P-1605-MCAS-8611 NEOPROD-104</td><td>NEOPROD-104</td><td>MCAS-8611</td><td>MCAS-8611</td><td>Sanchit</td><td>Production</td></tr>
<tr><td>31</td><td>12/02/2020</td><td>MFIN</td><td>P-1605-Pre-Prod SP8-MFIN-ONSITE-RELEASE_465</td><td></td><td></td><td></td><td>Sanchit</td><td>Production</td></tr>
<tr><td>32</td><td>27/03/2020</td><td>CAS</td><td>P-1605-HDFC-NEOPROD-154_27.03.2020_507</td><td>NEOPROD-154</td><td>CAS-106218</td><td>CAS-106218</td><td>Deepali.Jain</td><td>Production</td></tr>
<tr><td>33</td><td>30/03/2020</td><td>INTG</td><td>P-1605-HDFC-NEOPROD-150_PDF_THREAD_508</td><td>NEOPRD-150</td><td></td><td></td><td>Satish</td><td>Production</td></tr>
            
        </tbody>
        <tfoot>
            <tr>
                 <th>#</th>
                <th>Date</th>
                <th>Modules</th>
                <th>Patch Name</th>
                <th>Bank Jira</th>
                <th>Product Jira</th>
                <th>Internal Jira</th>
                <th>Resource</th>
                <th>Environments</th>
            </tr>
        </tfoot>
    </table>
    </div>
</body>
</html>