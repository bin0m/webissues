<%-- 
    Document   : issues
    Created on : Jun 16, 2013, 3:54:23 AM
    Author     : PetrK
--%>

<%@page import="ru.spb.petrk.webissues.issues.IssuesSearchForm"%>
<%@page import="java.util.Map"%>
<%@page import="ru.spb.petrk.webissues.utils.Constants"%>
<%@page import="ru.spb.petrk.webissues.model.Issue"%>
<%@page import="java.util.List"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelperImpl"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<% 
    ContentHelper contentHelper = new ContentHelperImpl();
    
    IssuesSearchForm searchForm = (IssuesSearchForm) request.getSession().getAttribute(Constants.BEAN_SEARCH_ISSUES_FORM);
    
    List<Issue> issues = (List<Issue>) request.getAttribute(Constants.ISSUES);
    int issuesCounter = 0;
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Start page</title>
        
        <link rel="stylesheet" type="text/css" href="<c:url value = '/static/resources/css/main.css' />" />        
        
        <script src="<c:url value = '/static/resources/scripts/jquery-2.0.2.min.js' />"></script>
        
        <script type="text/javascript" >
            function processIssue(urlAction) {
                var checkedRadio = $('input[type="radio"]:checked', '#issues_form');
                
                if (checkedRadio !== null && checkedRadio.length === 1) {
                    var id= checkedRadio.val();
                    var base = "<c:url value = '/app/' />" + urlAction;
                    window.location.href = base + "?" + "id=" + id;
                }            
            }            
            
            function showIssue() {
                processIssue('showIssue');
            };         
            
            function editIssue() {
                processIssue('editIssue');
            };               
        </script>        
    </head>
                                    
    <body id="main_body" >
        
        <%@include file="header.jsp"%>

        <img id="top" src="<c:url value = '/static/resources/images/top.png' />" alt="">
        <div id="form_container">

            <h1><a></a></h1>
            
            <div style="margin: 10px 20px 0px; padding: 0px 0px 0px;">
                <p>
                    <a href="<c:url value = '/app/' />">Главная</a> >
                    <a href="<c:url value = '/app/search' />?<%= searchForm.toRequestParams() %>">Список заданий</a>
                </p>
            </div>            
                        
            <form id="issues_form" class="appnitro"  method="post" action="">
                
                <div class="form_description">
                    <h2>Список заданий</h2>
                    <p></p>
                </div>
                
                <table width='100%' border='2px'>
                    <thead>
                        <tr>
                            <th style="width: 20px"></th>
                            <th style="width: 50%">Тема</th>
                            <th>Приоритет</th>
                            <th>Статус</th>
                            <th>Дата создания</th>
                        </tr>
                    </thead>

                    <c:forEach var="issue" items="${issues}" varStatus="rowCounter">
                        <tr>
                            <td><input type="radio" name="issue_${rowCounter}" value="${issue.id}" style='width: 20px'/></td>
                            <td>${issue.title}</td>
                            <td><spring:message code="${issue.priority.id}"/></td>
                            <td><spring:message code="${issue.status.id}"/></td>
                            <td align="center" ><%= contentHelper.format(issues.get(issuesCounter).getCreationDate(), null) %></td>
                            <% issuesCounter++; %>
                        </tr>
                    </c:forEach>
                </table>  
                
                <li class="buttons">
                    <input class="button_text" type="button" value="Просмотр" onclick="showIssue();" />
                    <input class="button_text" type="button" value="Редактирование" onclick="editIssue();" />
                </li>                
            </form>                        
        </div>
        <img id="bottom" src="<c:url value = '/static/resources/images/bottom.png' />" alt="">
    </body>  
</html>
