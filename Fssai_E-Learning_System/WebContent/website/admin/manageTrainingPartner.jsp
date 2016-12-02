<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.error {
    color: red;
}
</style>
<script type="text/javascript">
function OnStart(){
	////alert('s');
	updateDiv();
}
window.onload = OnStart;
</script>
<script>
function updateDiv(){
	$( "#updateDiv" ).hide();
}
</script>
<script type = "text/javascript" >
       function preventBack(){window.history.forward();}
        setTimeout("preventBack()", 10);
        window.onunload=function(){null};
    </script>

<script language="javascript" type="text/javascript">
function AvoidSpace(event) {
    var k = event ? event.which : window.event.keyCode;
    if (k == 32) return false;
}


function checkname()
{
 var name=document.getElementById( "userId" ).value;
	
 if(name)
 {
  $.ajax({
  type: 'post',
  url: 'checkdata.jspp?'+ name,
  data: {
   user_name:name,
  },
  
  success: function (response) {
   $( '#name_status' ).html(response);
   
   if(response == 'OK')	
   {
	   document.getElementById("register1").style.display = 'none';
    return false;	
   }
   else
   {
	   document.getElementById("register").style.display = 'block';
    return false;	
   }
  }
  });
 }
 else
 {
  $( '#name_status' ).html("");
  document.getElementById("register").style.display = 'none';
  return false;
 }
} 

function getDistrict(val)
{
	$.ajax({
	      type: 'post',
	      url: 'loadDistrict.jspp?'+ val,
	      success: function (response) {
	      var mainData1 = jQuery.parseJSON(response);
	      alert(mainData1);
	      $('#district option').remove();
	      $('#district').append('<option value="0" label="Select State" />');
	      $('#city option').remove();
	      $('#city').append('<option value="0" label="Select City" />');
	  	  $.each(mainData1 , function(i , obj)
	  		{
	  		
	  				$('#district').append('<option value='+obj.districtId+'>'+obj.districtName+'</option>');		
	  		});
	      }
	      });     
}

function getCity(val)
{
	////alert("hiiiiiiiii"+val);
	$.ajax({
	      type: 'post',
	      url: 'loadCity.jspp?'+ val,
	      success: function (response) {
	      ////alert(response);	      
	      var mainData1 = jQuery.parseJSON(response);
	      $('#city option').remove();
	      $('#city').append('<option value="0" label="Select City" />');
	  	  $.each(mainData1 , function(i , obj)
	  		{
	  		
	  				$('#city').append('<option value='+obj.cityId+' >'+obj.cityName+'</option>');		
	  		});
	      }
	      });     
}
</script> 
<script type="text/javascript" language="javascript">
function pan_validate(pan)
{
var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;
if(regpan.test(pan) == false)
{
		document.getElementById("panstatus").innerHTML = "Invalid PAN !!!";
}else{
	document.getElementById("panstatus").innerHTML = ""; 
}
}

function searchDataTP(){
	var userId =  $("#userId").val();
	var tpn = $("#trainingPartnerName").val();
	$(".displayNone").css("display","block");
	 {
		var result="";
		var total = "userId="+userId+"&assessmentAgencyName="+tpn;
		$.ajax({
		type: 'post',
		url: 'searchDataTP.jspp?'+ total,
		async: false, 
		success: function (data){
		$('#newTable').show();
		var mainData1 = jQuery.parseJSON(data);
		////alert(mainData1);
		var j=1;
		$('#newTable tr').remove();
		$('#newTable').append('<tr  class="background-open-vacancies"><th>S.No.</th><th>Training Partner Id</th><th>Training Partner Name</th><th>Weblink</th><th>Status</th><th>Option</th></tr>')
		$.each(mainData1 , function(i , obj)
		{
			if(obj[5] == 'A'){
				stat = 'Active';
			}else{
				stat = 'In-Active';
			}
			$('#newTable').append('<tr id="tableRow"><td>'+j++ +'</td><td>'+obj[1]+'</td><td>'+obj[2]+'</td><td>'+obj[4]+'</td><td>'+stat+'</td><td><input type="hidden" id="mtpId" value="'+obj[0]+'" /><a href="#" onClick="editManageTrainingPartner();">edit</a> </td></tr>');	
		});
		}
		});
	return result;
	}
}

