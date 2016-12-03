<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>
<%System.out.print("trainee home page jsp"); %>

<section>
	<%@include file="topMenuTrainee.jspf"  %>     
</section>

<!-- main body -->
<section class="main-section-margin-top">
      <div class="container-fluid">
    <div id="wrapper"> 
          
          <!-- Sidebar menu -->
    <%@include file="leftMenuTrainee.jspf"%>
       <!-- Sidebar menu -->
          <!-- /#sidebar-wrapper --> 
          <!-- Page Content -->
          <div id="page-content-wrapper">
        <div class="container-fluid"> 
              
              <!-- vertical button -->
              <div class="row">
            <div class="col-lg-12"> <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i>
             <span class="orange-font">
             Welcome Mr.  ${loginUser.loginDetails.loginId}</span> </a> </div>
          </div>
              
              <!-- add the content here for main body --> 
              <!-- timeline  -->
              
              <div class="container-fluid">
            <div class="row">
                  <div class="col-xs-12">
                <div class="page-header">
                      <h1 id="timeline">Certification Process</h1>
                    </div>
                <ul class="timeline">
                      <li>                    
                    <div class="timeline-badge success">1</div>
                    <div class="timeline-panel">
                          <div class="timeline-heading">
                        <h4 class="timeline-title">Step 1</h4>
                      </div>
                          <div class="timeline-body">
                        <p>Course Enrollment</p>
                        <p>
<ct:choose>
<ct:when test="${loginUser != null}">
<a href="basic.fssai">Basic</a> | <a href="advance.fssai">Advance</a> | <a href="special.fssai">Special</a> | <a href="../login-trainer/login.html">TOT</a>
</ct:when>
<ct:otherwise>
Basic | Advance | Special |  TOT
</ct:otherwise>
</ct:choose>                        
                      </p>
                      </div>
                        </div>
                  </li>
                      <li class="timeline-inverted">
<ct:choose>
	<ct:when test="${loginUser != null }">
  		<ct:set value="timeline-badge primary" var="cssClass"></ct:set>
  		<div class="${cssClass}">2</div> 
	</ct:when>
	<ct:otherwise>
		<ct:set value="timeline-badge success" var="cssClass"></ct:set>
		<div class="${cssClass}"> 2</div> 
	</ct:otherwise> 
</ct:choose>
                       
                    <!-- <div class="timeline-badge success">2</div> -->
                    <div class="timeline-panel">
                          <div class="timeline-heading">
                        <h4 class="timeline-title">Step-2</h4>
                      </div>
                          <div class="timeline-body">
                        <p>
<ct:choose>
<ct:when test="${loginUser != null}">
<a href="basic.fssai">Get Your Admit Card</a>
</ct:when>
<ct:otherwise>
Get Your Admit Card
</ct:otherwise>
</ct:choose>              
                        </p>
                      </div>
                        </div>
                  </li>
                      <li>
<ct:choose>
	<ct:when test="${loginUser != null}">
  		<ct:set value="timeline-badge primary" var="cssClass"></ct:set>
  		<div class="${cssClass}">3</div> 
	</ct:when>
	<ct:otherwise>
		<ct:set value="timeline-badge success" var="cssClass"></ct:set>
		<div class="${cssClass}"> 3</div> 
	</ct:otherwise> 
</ct:choose>                      
                    <!-- <div class="timeline-badge success">3</div> -->
                    <div class="timeline-panel">
                          <div class="timeline-heading">
                        <h4 class="timeline-title">Step-3</h4>
                      </div>
                          <div class="timeline-body">
                        <p>Attend Training</p>
                        <p>
<ct:choose>
<ct:when test="${loginUser != null}">
<a href="courseTraining.fssai?courseTypeId=1">Online</a> | <a href="courseTraining.fssai?courseTypeId=2">Classroom</a>
</ct:when>
<ct:otherwise>
Online | Classroom
</ct:otherwise>
</ct:choose> 

                        </p>
                      </div>
                        </div>
                  </li>
                      <li class="timeline-inverted">
<ct:choose>
	<ct:when test="${loginUser != null}">
  		<ct:set value="timeline-badge primary" var="cssClass"></ct:set>
  		<div class="${cssClass}">4</div> 
	</ct:when>
	<ct:otherwise>
		<ct:set value="timeline-badge success" var="cssClass"></ct:set>
		<div class="${cssClass}"> 4</div> 
	</ct:otherwise> 
</ct:choose>
                    <!-- <div class="timeline-badge success">4</div> -->
                    <div class="timeline-panel">
                          <div class="timeline-heading">
                        <h4 class="timeline-title">Step-4</h4>
                      </div>
                          <div class="timeline-body">
                        <p>Get Your Self Assessed</p>
                        <p>
<ct:choose>
<ct:when test="${loginUser != null}">
<a href="traineeAssessmentOnline.fssai">Online</a> | <a href="#">In Physical Center</a>
</ct:when>
<ct:otherwise>
Online | In Physical Center
</ct:otherwise>
</ct:choose> 
                        </p>
                      </div>
                        </div>
                  </li>
                      <li>
<ct:choose>
	<ct:when test="${loginUser != null}">
  		<ct:set value="timeline-badge primary" var="cssClass"></ct:set>
  		<div class="${cssClass}">5</div> 
	</ct:when>
	<ct:otherwise>
		<ct:set value="timeline-badge success" var="cssClass"></ct:set>
		<div class="${cssClass}"> 5</div> 
	</ct:otherwise> 
</ct:choose>                      
                    <!-- <div class="timeline-badge success">5</div> -->
                    <div class="timeline-panel">
                          <div class="timeline-heading">
                        <h4 class="timeline-title">Step-5</h4>
                      </div>
                          <div class="timeline-body">
                        <p>
<ct:choose>
<ct:when test="${loginUser != null}">
<a href="#"> Give Your Feedback</a>
</ct:when>
<ct:otherwise>
 Give Your Feedback
</ct:otherwise>
</ct:choose>
                       </p>
                      </div>
                        </div>
                  </li>
                      <li class="timeline-inverted">
<ct:choose>
	<ct:when test="${loginUser != null}">
  		<ct:set value="timeline-badge primary" var="cssClass"></ct:set>
  		<div class="${cssClass}">6</div> 
	</ct:when>
	<ct:otherwise>
		<ct:set value="timeline-badge success" var="cssClass"></ct:set>
		<div class="${cssClass}"> 6</div> 
	</ct:otherwise> 
</ct:choose>
                    <!-- <div class="timeline-badge success">6</div> -->
                    <div class="timeline-panel">
                          <div class="timeline-heading">
                        <h4 class="timeline-title">Step-6</h4>
                      </div>
                          <div class="timeline-body">
                        <p>
<ct:choose>
<ct:when test="${loginUser != null}">
<a href="#">  Get Your Certificate</a>
</ct:when>
<ct:otherwise>
Get Your Certificate
</ct:otherwise>
</ct:choose>                        
                       </p>
                      </div>
                        </div>
                  </li>
                    </ul>
              </div>
                </div>
          </div>
            </div>
      </div>
        </div>
  </div>
    </section>