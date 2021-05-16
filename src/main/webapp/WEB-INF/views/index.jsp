<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" type="image/x-icon"
          href="<spring:url value='/images/favicon.ico/'/>"/>
    <title><spring:message code="bot.name"/></title>
    <link href='<spring:url value="/css/bootstrap.css"/>' rel="stylesheet"/>

    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="import/navigation_bar.jsp" />


<h1><spring:message code="main.page"/></h1>
<div class="container" <%--style="min-height: 80%"--%>>
    <div class="row">
        <div class="col-sm-9"><img src='<spring:url value="https://www-cdn.intex-press.by/wp-content/uploads/2018/08/5-6.jpg"/>'
                                   width="100%" class="rounded" alt="kek"></div>
        <div class="col-sm-3">EEE BOIII!!</div>
    </div>
</div>
</body>
</html>

