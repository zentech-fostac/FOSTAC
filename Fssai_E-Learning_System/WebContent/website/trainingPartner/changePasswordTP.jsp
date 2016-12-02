    
<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
function OnStart(){
	//alert('kk');
	BlankFields();
}
window.onload=OnStart;
</script>
<script>
function BlankFields(){
	document.getElementById("oldPassword").value='';
	document.getElementById("newPassword").value='';
	document.getElementById("confirmPwd").value='';
}
</script>

<script>
function pwdConfirm(){
	var pwd1 = document.getElementById("newPassword").value;
	var pwd2 = document.getElementById("confirmPwd").value;
	if(pwd1 == pwd2){
		//alert('ggg');
	}else{
		document.getElementById("confirmPwd").value="";
		alert('Passwords does not match');
	}
}
</script>
<script>
function pwd(){
	var pwd3 = document.getElementById("loginid").value;
	var pwd4 = document.getElementById("loginidd").value;
	//alert(pwd3 + "  " + pwd4)
	if(pwd3 == pwd4){
		//document.getElementById("password").value="";
		alert('Old passwords does not match');
	}else{
		//alert('kjkj');
	}
}
</script>
<cf:form action="changePasswordTrainingartnerSave.fssai" commandName="changePasswordTrainingPartner" name="myForm" method="POST" onsubmit="return pwdConfirm();" >

<!-- horizontal navigation -->
<section>
  <div class="container-fluid">
    <nav class="navbar navbar-default navbar-fixed-top horizontal-nav-top horizontal-top-nav-border">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li class="active hori"><a href="#">Home</a></li>
                                    <li><a href="training-center-management.html">Training Center Management</a></li>
                                    <li><a href="contactTrainingPartner.fssai">Contact Us</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li class="dropdown active"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cog fa-spin"></i> <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="changePasswordTrainingPartner.fssai">Change Password</a></li>
                    <li><a href="fostac.fssai">Logout</a></li>
                  </ul>
                </li>
              </ul>
            </div>
        <!--/.nav-collapse --> 
      </div>
    </nav>
  </div>
</section>

<!-- main body -->
<section class="main-section-margin-top">
  <div class="container-fluid">
    <div id="wrapper"> 
      
      <!-- Sidebar menu -->
      <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
          <!-- <li class="sidebar-brand">
                        </li> -->
          <li><a href="postVacancyTrainingPartner.fssai">Post Trainer Vacancy</a></li>
                        <li><a href="trainer-application-status.html">Trainer Application Status</a></li>
                        <li><a href="view-training-calendar.html">View Training Calendar</a></li>
                        <li><a href="view-feedback.html">View Feedback</a></li>
          
        </ul>
      </div>
      <!-- Sidebar menu --> 
      <!-- /#sidebar-wrapper --> 
      <!-- Page Content -->
      <div id="page-content-wrapper">
        <div class="container-fluid"> 
          <!-- vertical button -->
          <div class="row">
            <div class="col-lg-12"> <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome Training Partner</span> </a> </div>
          </div>
          <!-- add the content here for main body --> 
          <!-- timeline  -->
          <section class="section-form-margin-top">
            <div class="container"> 
              <!-- login form -->
              <div class="row btm-margin">
                <div class="col-md-6  col-xs-12">
                  <h3 class="text-capitalize heading-3-padding">Change Password</h3>
                </div>
                <div class="col-md-2 hidden-xs"></div>
              </div>
            </div>
          </section>
          <section class="section-form-margin-top">
            <div class="container-fluid"> 
              <!-- login form -->
              <div class="row">
                <div class="col-md-6 col-xs-12">
                  <form>
                    <!-- left side -->
                    <div class="col-xs-12"> 
                      <!-- residential address -->
                      <div class="form-group">
                        <label>Current Password</label>
                        <cf:errors path="oldPassword" cssclass="error" /><h4 style="color: Red">${created }</h4>
						<cf:input path="oldPassword" class="form-control" placeholder="Password" onblur="pwd();"/>
                      </div>
                      <div class="form-group">
                        <label>New Password</label>
                        <cf:errors path="newPassword" cssclass="error" />
                        <cf:input path="newPassword" class="form-control" placeholder="Password"/>
                      </div>
                      <div class="form-group">
                        <label>Confirm Password</label>
                        <cf:input path="confirmPwd" class="form-control" placeholder="Confirm Password"/>
                      </div>
                      <div class="form-group">
                        <input type="submit" class="form-control login-btn" value="Confirm Password" />
                      </div>
                    </div>
                    
                    <!-- right side -->
                  </form>
                </div>
                <div class="col-md-6  col-xs-12">
                  <p><strong>Password Policy</strong></p>
                  <ol>
                    <li>Minimum Password length should be 7 charecters</li>
                    <li>Maximum Password length should not exceed 15 charecters</li>
                    <li>Password must contain one alphabet and one number</li>
                  </ol>
                </div>
                <div class="col-md-2 hidden-xs"></div>
              </div>
            </div>
          </section>
          </div>
          </div>
          </div>
          </div>
          </section>
          </cf:form>
          <!-- scripts --> 
        