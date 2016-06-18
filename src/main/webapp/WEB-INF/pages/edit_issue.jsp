<%-- 
    Document   : create_issue
    Created on : Jun 16, 2013, 4:55:40 AM
    Author     : PetrK
--%>

<%@page import="ru.spb.petrk.webissues.issues.IssuesSearchForm"%>
<%@page import="ru.spb.petrk.webissues.utils.Constants"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelper"%>
<%@page import="ru.spb.petrk.webissues.utils.ContentHelperImpl" %>
<%@page import="ru.spb.petrk.webissues.utils.MessageCollector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% 
    ContentHelper contentHelper = new ContentHelperImpl();
    
    IssuesSearchForm searchForm = (IssuesSearchForm) request.getSession().getAttribute(Constants.BEAN_SEARCH_ISSUES_FORM);
%>

<c:set var="existingIssue" value="${issue != null && issue.id > 0}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${existingIssue ? 'Edit' : 'Create'} issue</title>        
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
                    <c:if test="${existingIssue}">
                        <a href="<c:url value = '/app/search' />?<%= searchForm.toRequestParams() %>">Список
                            заданий</a> >
                    </c:if>
                    <a href="<c:url value = '/app/editIssue' />?id=${issue != null ? issue.id : ''}">${existingIssue ? 'Редактировать' : 'Создать'} задание</a>
                </p>
            </div>            
            
            <form id="create_user_form" class="appnitro"  method="post" action="saveIssue">      
                <input type="hidden" name="id" value="${existingIssue ? issue.id : '-1'}"/>
                
                <div class="form_description">
                    <h2>${existingIssue ? 'Редактировать' : 'Создать'} задание</h2>
                    <p>
                        <c:if test="${messageCollector != null}">
                            <%= ((MessageCollector)request.getAttribute("messageCollector")).format() %>
                        </c:if>
                    </p>
                </div>	
                
                <ul >
                    <li id="li_1" >
                        <label class="description" for="login">Тема </label>
                        <div>
                            <input id="login" name="title" class="element text large" type="text" maxlength="255" value="${issue != null ? issue.title : ''}"/> 
                        </div> 
                    </li>
                    <li id="li_2" >
                        <label class="description" for="assignee">Исполнитель </label>
                        <div>
                            <select class="element select medium" id="assignee" name="assignee"> 
                                <option value="-1" ><spring:message code="not_assigned"/></option>
                                <c:forEach var="user" items="${users}" varStatus="rowCounter">
                                    <option value="${user.id}" ${issue != null && issue.assignee != null && issue.assignee.id == user.id ? 'selected' : ''} >
                                        ${user.firstName} ${user.lastName}
                                    </option>
                                </c:forEach>
                            </select>                            
                        </div> 
                    </li>		
                    <li id="li_3" >
                        <label class="description" for="priority">Приоритет </label>
                        <div>
                            <select class="element select medium" id="priority" name="priority"> 
                                <c:forEach var="priority" items="<%= contentHelper.getPriorities() %>" varStatus="rowCounter">
                                    <option value="${priority.id}" ${issue != null && issue.priority == priority ? 'selected' : ''} >
                                        <spring:message code="${priority.id}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div> 
                    </li>		
                    <li id="li_4" >
                        <label class="description" for="status">Статус </label>
                        <div>
                            <select class="element select medium" id="status" name="status"> 
                                <c:forEach var="status" items="<%= contentHelper.getStatuses() %>" varStatus="rowCounter">
                                    <option value="${status.id}" ${issue != null && issue.status == status ? 'selected' : ''} >
                                        <spring:message code="${status.id}"/>
                                    </option>
                                </c:forEach>
                            </select>                            
                        </div> 
                    </li>
                    <li id="li_5" >
                        <label class="description" for="description">Описание </label>
                        <div>
                            <textarea id="description" 
                                      name="description" 
                                      class="element text large"
                                      rows="5" cols="60" 
                                      type="text" >${issue != null ? issue.description : ''}</textarea>
                        </div>
                    </li>
                    <li id="li_6">
                        <label class="description" for="hashtag">Хэштег </label>
                        <div>
                            <input id="hashtag" name="hashtag" class="element text large" type="text" maxlength="255"
                                   value="${issue != null ? issue.hashtag : ''}"/>
                        </div>
                    </li>

                    <li class="buttons">
                        <input id="saveForm" class="button_text" type="submit" name="submit" value="Сохранить" />
                    </li>
                </ul>
            </form>	
        </div>
        <img id="bottom" src="<c:url value = '/static/resources/images/bottom.png' />" alt="">
    </body>    
</html>
