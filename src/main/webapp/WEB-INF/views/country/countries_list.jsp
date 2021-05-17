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
        <h1>
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-globe"
                 viewBox="0 0 16 16">
                <path d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm7.5-6.923c-.67.204-1.335.82-1.887 1.855A7.97 7.97 0 0
                 0 5.145 4H7.5V1.077zM4.09 4a9.267 9.267 0 0 1 .64-1.539 6.7 6.7 0 0 1 .597-.933A7.025 7.025 0 0 0
                  2.255 4H4.09zm-.582 3.5c.03-.877.138-1.718.312-2.5H1.674a6.958 6.958 0 0 0-.656 2.5h2.49zM4.847
                  5a12.5 12.5 0 0 0-.338 2.5H7.5V5H4.847zM8.5 5v2.5h2.99a12.495 12.495 0 0 0-.337-2.5H8.5zM4.51
                   8.5a12.5 12.5 0 0 0 .337 2.5H7.5V8.5H4.51zm3.99 0V11h2.653c.187-.765.306-1.608.338-2.5H8.5zM5.145
                    12c.138.386.295.744.468 1.068.552 1.035 1.218 1.65 1.887 1.855V12H5.145zm.182 2.472a6.696 6.696
                    0 0 1-.597-.933A9.268 9.268 0 0 1 4.09 12H2.255a7.024 7.024 0 0 0 3.072 2.472zM3.82 11a13.652 13
                    .652 0 0 1-.312-2.5h-2.49c.062.89.291 1.733.656 2.5H3.82zm6.853 3.472A7.024 7.024 0 0 0 13.745 1
                    2H11.91a9.27 9.27 0 0 1-.64 1.539 6.688 6.688 0 0 1-.597.933zM8.5 12v2.923c.67-.204 1.335-.82 1.
                    887-1.855.173-.324.33-.682.468-1.068H8.5zm3.68-1h2.146c.365-.767.594-1.61.656-2.5h-2.49a13.65 13
                    .65 0 0 1-.312 2.5zm2.802-3.5a6.959 6.959 0 0 0-.656-2.5H12.18c.174.782.282 1.623.312 2.5h2.49zM1
                    1.27 2.461c.247.464.462.98.64 1.539h1.835a7.024 7.024 0 0 0-3.072-2.472c.218.284.418.598.597.
                    933zM10.855 4a7.966 7.966 0 0 0-.468-1.068C9.835 1.897 9.17 1.282 8.5 1.077V4h2.355z"></path>
            </svg>
            <spring:message code="all.countries"/></h1>
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
                <tr id="${country.id}block">

                    <td>${country.id}
                        <p><a href="${pageContext.request.contextPath}/country/edit/${country.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="yellow"
                                 class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1
                                         .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805
                                          2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z">
                                </path>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0
                                        1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0
                                        1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                            </svg>
                        </a></p>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/country/${country.id}"><h3 style="color: White">
                            ${country.name}</h3>
                    </a></td>
                    <td>${country.abbreviation.toUpperCase()}</td>
                    <td>
                        (${country.currency.name.toUpperCase()})
                    </td>

                    <td>
                        <form id="deleteCountryForm${country.id}" name="deleteCountryForm${country.id}"
                              action="${pageContext.request.contextPath}/country/delete"
                              method="post" enctype="text/plain">
                            <input id="url" hidden name="url"
                                   value="${pageContext.request.contextPath}/country/delete">
                            <input hidden type="text" name="id" value="${country.id}"/>

                            <div class="input-group">
                                <button id="deleteCountryButton${country.id}" form="deleteCountryForm${country.id}"
                                        class="btn btn-danger" type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                                         class="bi bi-globe"
                                         viewBox="0 0 16 16">
                                        <path d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm7.5-6.923c-.67.204-1.335.82-1.887
                                         1.855A7.97 7.97 0 00 5.145 4H7.5V1.077zM4.09 4a9.267 9.267 0 0 1 .64-1.539
                                          6.7 6.7 0 0 1 .597-.933A7.025 7.025 0 0 0
                  2.255 4H4.09zm-.582 3.5c.03-.877.138-1.718.312-2.5H1.674a6.958 6.958 0 0 0-.656 2.5h2.49zM4.847
                  5a12.5 12.5 0 0 0-.338 2.5H7.5V5H4.847zM8.5 5v2.5h2.99a12.495 12.495 0 0 0-.337-2.5H8.5zM4.51
                   8.5a12.5 12.5 0 0 0 .337 2.5H7.5V8.5H4.51zm3.99 0V11h2.653c.187-.765.306-1.608.338-2.5H8.5zM5.145
                    12c.138.386.295.744.468 1.068.552 1.035 1.218 1.65 1.887 1.855V12H5.145zm.182 2.472a6.696 6.696
                    0 0 1-.597-.933A9.268 9.268 0 0 1 4.09 12H2.255a7.024 7.024 0 0 0 3.072 2.472zM3.82 11a13.652 13
                    .652 0 0 1-.312-2.5h-2.49c.062.89.291 1.733.656 2.5H3.82zm6.853 3.472A7.024 7.024 0 0 0 13.745 1
                    2H11.91a9.27 9.27 0 0 1-.64 1.539 6.688 6.688 0 0 1-.597.933zM8.5 12v2.923c.67-.204 1.335-.82 1.
                    887-1.855.173-.324.33-.682.468-1.068H8.5zm3.68-1h2.146c.365-.767.594-1.61.656-2.5h-2.49a13.65 13
                    .65 0 0 1-.312 2.5zm2.802-3.5a6.959 6.959 0 0 0-.656-2.5H12.18c.174.782.282 1.623.312 2.5h2.49zM1
                    1.27 2.461c.247.464.462.98.64 1.539h1.835a7.024 7.024 0 0 0-3.072-2.472c.218.284.418.598.597.
                    933zM10.855 4a7.966 7.966 0 0 0-.468-1.068C9.835 1.897 9.17 1.282 8.5 1.077V4h2.355z"></path>
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-arrow-right" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"></path>
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                                         class="bi bi-trash-fill" viewBox="0 0 16 16">
                                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2
                                         2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1
                                          1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1
                                           .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3
                                            .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"></path>
                                    </svg>
                                    <spring:message code="delete.button"/>
                                </button>
                            </div>
                        </form>
                        <div id="alertMsg" class="error"></div>
                    </td>
                </tr>
                <script>
                    $(function () {
                        $('button#deleteCountryButton${country.id}[type=submit]').click(function (e) {
                            e.preventDefault();
                            var form = document.forms['deleteCountryForm${country.id}'];
                            var formData = new FormData(form);
                            // Ajax call for DELETE request
                            var ajaxReq = $.ajax({
                                url: document.getElementById('url').value,
                                type: 'DELETE',
                                data: formData,
                                cache: false,
                                contentType: false,
                                processData: false,
                                xhr: function () {
                                    //Get XmlHttpRequest object
                                    var xhr = $.ajaxSettings.xhr();
                                    //Set onprogress event handler
                                    return xhr;
                                },
                            });
                            // Called on success of file upload
                            ajaxReq.done(function (msg) {
                                $('#${country.id}block').hide(500);
                            });
                            // Called on failure of file upload
                            ajaxReq.fail(function (jqXHR) {
                                $('#${country.id}block').hide(500);
                            });
                        });
                    });
                </script>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="../import/footer.jsp"/>
</body>
</html>