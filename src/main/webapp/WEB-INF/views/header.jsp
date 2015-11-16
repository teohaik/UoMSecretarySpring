<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="<c:url value="/resources/assets/images/favicon.png" />">

        <title>University of Macedonia, Secretary</title>

        <!-- Bootstrap core CSS -->
        <link type="text/css" href="<c:url value="/resources/assets/css/bootstrap.min.css" />" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link type="text/css" href="<c:url value="/resources/assets/css/sticky-footer-navbar.css" />" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="<c:url value="/resources/assets/js/ie-emulation-modes-warning.js" />"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <!-- Fixed navbar -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">UoM Secretary</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/lessons">Lessons</a></li>
                        <li><a href="${pageContext.request.contextPath}/professors">Professors</a></li>
                        <li><a href="${pageContext.request.contextPath}/secretaries">Secretaries</a></li>
                        <li><a href="${pageContext.request.contextPath}/myAccount">My Account</a></li>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="isAuthenticated()">
	                        <li>
	                        	<c:url var="logoutUrl" value="/logout" />
	                        	<form action="${logoutUrl}" method="post">
	                        		<input class="btn btn-link" role="button" type="submit" value="Log out" /> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                        	</form>
	                        </li>
                        </sec:authorize>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>