function editManageTrainingPartner(){
	var userId =  $("#mtpId").val();
	//alert(userId);
	$(".displayNone").css("display","block");
	 {
		var result="";
		var total = userId;
		$.ajax({
		type: 'post',
		url: 'editMTP.jspp?'+ total,
		async: false, 
		success: function (data){
		$('#newTable').show();
		var mainData1 = jQuery.parseJSON(data);
		//alert(mainData1);
		$.each(mainData1 , function(i , obj)
		{
			document.getElementById("trainingPartnerName").value = obj[3];
			document.getElementById("userId").value = obj[1];
			$("#trainingPartnerName").attr("disabled", "disabled"); 
			$("#userId").attr("disabled", "disabled"); 
			
			$("#PAN").attr("disabled", "disabled");
			document.getElementById("PAN").value = obj[2];
			document.getElementById("websiteUrl").value = obj[5];
			document.getElementById("email").value = obj[12];
			document.getElementById("headOfficeDataAddress1").value = obj[6];
			document.getElementById("headOfficeDataAddress2").value = obj[7];
			document.getElementById("pin").value = obj[8];
			
			var s = obj[9];
			var d = obj[10];
			var c = obj[11];
			//alert(s+' '+d+' '+c);
			if(obj[4]=="A"){
				//alert('a');class="form-control"
				$('#status option ').remove();
				//$('#status').addClass("form-control");
				$('#status').append('<option value="A" selected="true">Active</option><option value="I">In-active</option>');
			}else{
				//alert('i');
				$('#status option').remove();
				$('#status').append('<option value="A">Active</option><option value="I"  selected="true">In-active</option>');
			}
			getStateUpdate(s ,d , c);
		});
		}
		});
		$("#updateDiv").css("display","block");
		$("#createDiv").css("display","none");
	return result;
	}
}

