<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript">
        $("#btnPrint").live("click", function () {
            var divContents = $("#dvContainer").html();
            var printWindow = window.open('', '', 'height=400,width=800');
            /* printWindow.document.write('<html><head><title>DIV Contents</title>'); */
            printWindow.document.write('</head><body >');
            printWindow.document.write(divContents);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.print();
        });
    </script>
    
</head>
<body>
<form>
    <!-- horizontal navigation -->
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
                                    <li class="hori"><a href="index.fssai">Home</a></li>
                                    <li class="hori"><a href="search-and-apply.fssai">Search & Apply Vacancy</a></li>
                                    <li class="hori"><a href="update-profile.fssai">Update Profile</a></li>
                                    <li class="hori"><a href="contactTrainer.fssai">Contact Us</a></li>
                                </ul>
                                <ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown active"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cog fa-spin"></i> <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="changePasswordTrainer.fssai">Change Password</a></li>
                                            <li><a href="#">Logout</a></li>
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
                        <!-- <li class="sidebar-brand">
                        </li> -->
                        <li class="dropdown active"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Course Enrollment <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="basic.fssai" class="clr">Basic Course</a></li>
                                <li><a href="advance.fssai" class="clr">Advanced Course</a></li>
                                <li><a href="special.fssai" class="clr">Special Course</a></li>
                            </ul>
                        </li>
                        <li> <a href="generateadmitcardtrainer.fssai">Generate Admit Card</a> </li>
                        <li> <a href="course-training.fssai">Training</a> </li>
                        <li> <a href="assessment-instructions.fssai">Assessment</a> </li>
                        <li> <a href="feedback-form.fssai">Feedback</a> </li>
                        <li> <a href="generatecertificatetrainer.fssai">Certification</a> </li>
                    </ul>
                </div>
                <!-- /#sidebar-wrapper -->
                <!-- Page Content -->
                <div id="page-content-wrapper">
                    <div class="container-fluid">
                        <!-- vertical button -->
                        <div class="row">
                            <div class="col-lg-12">
                                <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome Mr. ${loginUr.firstName}</span> </a>
                            </div>
                        </div>
                        <!-- add the content here for main body -->
                        <!-- timeline  -->
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="page-header">
                                        <h1 id="timeline">Generate Admit Card</h1> </div>
                                    <!-- admit card starts -->
                                    <div id="dvContainer">
                                    <table align="center" width="856" border="0" style="border:1px solid #CCC;">
                                        <tr>
                                            <td width="35%">
                                                <table border="0">
                                                    <tr>
                                                        <td width="50%" align="center" class="border-bottom-logo"><img src="${pageContext.request.contextPath}/website/img/admit-card-fssai-logo.png" alt="" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="50%" align="center" class="border-bottom-logo" style="border-bottom:0px;"><img src="${pageContext.request.contextPath}/website/img/admit-card-fostac-logo.png" alt="" /></td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td width="50%">
                                                <table width="100%" border="0">
                                                    <tr>
                                                        <td align="center" class="heading">Food Safety Training & Certificate</td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="sub-heading">FDA Bhavan, Kotla Road, Delhi ITO - 2016 - 2017</td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" class="sub-heading" style="padding-top:20px;">Admit Card for HTET 2016-2017</td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center"><img src="${pageContext.request.contextPath}/website/img/admit-card-barcode.png" width="182" height="41" alt="" /></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="35%" style="border-top: 1px solid #CCC; border-right: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;">Roll Number: 322</td>
                                            <td width="50%" style="border-top: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;">Applied for Course: Basic Food Safety Certification</td>
                                        </tr>
                                        <tr>
                                            <td width="35%" style="border-top: 1px solid #CCC; border-right: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;">Name: ${loginUr.firstName}</td>
                                            <td width="50%">
                                                <table width="100%" border="0">
                                                    <tr>
                                                        <td width="52%" style="border-top: 1px solid #CCC; border-right: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold; margin:0px;">Category: General</td>
                                                        <td width="48%" style="border-top: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold; margin:0px;">Gender: Male</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="52%" style="border-top: 1px solid #CCC; border-right: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold; margin:0px;">&nbsp;</td>
                                                        <td width="48%" style="border-top: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold; margin:0px;">City: Gurgaon</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="35%" style="border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; border-right: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;">Father's Name: Mr. RK Ghosh</td>
                                            <td width="50%" style="border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;"></td>
                                        </tr>
                                        <tr>
                                            <td width="35%" style="padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;">Course Content:
                                                <ol type="i" style="font-weight:normal; line-height:22px;">
                                                    <li>Food Safety</li>
                                                    <li>GHP-GMP</li>
                                                    <li>GHP-GMP-HACCP</li>
                                                </ol>
                                            </td>
                                            <td width="50%" style="padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <table width="100%" border="0" style="border-top: 1px solid #CCC;">
                                                    <tr>
                                                        <td align="center" style="padding:10px; border-right: 1px solid #CCC; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;"><strong>Centre of Examination:</strong>
                                                            <p style="font-weight:normal;">Centre Code: 1104,
                                                                <br> FDA Bhavan, ITO Bhvan</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center" style="border-top: 1px solid #CCC; border-right: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;"><img src="${pageContext.request.contextPath}/website/img/authorize-signature.png" width="58" height="30" alt="" />
                                                            <br> <strong>Authorize Signatory</strong></td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td align="center" style="border-top: 1px solid #CCC; padding:10px; font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size:14px; font-weight:bold;">
                                                <table width="100%" border="0">
                                                    <tr>
                                                        <td width="61%" align="center"><img src="${pageContext.request.contextPath}/website/img/admit-card-candidate-photo.png" width="76" height="93" alt="" />
                                                            <br> <strong>Candidate Photo</strong></td>
                                                        <td width="39%"><img src="${pageContext.request.contextPath}/website/img/admit-card-candidate-signature.png" width="108" height="55" alt="" />
                                                            <br> <strong>Canditate Signature</strong></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    </div>
                                    <!-- admit card ends -->
                                    
                                    <!-- button -->
                                    <div class="col-md-6 col-xs-12"></div>
                                    <div class="col-md-6 col-xs-12">
                                        <br><br>
                                     <input type="button" value="Download AdmitCard" id="btnPrint" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- scripts -->
    </form>
</body>
</html>
    
    