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
    <title>${city.name} - <spring:message code="edit.city"/></title>
    <link href='<spring:url value="/css/bootstrap.css"/>' rel="stylesheet"/>

    <script src="http://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../import/navigation_bar.jsp"/>

<div class="container" style="width: 50%;">
    <div class="row justify-content-center align-items-center">
        <div class="row">
            <h1 align="center">
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor"
                     class="bi bi-building"
                     viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M14.763.075A.5.5 0 0 1 15 .5v15a.5.5 0 0 1-.5.5h-3a.5.5
                     0 0 1-.5-.5V14h-1v1.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V10a.5.5 0 0 1 .342-.474L6
                     7.64V4.5a.5.5 0 0 1 .276-.447l8-4a.5.5 0 0 1 .487.022zM6 8.694 1 10.36V15h5V8.694zM7
                     15h2v-1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5V15h2V1.309l-7 3.5V15z"></path>
                    <path d="M2 11h1v1H2v-1zm2 0h1v1H4v-1zm-2 2h1v1H2v-1zm2 0h1v1H4v-1zm4-4h1v1H8V9zm2
                     0h1v1h-1V9zm-2 2h1v1H8v-1zm2 0h1v1h-1v-1zm2-2h1v1h-1V9zm0 2h1v1h-1v-1zM8 7h1v1H8V7zm2
                     0h1v1h-1V7zm2 0h1v1h-1V7zM8 5h1v1H8V5zm2 0h1v1h-1V5zm2 0h1v1h-1V5zm0-2h1v1h-1V3z"></path>
                </svg>
                <span style="color: crimson; font-weight: bold;">${city.name}</span>&nbsp;-&nbsp;
                <spring:message code="edit.city"/>
                <br/>
                <br/>
            </h1>
            <style>
                .error {
                    color: red;
                    margin-left: 5px;
                }
            </style>
            <form id="city_form" action="${pageContext.request.contextPath}/city/edit/${city.id}" method="post">
                <h4 id="name"><spring:message code="new.name"/>:</h4>
                <p class="error">${empty_name}</p>
                <p class="error">${not_cyrillic_name}</p>
                <input type="text" class="form-control" name="newName" minlength="3"
                       placeholder="${city.name.toUpperCase()}" value="${city.name.toUpperCase()}"/>
                <br/>
                <h4><spring:message code="country"/>:</h4>
                <select name="newCountryId" class="form-select" aria-label="Default select example">
                    <option selected name="newCountryId" value=${city.country.id}>${city.country.name}</option>
                    <c:forEach items="${countries}" var="country">
                        <c:if test="${!city.country.equals(country)}">
                            <option name="newCountryId" value=${country.id}>${country.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <br/>
                <h4><spring:message code="is.capital"/>:</h4>
                <c:if test="${city.isCapital}">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="newCapitalStatus" value="true"
                               id="flexRadioDefault2"
                               checked>
                        <label class="form-check-label" for="flexRadioDefault2">
                            <spring:message code="true"/>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="newCapitalStatus" value="false"
                               id="flexRadioDefault3">
                        <label class="form-check-label" for="flexRadioDefault3">
                            <spring:message code="false"/>
                        </label>
                    </div>
                </c:if>
                <c:if test="${!city.isCapital}">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="newCapitalStatus" value="true"
                               id="flexRadioDefault4">
                        <label class="form-check-label" for="flexRadioDefault4">
                            <spring:message code="true"/>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="newCapitalStatus" value="false"
                               id="flexRadioDefault5"
                               checked>
                        <label class="form-check-label" for="flexRadioDefault5">
                            <spring:message code="false"/>
                        </label>
                    </div>
                </c:if>

                <br/>
                <h4 id="newDescription"><spring:message code="new.description"/>:</h4>
                <p class="error">${empty_description}</p>
                <div class="form-group">
                    <textarea name="newDescription" class="form-control" id="exampleFormControlTextarea1"
                              placeholder="${city.description}" rows="3">${city.description}</textarea>
                </div>
                <br/>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button id="btnSubmit" class="btn btn-warning" type="submit" formmethod="post">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                             class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1
                                         .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805
                                          2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z">
                            </path>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0
                                        1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0
                                        1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                        </svg>
                        <spring:message code="edit.city"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../import/footer.jsp"/>
</body>
</html>