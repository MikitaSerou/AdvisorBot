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
    <title><spring:message code="add.city"/></title>
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
            <h1 align="center"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="red"
                                      class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
                <path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z"></path>
            </svg>
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-building"
                                    viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M14.763.075A.5.5 0 0 1 15 .5v15a.5.5 0 0 1-.5.5h-3a.5.5
                     0 0 1-.5-.5V14h-1v1.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V10a.5.5 0 0 1 .342-.474L6
                     7.64V4.5a.5.5 0 0 1 .276-.447l8-4a.5.5 0 0 1 .487.022zM6 8.694 1 10.36V15h5V8.694zM7
                     15h2v-1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5V15h2V1.309l-7 3.5V15z"></path>
                <path d="M2 11h1v1H2v-1zm2 0h1v1H4v-1zm-2 2h1v1H2v-1zm2 0h1v1H4v-1zm4-4h1v1H8V9zm2
                     0h1v1h-1V9zm-2 2h1v1H8v-1zm2 0h1v1h-1v-1zm2-2h1v1h-1V9zm0 2h1v1h-1v-1zM8 7h1v1H8V7zm2
                     0h1v1h-1V7zm2 0h1v1h-1V7zM8 5h1v1H8V5zm2 0h1v1h-1V5zm2 0h1v1h-1V5zm0-2h1v1h-1V3z"></path>
            </svg>
                <spring:message code="new.city"/>
            </h1>
            <br/>
            <br/>
            <style>
                .error {
                    color: red;
                    margin-left: 5px;
                }
            </style>
            <form id="city_form" action="${pageContext.request.contextPath}/city/new" method="post">
                <h4 id="name"><spring:message code="name"/>:</h4>
                <p class="error">${empty_name}</p>
                <p class="error">${not_unique_name}</p>
                <p class="error">${not_cyrillic_name}</p>
                <input type="text" class="form-control" name="name" minlength="3"
                       placeholder="<spring:message code="name"/>"/>
                <br/>
                <h4><spring:message code="country"/>:</h4>
                <select name="countryId" class="form-select" aria-label="Default select example">
                    <c:forEach items="${countries}" var="country">
                        <option name="countryId" value=${country.id}>
                                ${country.name}</option>
                    </c:forEach>
                </select>
                <br/>
                <h4><spring:message code="is.capital"/>:</h4>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="isCapital" value="true" id="flexRadioDefault2">
                    <label class="form-check-label" for="flexRadioDefault2">
                        <spring:message code="true"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="isCapital" value="false" id="flexRadioDefault1"
                           checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                        <spring:message code="false"/>
                    </label>
                </div>
                <br/>
                <h4 id="description"><spring:message code="description"/>:</h4>
                <p class="error">${empty_description}</p>
                <div class="form-group">
                    <textarea name="description" class="form-control" id="exampleFormControlTextarea1"
                              placeholder="<spring:message code="description"/>" rows="3"></textarea>
                </div>
                <br/>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button id="btnSubmit" class="btn btn-success" type="submit" formmethod="post">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor"
                             class="bi bi-plus" viewBox="0 0 16 16">
                            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0
                        1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                        </svg>
                        <spring:message code="add.city"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../import/footer.jsp"/>
</body>
</html>
