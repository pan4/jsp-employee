<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@page import="org.panch.entity.Employee"%>
 <%@page import="org.panch.entity.Department"%>
 <%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees</title>
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Dep Name</th>
        </tr>
        <c:forEach var="department" items="${departmentList}">
            <tr>
                <td><a href="employee.do?page=${currentPage}&recordsPerPage=${recordsPerPage}&depid=${department.id}">${department.name}</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <td><a href="employee.do?page=${currentPage}&recordsPerPage=${recordsPerPage}&depid=${depid}&sort=empid">Emp ID</a></td>
            <td><a href="employee.do?page=${currentPage}&recordsPerPage=${recordsPerPage}&depid=${depid}&sort=empname">Emp Name</a></td>
            <td><a href="employee.do?page=${currentPage}&recordsPerPage=${recordsPerPage}&depid=${depid}&sort=empsalary">Salary</a></td>
            <th>Action</th>
        </tr>
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.employeeName}</td>
                <td>${employee.salary}</td>
                <td><a href="employee.do?page=${currentPage}&recordsPerPage=${recordsPerPage}&depid=${depid}&empid=${employee.employeeId}">Remove</a></td>
            </tr>
        </c:forEach>
    </table>

    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="employee.do?page=${currentPage - 1}&recordsPerPage=${recordsPerPage}&depid=${depid}">Previous</a></td>
    </c:if>

    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="employee.do?page=${i}&recordsPerPage=${recordsPerPage}&depid=${depid}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="employee.do?page=${currentPage + 1}&recordsPerPage=${recordsPerPage}&depid=${depid}">Next</a></td>
    </c:if>

</body>
</html>