function getStateUpdate(s , d , c)
{
	$.ajax({
	      type: 'post',
	      url: 'getStateUpdate.jspp',
	      success: function (response) {      
	      var mainData2 = jQuery.parseJSON(response);
	      $('#state option').remove();
	      $('#state').append('<option value="0" label="Select Stateeeeee" />');
	  	  
	      $.each(mainData2 , function(i , obj)
	  		{	
	    	  if(s == obj.stateId){
	    		  $('#state').append('<option value='+obj.stateId+' label='+obj.stateName+' selected="true" />');	
	    	  }else{
	    		  $('#state').append('<option value='+obj.stateId+' label='+obj.stateName+' />');	
	    	  }	
	  		});
	      }
	      });
	getDistrictUpdate(s , d , c);
}
function getDistrictUpdate(s , d , c)
{
	$.ajax({
	      type: 'post',
	      url: 'getDistrictUpdate.jspp?'+ s,
	      success: function (response) {      
	      var mainData1 = jQuery.parseJSON(response);
	      $('#district option').remove();
	      $('#district').append('<option value="0" label="Select District" />');
	  	  
	      $.each(mainData1 , function(i , obj)
	  		{
	    	  if(d == obj.districtId){
	    		  $('#district').append('<option value='+obj.districtId+' label='+obj.districtName+' selected="true"/>');
	    	  }else{
	    		  $('#district').append('<option value='+obj.districtId+' label='+obj.districtName+' />');
	    	  }	
	  		});
	      }
	      }); 
	getCityUpdate(d , c);
}
function getCityUpdate(d , c)
{
	$.ajax({
	      type: 'post',
	      url: 'getCityUpdate.jspp?'+ d,
	      success: function (response) {      
	      var mainData1 = jQuery.parseJSON(response);
	      $('#city option').remove();
	      $('#city').append('<option value="0" label="Select District" />');
	  	  
	      $.each(mainData1 , function(i , obj)
	  		{
	    	  if(c == obj.cityId){
	    		  $('#city').append('<option value='+obj.cityId+' label='+obj.cityName+' selected="true"/>');
	    	  }else{
	    		  $('#city').append('<option value='+obj.cityId+' label='+obj.cityName+' />');
	    	  }	
	  		});
	      }
	      });     
}
</script>
<script>
function updateMTP()
{
	var status = $("#status").val();;
	var url = $("#websiteUrl").val();;
	var email = $("#email").val();;
	var address1 = $("#headOfficeDataAddress1").val();;
	var address2 = $("#headOfficeDataAddress2").val();;
	var pin = $("#pin").val();;
	var state = $("#state").val();;
	var district = $("#district").val();;
	var city = $("#city").val();
	var mtpId = $('#mtpId').val();
	var total = status+','+url+','+email+','+address1+','+address2+','+pin+','+state+','+district+','+city+','+mtpId;
	$.ajax({
	      type: 'post',
	      url: 'updateMTP.jspp?'+ total,
	      data: {
	          user_name:name,
	         },
	      success: function (response) {    
	    	  $( '#name_status' ).html(response);  
	      }
	      }); 
}
</script>
<cf:form action="manageTrainingPartnerSave.fssai" name="myForm" method="POST" commandName="manageTrainingPartnerForm" onsubmit="return validateFields();"> 

    <section>
        <div class="container-fluid">
            <nav class="navbar navbar-default navbar-fixed-top horizontal-nav-top horizontal-top-nav-border">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                            </div>
                            <div id="navbar" class="navbar-collapse collapse">
                                <ul class="nav navbar-nav">
                                    <li></li>
                                    <li class="hori"><a href="adminHomepage.fssai">Home</a></li>
                                    <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User Management<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="traineeUserManagementForm.fssai" class="clr">Trainee</a></li>
                                            <li><a href="trainerUserManagementForm.fssai" class="clr">Trainer</a></li>
                                            <li><a href="trainingCenterUserManagementForm.fssai" class="clr">Training Center</a></li>
                                            <li><a href="assessorUserManagementForm.fssai" class="clr">Assessor</a></li>
                                            <li><a href="adminUserManagementForm.fssai" class="clr">Admin</a></li>
                                        </ul>
                                    </li>
                                    <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Master Data<span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="stateMaster.fssai" class="clr">State</a></li>
                                            <li><a href="districtMaster.fssai" class="clr">District</a></li>
                                            <li><a href="cityMaster.fssai" class="clr">City</a></li>
                                            <li><a href="regionMappingMaster.fssai" class="clr">Region Mapping</a></li>
                                        </ul>
                                    </li>
                                    <li class="active"><a href="manageTrainingPartnerForm.fssai">Manage Training Partner</a></li>
                                    <li><a href="manageAssessmentAgencyForm.fssai">Manage Assessment Agency</a></li>
                                </ul>
                                <ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown active"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cog fa-spin"></i> <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="changePasswordAssesAgency.fssai">Change Password</a></li>
                                            <li><a href="fostac.fssai">Logout</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!--/.nav-collapse -->
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </section>
    <!-- main body -->
    <section class="main-section-margin-top">
        <div class="container-fluid">
            <div id="wrapper">
                <!-- Sidebar -->
                <div id="sidebar-wrapper">
                    <ul class="sidebar-nav">
                        <!-- <li class="sidebar-brand"></li> -->
                        <li><a href="manageCourse.fssai">Manage Course</a></li>
                        <li><a href="manageCourseContent.fssai">Manage Course Content</a></li>
                        <li> <a href="trainingCalendarForm.fssai">Training Calendar</a> </li>
                        <li> <a href="assessmentSchedule.fssai">Update Assessor</a> </li>
                        <li> <a href="manageAssessmentQuestions.fssai">Assessment Questions</a> </li>
                        <li> <a href="updateTrainerAssessment.fssai">TOT Assessment</a> </li><li> <a href="feedback-master.html">Feedback Master</a> </li>
                    </ul>
                </div>
                <!-- /#sidebar-wrapper -->
                <!-- Page Content -->
                <div id="page-content-wrapper">
                    <div class="container-fluid">
                        <!-- vertical button -->
                        <div class="row">
                            <div class="col-lg-12">
                                <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome Admin</span> </a>
                            </div>
                        </div>
                        <!-- add the content here for main body -->
                        <!-- timeline  -->
                        <div class="row">
                            <div class="col-xs-12">
                                <h1>Manage Training Partner</h1>
                                
                                     <fieldset>
                                        <legend>Head Office Details</legend>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- left side -->
                                        <div class="col-md-6 col-xs-12">
                                            
                                             <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong><cs:message code="lbl.Trainee.UserId" /></strong></li>
                                                        <li class="style-li error-red">${created }<span id="name_status"> </span>
                                                        <cf:errors path="userId" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
 <cf:input path="userId" onkeypress="return AvoidSpace(event)"  placeholder="UserId" class="form-control" onblur  ="checkname();" onKeyUP="this.value = this.value.toUpperCase();" />
                                            </div>
                                            
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Training Partner Name:</strong></li>
                                                        <li class="style-li error-red">
                                                         <cf:errors path="trainingPartnerName" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
 <cf:input path="trainingPartnerName" maxlength="100"  placeholder="Training Partner Name" class="form-control" />
                                            </div>
                                            
                                              <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Website URL:</strong></li>
                                                        <li class="style-li error-red">
                                                         <cf:errors path="websiteUrl" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
 <cf:input path="websiteUrl"  maxlength="100" placeholder="Website URL" class="form-control" />
                                            </div>
                                            
                                           
                                           
                                        </div>
                                        <!-- left side ends -->
                                        <!-- right side -->
                                        <div class="col-md-6 col-xs-12">
                                            
                                           
                                            
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>PAN:</strong></li>
                                                        <li class="style-li error-red">
                                                        <span id="name_status"> </span>
                                                        <span id="panstatus"></span>
                                                        <cf:errors path="PAN" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
 <cf:input path="PAN" maxlength="10"  placeholder="PAN" class="form-control" onKeyUP="this.value = this.value.toUpperCase();" onblur="pan_validate(this.value);"/>
                                            </div>
                                            
                                             <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Status:</strong></li>
                                                        <li class="style-li error-red"></li>
                                                    </ul>
                                                </div>
