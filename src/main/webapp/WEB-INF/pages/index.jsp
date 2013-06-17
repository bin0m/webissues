<%-- 
    Document   : index
    Created on : Jun 15, 2013, 4:51:58 PM
    Author     : PetrK
--%>

<%@page import="ru.spb.petrk.webissues.issues.IssuesSearchForm"%>
<%@page import="ru.spb.petrk.webissues.utils.Constants"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelperImpl"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<% 
    ContentHelper contentHelper = new ContentHelperImpl();
    
//    IssuesSearchForm searchForm = (IssuesSearchForm) request.getSession().getAttribute(Constants.BEAN_SEARCH_ISSUES_FORM);
%>

<c:set var="searchForm" value="<%= (IssuesSearchForm) request.getSession().getAttribute(Constants.BEAN_SEARCH_ISSUES_FORM) %>" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Start page</title>        
        <link rel="stylesheet" type="text/css" href="<c:url value = '/static/resources/css/main.css' />" />                
        
        <script src="<c:url value = '/static/resources/scripts/jquery-2.0.2.min.js' />"></script>
        
        <script type="text/javascript" >
            function createIssue() {
                window.location.href = "<c:url value = '/app/createIssue' />";
            }
            
            function showMyIssues() {
                window.location.href = "<c:url value = '/app/searchForVisitor' />";
            }            
        </script>        
    </head>
                                    
    <body id="main_body" >
        
        <%@include file="header.jsp"%>

        <img id="top" src="<c:url value = '/static/resources/images/top.png' />" alt="">
        <div id="form_container">

            <h1><a></a></h1>
            
            <div style="margin: 10px 20px 0px; padding: 0px 0px 0px;">
                <p>
                    <a href="<c:url value = '/app/' />">Главная</a>
                </p>
            </div>            
                
            <form id="issues_form" class="appnitro"  method="get" action="">
                <div class="form_description">
                    <h2>Задания</h2>
                </div>
                
                <ul>                    
                    <li class="buttons">
                        <input id="createTask" class="button_text" type="button" value="Создать задание" onclick="createIssue();" />
                        <input id="searchTasks" class="button_text" type="button" value="Мои задания" onclick="showMyIssues();" />
                    </li>
                </ul>   
            </form>                
                        
                    <form id="search_form" class="appnitro"  method="get" action="<c:url value = '/app/search' /> ">
                <div class="form_description">
                    <h2>Поиск заданий</h2>
                </div>
                
                <ul >             
                    <li id="li_1" >
                        <label class="description" for="title">Тема </label>
                        <div>
                            <input id="title" name="title" class="element text large" type="text" maxlength="255" value="${searchForm != null ? searchForm.title : ''}"/> 
                        </div> 
                    </li>                    
                    <li id="li_2" >
                        <label class="description" for="creator">Создатель </label>
                        <div>
                            <select class="element select medium" id="creator" name="creator"> 
                                <option value="-1" ></option>
                                <c:forEach var="user" items="${users}" varStatus="rowCounter">
                                    <option value="${user.id}" ${searchForm != null && searchForm.creator == user.id ? 'selected' : ''} >
                                        ${user.firstName} ${user.lastName}
                                    </option>
                                </c:forEach>
                            </select>                             
                        </div> 
                    </li>		
                    <li id="li_3" >
                        <label class="description" for="assignee">Исполнитель </label>
                        <div>
                            <select class="element select medium" id="assignee" name="assignee"> 
                                <option value="-1" ></option>
                                <c:forEach var="user" items="${users}" varStatus="rowCounter">
                                    <option value="${user.id}" ${searchForm != null && searchForm.assignee == user.id ? 'selected' : ''} >
                                        ${user.firstName} ${user.lastName}
                                    </option>
                                </c:forEach>
                            </select>   
                        </div> 
                    </li>		
                    <li id="li_4" >
                        <label class="description" for="priority">Приоритет </label>
                        <div>
                            <select class="element select medium" id="priority" name="priority"> 
                                <option value="" ></option>
                                <c:forEach var="priority" items="<%= contentHelper.getPriorities() %>" varStatus="rowCounter">
                                    <option value="${priority.id}" ${searchForm != null && searchForm.priority == priority.id ? 'selected' : ''} >
                                        <spring:message code="${priority.id}"/>
                                    </option>
                                </c:forEach>
                            </select>  
                        </div> 
                    </li>
                    <li id="li_5" >
                        <label class="description" for="status">Статус </label>
                        <div>
                            <select class="element select medium" id="status" name="status"> 
                                <option value="" ></option>
                                <c:forEach var="status" items="<%= contentHelper.getStatuses() %>" varStatus="rowCounter">
                                    <option value="${status.id}" ${searchForm != null && searchForm.status == status.id ? 'selected' : ''} >
                                        <spring:message code="${status.id}"/>
                                    </option>
                                </c:forEach>
                            </select>    
                        </div> 
                    </li>

                    <li class="buttons">
                        <input id="searchTasks" class="button_text" type="submit" name="submit" value="Искать" />
                    </li>
                </ul>   
            </form>                        
        </div>
        <img id="bottom" src="<c:url value = '/static/resources/images/bottom.png' />" alt="">
    </body>  
</html>
