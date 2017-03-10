<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Person Page</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }

    </style>
</head>
<body>
<h1>
    <a href="<c:url value='/persons/1/nosearch' />">Home</a>
</h1>
<table class="tgall">



    <tr>
        <td >


            <c:url var="addAction" value="/person/add/1/${search}"></c:url>

            <form:form action="${addAction}" commandName="person">
                <table>
                        <%--<c:if test="${!empty person.name}">--%>

                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                        <%--</c:if>--%>

                    <tr>
                        <td>
                            <form:label path="name">
                                <spring:message text="Name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name" value="${person.name}"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:label path="age">
                                <spring:message text="age"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="number" path="age"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:label path="admin">
                                <spring:message text="admin"/>
                            </form:label>
                        </td>
                        <td>
                            <form:checkbox path="admin" value="true"/>Java
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit"
                                   value="<spring:message text="Add/Edit"/>"/>
                        </td>


                    </tr>
                </table>
            </form:form>
        </td>

        <td>


            <c:url var="addAction1" value="/person/search/1/${search}"></c:url>

            <form:form action="${addAction1}" commandName="person">
                <table>
                    <tr>
                        <td>
                            <form:label path="name">
                                <spring:message text="Contains: "/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="name" value="${search}"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                                <%--<c:if test="${empty person.name}">--%>
                            <input type="submit"
                                   value="<spring:message text="Search"/>"/>
                        </td>
                    </tr>
                </table>
            </form:form>


            <a href="<c:url value='/create10random' />">create10random</a>
            <a href="<c:url value='/deleteAll' />">deleteAll</a>

        </td>
    </tr>
</table>
<h3>Persons List</h3>
<c:if test="${!empty listPersons}">
    <table class="tg">


        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Age</th>

            <th width="120">Admin</th>
            <th width="120">Date</th>

            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
            <%--<tr>--%>
            <%--<th width="80">ID</th>--%>
            <%--<form:input path="nameS"/>--%>
            <%--<form:input path="ageS"/>--%>
            <%--<form:checkbox path="adminS" value="true"/>Java--%>
            <%--<th width="120">Date</th>--%>

            <%--<th width="60">Edit</th>--%>
            <%--<th width="60">Delete</th>--%>
            <%--</tr>--%>
        <c:forEach items="${listPersons}" var="person">

            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
                <td>${person.admin}</td>
                <td>${person.date}</td>
                <td><a href="<c:url value='/edit/${person.id}/${search}' />">Edit</a></td>
                <td><a href="<c:url value='/remove/${person.id}/${search}' />">Delete</a></td>
            </tr>


        </c:forEach>

    </table>

    <c:forEach items="${pageid}" var="pageids">

        <a href="<c:url value='/persons/${pageids}/${search}' />">${pageids}</a>

    </c:forEach>

</c:if>


</body>
</html>
