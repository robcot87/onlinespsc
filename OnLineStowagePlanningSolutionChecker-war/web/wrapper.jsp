

<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html xml:lang="it" lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="funzioni_javascript.js"></script>
<title>OnLine Stowage Planning Solution Checker</title>

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.css">



<script src="./bootstrap/js/jquery.min.js"></script>
<script src="./bootstrap/js/bootstrap.js"></script>



<!-- or the full Bootstrap JS if you want other features
<script src="http://twitter.github.com/bootstrap/assets/js/bootstrap.js"></script> -->

<style type="text/css">

*, html {
	margin: 0;padding: 0;
}

body {
	background: #f9f9f9;
}
.navbar-search .search-query {
    padding-left: 29px !important;
}/*lascia spazio per l'icona'*/
.navbar-search .icon-search {
    position: absolute;
    top: 7px;
    left: 11px;
}/* spazio per inserire il simbolo di ricerca*/
#wrapper {
	width: 940px;
	min-height: 500px;
	margin: 50px auto;
	padding: 25px;
	border-radius: 5px;
	background: #fff;
	box-shadow: 0 0 1px 1px #eee;
}

	/* dropdown
	------------------------------------------------------- */

.dropdown-menu {
	display: none;
	background: #f9f9f9;
	border: 1px solid #ddd;
	border-top: none;
	z-index: 9;
}
.dropdown-menu:hover {
	cursor: pointer;
        display: block;
}
.dropdown-menu li a {
	font-weight: normal;
}
ul.nav li.dropdown:hover ul.dropdown-menu{
    display: block;
}
.pull-right, [class*="span"] .pull-right {
float: right;
}
.pull-left, [class*="span"] .pull-left {
float: left;
}
.container { margin-left: 0px; }
.navbar .nav > li > a {
padding: 10px 11px 10px;
}

</style>

</head>
<div id="wrapper">

          <div id="navbar-example" class="navbar navbar-static">
            <div class="navbar-inner" style="padding-left:0px;height: 55px;padding-right: 2px">
              <div class="container">
                <ul class="nav" >
                        <li>
                          
                        </li>
                        <li class="divider-vertical" ></li>
                        <li><a href="Controller?button=index"><img src="img/vessel_icon3.jpg" width="100" height="100%" style=" margin-top: 7px"/></a></li>
                        <li class="divider-vertical" ></li>
	             	<li>
                            <a href="Controller?button=index">
                                <p onmouseover="highlightLink(this)" onmouseout="unhighlightLink(this)" style="padding-top: 5px">
                                    <!--<img src="img/vessel_icon3.jpg" width="100" height="100%" />-->
                                   <span><b>On Line Stowage Planning Solution Checker</b></span>
                                    
                                </p>
                            </a>
			</li>
                        
                       
	</ul>
                  
        
	


              </div>
            </div>
          </div> <!-- fine topbar parte condivisa da tutte le pagine-->

