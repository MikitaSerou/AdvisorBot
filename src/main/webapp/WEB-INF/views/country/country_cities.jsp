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
    <title>${country.name}</title>
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
        <h1><spring:message code="all.cities"/> (${country.name})</h1>
        <table class="table table-hover table-dark"
               border="1">
            <thead>
            <th><spring:message code="id"/></th>
            <th><spring:message code="name"/></th>
            <th><spring:message code="country"/></th>
            <th><spring:message code="capital"/></th>
            <th><spring:message code="description"/></th>
            <th><spring:message code="action"/></th>
            </thead>
            <c:forEach items="${citiesOfCountry}" var="city">
                <tr>
                    <a href="/city/${city.id}">
                        <td>${city.id}</td>
                        <td><h3 style="color: White">${city.name}</h3></td>
                        <td>${city.country.name} (${city.country.abbreviation.toUpperCase()})</td>
                        <td>
                            <c:if test="${city.isCapital}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="green"
                                     class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0
                           0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-
                           .02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
                                </svg>
                            </c:if>
                        </td>
                        <td>${city.description}</td>
                    </a>
                    <td>
                        <a data-method='delete' href='${pageContext.request.contextPath}/city/delete/${city.id}'>link</a>
                        <button class="delete" data-target="${pageContext.request.contextPath}/city/delete/${city.id}"
                                data-method="DELETE" data-disabled="true">Delete Article</button>
                        <a data-confirm="Are you sure?" data-method="delete"
                           href="${pageContext.request.contextPath}/city/delete/${city.id}" rel="nofollow">Delete</a>
                        <form action="${pageContext.request.contextPath}/city/delete" method="post">
                            <button type="submit" class="btn btn-danger"><spring:message code="delete"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</div>
</body>
</html>