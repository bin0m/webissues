<%-- 
    Document   : show_issue
    Created on : Jun 16, 2013, 4:55:40 AM
    Author     : PetrK
--%>

<%@page import="ru.spb.petrk.webissues.model.Issue"%>
<%@page import="ru.spb.petrk.webissues.issues.IssuesSearchForm"%>
<%@page import="ru.spb.petrk.webissues.utils.Constants"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelperImpl"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelper"%>
<%@page import="ru.spb.petrk.webissues.utils.MessageCollector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% 
    IssuesSearchForm searchForm = (IssuesSearchForm) request.getSession().getAttribute(Constants.BEAN_SEARCH_ISSUES_FORM);
    
    ContentHelper contentHelper = new ContentHelperImpl();
    
    Issue issue = (Issue) request.getAttribute(Constants.ISSUE);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View issue</title>        
        <link rel="stylesheet" type="text/css" href="<c:url value = '/static/resources/css/main.css' />" />        
    </head>
    
    <body id="main_body" >
        
        <%@include file="header.jsp"%>

        <img id="top" src="<c:url value = '/static/resources/images/top.png' />" alt="">
        <div id="form_container">

            <h1><a></a></h1>
            
            <div style="margin: 10px 20px 0px; padding: 0px 0px 0px;">
                <p>
                    <a href="<c:url value = '/app/' />">Главная</a> > 
                    <c:if test="<%= searchForm != null %>">
                        <a href="<c:url value = '/app/search' />?<%= searchForm.toRequestParams() %>">Список заданий</a> >                     
                    </c:if>
                    <a href="<c:url value = '/app/showIssue' />?id=${issue != null ? issue.id : ''}">Просмотр задания</a>
                </p>
            </div>            
            
            <form id="create_user_form" class="appnitro"  method="post" action="saveIssue">      
                <div class="form_description">
                    <h2>Просмотр задания</h2>
                    <p>
                        <c:if test="${messageCollector != null}">
                            <%= ((MessageCollector)request.getAttribute("messageCollector")).format() %>
                        </c:if>
                    </p>
                </div>	
                
                <ul >
                    <li id="li_1" >
                        <label class="description" for="title">Тема </label>
                        <div>
                            <input id="login" name="title" class="element text large" type="text" maxlength="255" value="${issue != null ? issue.title : ''}" disabled /> 
                        </div> 
                    </li>
                    <li id="li_2" >
                        <label class="description" for="assignee">Исполнитель </label>
                        <div>
                            <c:if test="${issue != null && issue.assignee != null}">
                                <input id="assignee" name="assignee" class="element text large" type="text" maxlength="255" value="${issue.assignee.fio}" disabled /> 
                            </c:if>
                            <c:if test="${issue != null && issue.assignee == null}">
                                <input id="assignee" name="assignee" class="element text large" type="text" maxlength="255" value="<spring:message code="not_assigned"/>" disabled /> 
                            </c:if>
                        </div> 
                    </li>		
                    <li id="li_3" >
                        <label class="description" for="priority">Приоритет </label>
                        <div>
                            <c:if test="${issue != null}">
                                <input id="priority" name="priority" class="element text large" type="text" maxlength="255" value="<spring:message code="${issue.priority}"/>" disabled /> 
                            </c:if>
                        </div> 
                    </li>		
                    <li id="li_4" >
                        <label class="description" for="status">Статус </label>
                        <div>
                            <c:if test="${issue != null}">
                                <input id="status" name="status" class="element text large" type="text" maxlength="255" value="<spring:message code="${issue.status}"/>" disabled /> 
                            </c:if>
                        </div> 
                    </li>                    
                    <li id="li_5" >
                        <label class="description" for="creator">Создатель </label>
                        <div>
                            <c:if test="${issue != null}">
                                <input id="creator" name="creator" class="element text large" type="text" maxlength="255" value="${issue.creator.fio}" disabled /> 
                            </c:if>
                        </div> 
                    </li>
                    <li id="li_6" >
                        <label class="description" for="creationDate">Дата создания </label>
                        <div>
                            <c:if test="${issue != null}">
                                <input id="creationDate" name="creationDate" class="element text large" type="text" maxlength="255" value="<%= contentHelper.format(issue.getCreationDate(), null) %>" disabled /> 
                            </c:if>
                        </div> 
                    </li>                    
                    <li id="li_7" >
                        <label class="description" for="description">Описание </label>
                        <div>
                            <textarea id="description" 
                                      name="description" 
                                      class="element text large"
                                      rows="5" cols="60" 
                                      type="text" disabled >${issue != null ? issue.description : ''}</textarea>
                        </div> 
                    </li>
                </ul>
            </form>	
        </div>
        <img id="bottom" src="<c:url value = '/static/resources/images/bottom.png' />" alt="">
    </body>    
</html>
