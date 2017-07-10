<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Spring Boot Web JSP Example</h1>
	<h2>Message: ${message}</h2>
	<h3>List of projects you are associated with</h3>
        <c:choose>
          <c:when test="${f:length(ojbects) gt 0}">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${ojbects}" var="ojbect">
                <tr>
                  <td>${ojbect.id}&nbsp;</td>
                  <td>${ojbect.status}&nbsp;</td>
                </tr>
              </c:forEach> 
              </tbody>
            </table>
          </c:when>
          <c:otherwise>
            <div >
              You don't have any projects registered.
            </div>
          </c:otherwise>
        </c:choose>
</body>
</html>