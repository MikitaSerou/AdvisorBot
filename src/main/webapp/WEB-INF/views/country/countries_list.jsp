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
    <title><spring:message code="all.countries"/></title>
    <link href='<spring:url value="/css/bootstrap.css"/>' rel="stylesheet"/>

    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../import/navigation_bar.jsp"/>
<jsp:include page="../import/up_button.jsp"/>
<div class="container">
    <div class="row">
        <h1><spring:message code="all.countries"/></h1>
        <table class="table table-hover table-dark"
               border="1">
            <thead>
            <th><spring:message code="id"/></th>
            <th><spring:message code="name"/></th>
            <th><spring:message code="abbreviation"/></th>
            <th><spring:message code="currency"/></th>
            <th><spring:message code="action"/></th>
            </thead>
            <c:forEach items="${countriesList}" var="country">
                <tr>

                    <td>${country.id}</td>
                    <td><a href="${pageContext.request.contextPath}/country/${country.id}"><h3 style="color: White">
                            ${country.name}</h3>
                    </a></td>
                    <td>${country.abbreviation.toUpperCase()}</td>
                    <td>
                        (${country.currency.name.toUpperCase()})
                    </td>

                    <td>
                        <a data-method='delete'
                           href='${pageContext.request.contextPath}/city/delete/${country.id}'>link</a>
                        <button class="delete"
                                data-target="${pageContext.request.contextPath}/city/delete/${country.id}"
                                data-method="DELETE" data-disabled="true">Delete Article
                        </button>
                        <a data-confirm="Are you sure?" data-method="delete"
                           href="${pageContext.request.contextPath}/city/delete/${country.id}" rel="nofollow">Delete</a>
                        <form action="${pageContext.request.contextPath}/country/delete" method="post">
                            <button type="submit" class="btn btn-danger"><spring:message code="delete"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>