<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
	    <title>CAS &#8211; Central Authentication Service</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <style type="text/css" media="screen">@import '<%= request.getContextPath() %>/skin/style/common.css'/**/;</style>
	    <style type="text/css" media="screen">@import '<%= request.getContextPath() %>/skin/style/style.css'/**/;</style>
	    <style type="text/css" media="screen">@import '<%= request.getContextPath() %>/skin/IntegralStyle.css'/**/;</style>
	    <script src="http://code.jquery.com/jquery-1.4.4.js"></script>
		<script language="JavaScript">
			$('span[id$=".errors"]').each(function() {
			     var fieldError = $(this).attr("id");
			     var field=fieldError.split(".errors");
			     if(document.getElementById(field[0]) != null){
			        document.getElementById(field[0]).style.background = "red";
			     }
			    });
		</script>
	    <style type="text/css">
			.cursor{
				cursor: pointer;
			}

			.firstColumn {
				width:217px;
				height:auto;
				padding: 0px;
				margin-right: 2px;
			}

			.secondColumn {
				float: left;
				width: auto;
				right: 0;
				left: 0;
				background-color: white;
				min-height: 430px;
			}

			.thirdColumn {
				margin-left: 0px;
				padding-left: 10px;
				float: left;
				width:700px;
				height:430px;
			}

			.mainView {
				width: 990px;
				height: 755px;
				margin-top:7px;
				margin-left:5px;
			}

			.rb_content {
			  width: 700px;
			}

			.rb_content_bottom  .rb_left{
				background-image: url(<%= request.getContextPath() %>/screenFiles/roundedBox/bottom_left.PNG);
			}

			.rb_content_bottom .rb_right{
				background-image: url(<%= request.getContextPath() %>/screenFiles/roundedBox/bottom_right.PNG);
			}

			.rb_content_top {
				background-image: url(<%= request.getContextPath() %>/screenFiles/roundedBox/bg_title_001.gif);
				width: 100%;
				padding: 0px;
			}

			.rb_content_middle {
				padding: 0px;
				width: 100%;
				height: 100%;
			}

			.rb_content_bottom {
				width: 102%;
			}

			.rb_content_bottom_area2 {
				width: 658px;
			}


			div.fct .rb_content_top{
				margin: 0px;
				width: 218px;
			}

			div.fct .rb_content_middle{
				height: 80px;
				margin: 0px;
				width: 218px;
			}

			div.fct .rb_content_bottom{
				margin: 0px;
				width: 220px;
			}

			div.fct .rb_content_bottom .rb_content_bottom_area2{
				margin: 0px;
				width: 176px;
			}

			.sectionbutton{
				width:160px;
				margin-top:10px;
				text-align: center;
			}

			.text{
				font-family:Arial;
				font-size:13;
				font-weight: bold;
			}

			.error-block{
				padding-left:12px;
				color: red;
			}

			.error{
				color: red;
				padding:0px;
				margin:0px;
				font-weight: bold;
			}

			.info{
				color: green;
				padding:0px;
				margin:0px;
				font-weight: bold;
			}

	    </style>
	</head>

	<table id="table" align="center"><tr><td>
	 <div id="portlet" class="mainView">
	    <div id="sidebar" style="float:left;">
	       <div style="margin-top:-1px;">
	        <tiles:insertAttribute name="logo"/>
	      </div>

	      <div class="firstColumn fct">
	        <tiles:insertAttribute name="links"/>
	        <div style="margin: 10px;"></div>
	        <tiles:insertAttribute name="quicklinks"/>
	      </div>
	    </div>
	    <div id="body"  style="float:left;">

	      <div class="secondColumn"></div>

	      <div class="rb_content thirdColumn mainview" style="margin-left:5px;margin-top:0px;">
	        <tiles:insertAttribute name="body"/>
	        <tiles:insertAttribute name="footer"/>
	      </div>
	    </div>
	</div>

	</td></tr></table>
</html>
