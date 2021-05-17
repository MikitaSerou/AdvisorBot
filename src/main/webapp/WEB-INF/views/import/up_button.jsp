<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href='<spring:url value="/css/bootstrap.css"/>' rel="stylesheet"/>

    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
   <%-- <script src="<c:url value="/js/scrollButtons.js"/>"></script>--%>
</head>
<body>
<style>
    .nav_up{
        padding:7px;
        background-color:#ee5f5b;
        border:3px solid #CCC;
        position:fixed;
        background-position:50% 50%;
        width:40px;
        height:40px;
        bottom:60px;
        left:20px;
        white-space:nowrap;
        cursor: pointer;
        border-radius: 5px;
        opacity:0.7;
        filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);
    }
</style>
<div style="display:none;" class="nav_up" id="nav_up"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                                           fill="#CCC"
                                                           class="bi bi-chevron-up" viewBox="0 0 16 16">
    <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646
     5.647a.5.5 0 0 1-.708-.708l6-6z"/>
</svg></div>
</body>
</html>