<cf:select path="status" class="form-control">
<cf:option value="A" label="Active" />
<cf:option value="I" label="In-Active" />
</cf:select>
                                            </div>
 
  <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Email:</strong></li>
                                                        <li class="style-li error-red"></li>
                                                    </ul>
                                                </div>
<cf:input path="email" class="form-control" maxlength="100" />

                                            </div>                                           
                                        </div>
                                        <!-- rigth side ends -->
                                    </div>
                                </div>
</fieldset>
                            </div>
 <div style="height:50px;"></div>                          
                <div class="row">
                                <!-- head office data -->
                                <div class="col-xs-12">
                                    <fieldset>
                                        <legend>Head Office Data</legend>
                                        <!-- left side -->
                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Address 1:</strong></li>
                                                        <li class="style-li error-red">
                                                        <cf:errors path="headOfficeDataAddress1" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
 <cf:input path="headOfficeDataAddress1"  maxlength="100" placeholder="Address" class="form-control" />
                                            </div>
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Address 2:</strong></li>
                                                        <li class="style-li error-red">
                                                        <cf:errors path="headOfficeDataAddress2" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
 <cf:input path="headOfficeDataAddress2" maxlength="100"  placeholder="Address" class="form-control" />
                                            </div>
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>PIN:</strong></li>
                                                        <li class="style-li error-red">
                                                        <cf:errors path="pin" cssClass="error" />
                                                        </li>
                                                    </ul>
                                                </div>
<cf:input path="pin" maxlength="6"  placeholder="PIN" class="form-control" onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')"/>
                                            </div>
                                        </div>
                                        <!-- right side -->
                                        <div class="col-md-6 col-xs-12">
                                         <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>State:</strong></li>
                                                        <li class="style-li error-red"></li>
                                                    </ul>
                                                </div>
<cf:select path="state" class="form-control" onchange="getDistrict(this.value)">
<cf:option value="0" label="Select State" />
<cf:options items="${stateList}" itemValue="stateId" itemLabel="stateName" />
</cf:select>
                                            </div>
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>District:</strong></li>
                                                        <li class="style-li error-red"></li>
                                                    </ul>
                                                </div>
<cf:select path="district" class="form-control" onchange="getCity(this.value)">
<cf:option value="0" label="Select District"/>
</cf:select>
                                            </div>
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>City:</strong></li>
                                                        <li class="style-li error-red"></li>
                                                    </ul>
                                                </div>
<cf:select path="city" class="form-control">
<cf:option value="0" label="Select City" />
</cf:select> 
                                            </div>
                                        </div>
                                    </fieldset>
                                </div></div>
                                <!-- button -->
                                <div class="row" id="register1">
                                    <div class="col-md-6 col-xs-12"></div>
                                    <div class="col-md-6 col-xs-12" style="margin-top: 26px;">
                                        
<div id="createDiv" style="float:left;">
<input type="submit" id="register" class="btn login-btn" value="Create" />
</div>
<div id="updateDiv" style=" float:left; margin-left: 20px;">
<a href="#" onclick="updateMTP();" class="btn btn-default pull-right show-details-vacancy collapsed" 
data-toggle="collapse" data-target="#show-result" aria-expanded="false" style="margin-right: 0px;  ">
Update</a>
</div>
<div id="searchDiv" style=" float:left; margin-left: 20px;">
<a href="#" onclick="searchDataTP();" class="btn btn-default pull-right show-details-vacancy collapsed" 
data-toggle="collapse" data-target="#show-result" aria-expanded="false" style="margin-right: 15px; ">
Search</a>
</div>                                        
                            </div>
                                </div>
                          
                            <!-- search Results -->
                            <div class="col-xs-12 " id="show-result" aria-expanded="false" style="height: 0px;">
                                <!-- table -->
                                <div class="row">
                                    <div class="col-xs-12">
                                        <fieldset>
                                            <legend>Search Result</legend>
                                            <table id="newTable" class="table table-bordered table-responsive">
                                                <thead>
                                                    <tr class="background-open-vacancies">
                                                        <th>S.No.</th>
                                                        <th>Training Partner ID</th>
                                                        <th>Training Partner Name</th>
                                                        <th>Website Link</th>
                                                        <th>Status</th>
                                                        <th>Option</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <!-- search div ends -->
                    
                        <!-- row ends -->
                    </div>
             
        </div>
    </section>
    </cf:form>