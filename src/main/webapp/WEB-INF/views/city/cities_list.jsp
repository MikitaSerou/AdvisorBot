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
<jsp:include page="../import/navigation_bar.jsp"/>
<jsp:include page="../import/up_button.jsp"/>

<div class="container">
    <div class="row">
        <h1><spring:message code="all.cities"/></h1>
        <table  class="table table-hover table-dark"
               border="1">
            <thead>
            <th><spring:message code="id"/></th>
            <th><spring:message code="name"/></th>
            <th><spring:message code="country"/></th>
            <th><spring:message code="capital"/></th>
            <th><spring:message code="description"/></th>
            <th><spring:message code="action"/></th>
            </thead>
            <c:forEach items="${cityList}" var="city">
                <tr id="${city.id}block">
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
                        <form id="deleteCityForm${city.id}" name="deleteCityForm${city.id}"
                              action="${pageContext.request.contextPath}/city/delete"
                              method="post" enctype="text/plain">
                            <input id="url" hidden name="url"
                                   value="${pageContext.request.contextPath}/city/delete">
                            <input hidden type="text" name="id" value="${city.id}"/>

                            <div class="input-group">
                                <button id="deleteCityButton${city.id}" form="deleteCityForm${city.id}"
                                        class="btn btn-danger" type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                                         class="bi bi-trash-fill" viewBox="0 0 16 16">
                                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2
                                         2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1
                                          1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1
                                           .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3
                                            .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"></path>
                                    </svg>
                                    <spring:message code="delete"/>
                                </button>
                            </div>
                        </form>
                        <div id="alertMsg" class="error"></div>
                        <script type="text/javascript">
                            $(document).ready(function () {
                                $("#inputProductName").keyup(function () {
                                    var name = $(this).val();
                                    $("#productNameToFile").val(name);
                                }).keyup();
                            });
                        </script>
                    </td>
                </tr>  <script>
                $(function() {
                    $('button#deleteCityButton${city.id}[type=submit]').click(function(e) {
                        e.preventDefault();
                        var form = document.forms['deleteCityForm${city.id}'];
                        var formData = new FormData(form);
                        // Ajax call for file DELETE
                        var ajaxReq = $.ajax({
                            url : document.getElementById('url').value,
                            type : 'DELETE',
                            data : formData,
                            cache : false,
                            contentType : false,
                            processData : false,
                            xhr: function(){
                                //Get XmlHttpRequest object
                                var xhr = $.ajaxSettings.xhr() ;
                                //Set onprogress event handler
                                return xhr ;
                            },
                        });
                        // Called on success of file upload
                        ajaxReq.done(function(msg) {
                            $('#${city.id}block').hide(1000);
                        });
                        // Called on failure of file upload
                        ajaxReq.fail(function(jqXHR) {
                            $('#${city.id}block').hide(1000);
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