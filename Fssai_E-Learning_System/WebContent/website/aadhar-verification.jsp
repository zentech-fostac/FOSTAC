<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>
<script> 
    function AvoidSpace(event) {
        var k = event ? event.which : window.event.keyCode;
        if (k == 32) return false;
    }
</script>
<cf:form action="registrationForm.fssai"  name="myForm" method="POST" commandName="aadharDetails" > 

<!-- main section -->
<section class="section-form-margin-top">
  <div class="container"> 
    <!-- login form -->
    <div class="row">
      <div class="col-md-2 hidden-xs"></div>
      <div class="col-md-8  col-xs-12">
        <h3 class="text-capitalize heading-3-padding" style="font-size: 24px; text-align: left;">Trainee Registration Form</h3>
        <!-- personel info Start -->
        <div class="personel-info" style="margin-bottom: 40px;">
          <fieldset>
            <legend>
            <h3 style="font-size: 20px; font-weight: normal;">Personal Information</h3>
            </legend>
            <!-- left side -->
            <div class="col-md-6 col-xs-12">
              <div class="form-group">
                <div>
                  <ul class="lab-no">
                    <li class="style-li"><strong>Aadhar Number:</strong></li>
                    <li class="style-li error-red"><cf:errors id="aadharNumber" /> </li>
                  </ul>
                </div>
                <cf:input path="aadharNumber" class="form-control" onkeypress="return AvoidSpace(event)" placeholder="Aadhar Number"   maxlength="12" onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')" />
              </div>
              <div class="form-group">
                <div>
                  <ul class="lab-no">
                    <li class="style-li"><strong>Date Of Birth:</strong></li>
                    <li class="style-li error-red"> </li>
                  </ul>
                </div>
                <cf:input path="dob" type="date" class="form-control" placeholder="Date of birth" />
              </div>

            </div>
            <!-- right side -->
            <div class="col-md-6 col-xs-12">
              <div class="form-group">
                <div>
                  <ul class="lab-no">
                    <li class="style-li"><strong>Full Name:</strong></li>
                    <li class="style-li error-red"></li>
                  </ul>
                </div>
                <cf:input path="name" class="form-control" placeholder="Full Name" />
              </div>


<div class="form-group">
<div>
<ul class="lab-no">
<li class="style-li"><strong><cs:message code="lbl.Trainee.Gender" /></strong></li>
<li class="style-li error-red">
<label id="errorgender" class="error visibility">* error</label>
<cf:errors path="gender" cssClass="error" /></li>
</ul>
</div>
<label class="radio-inline">
<cf:radiobutton path="gender" value="M" checked="true" />Male&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <cf:radiobutton path="gender" value="F" />Female
</label>
</div>
            </div>
            
            <div class="col-xs-12" style="margin-top: 20px; text-align: -webkit-center;"> <a href="#aadhar-verification" class="btn login-btn" data-toggle="modal" data-target="#aadhar-verification" style="width: 40%;">Verify</a> 
              
              <!-- details of modal -->
              <div id="aadhar-verification" class="modal fade" role="dialog">
                <div class="modal-dialog"> 
                  
                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-body"> <br>
                      <p class="text-center" style="color: green;">Aadhar Verified!</p>
                      <p class="text-center">Your Linked Bank Account : State Bank of India</p>
                      <!-- button -->
                      <div class="col-xs-12 text-center"> <a href="registrationFormTrainee.fssai" class="btn login-btn">Ok</a> </div>
                      <br>
                      <br>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
          </fieldset>
        </div>
      </div>
      <div class="col-md-2 hidden-xs"></div>
    </div>
  </div>
</section>
</cf:form>