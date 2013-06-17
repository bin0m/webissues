<%-- 
    Document   : create_user
    Created on : Jun 13, 2013, 3:16:47 AM
    Author     : PetrK
--%>

<%@page import="ru.spb.petrk.webissues.utils.MessageCollector"%>
<%@page import="ru.spb.petrk.webissues.security.Role"%>
<%@page import="ru.spb.petrk.webissues.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% 
    User user = (User)request.getAttribute("user");    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create user</title>        
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
                    <a href="<c:url value = '/app/admin/' />">Панель администратора</a> >
                    <a href="<c:url value = '/app/admin/createUser' />">Новый пользователь</a>
                </p>
            </div>            
            
            <form id="create_user_form" class="appnitro"  method="post" action="saveUser">                
                <div class="form_description">
                    <h2>Новый пользователь</h2>
                    <p>
                        <c:if test="${messageCollector != null}">
                            <%= ((MessageCollector)request.getAttribute("messageCollector")).format() %>
                        </c:if>
                    </p>
                    <!--<p>Заполните все поля, чтобы создать пользователя</p>-->
                </div>						
                <ul >

                    <li id="li_1" >
                        <label class="description" for="login">Логин </label>
                        <div>
                            <input id="login" name="login" class="element text medium" type="text" maxlength="255" value="${user != null ? user.login : ''}"/> 
                        </div> 
                    </li>		<li id="li_2" >
                        <label class="description" for="firstName">Имя </label>
                        <div>
                            <input id="firstName" name="firstName" class="element text medium" type="text" maxlength="255" value="${user != null ? user.firstName : ''}"/> 
                        </div> 
                    </li>		<li id="li_3" >
                        <label class="description" for="lastName">Фамилия </label>
                        <div>
                            <input id="lastName" name="lastName" class="element text medium" type="text" maxlength="255" value="${user != null ? user.lastName : ''}"/> 
                        </div> 
                    </li>		<li id="li_4" >
                        <label class="description" for="email">Почта </label>
                        <div>
                            <input id="email" name="email" class="element text medium" type="text" maxlength="255" value="${user != null ? user.email : ''}"/> 
                        </div> 
                    </li>		<li id="li_5" >
                        <label class="description" for="password">Пароль </label>
                        <div>
                            <input id="password" name="password" class="element text medium" type="password" maxlength="255" value=""/> 
                        </div> 
                    </li>
                    
                    <li id="li_6" >
                        <label class="description" for="element_6">Роль </label>
                        <span>                            
                            <input id="element_6_1" name="roleAdmin" class="element radio" type="checkbox" <%= user != null && user.hasRole(Role.Admin) ? "checked" : "" %> />
                            <label class="choice" for="element_6_1"><spring:message code="<%= Role.Admin.getId() %>"/><br></label>
                            <input id="element_6_2" name="roleUser" class="element radio" type="checkbox" <%= user != null && user.hasRole(Role.User) ? "checked" : "" %> />
                            <label class="choice" for="element_6_2"><spring:message code="<%= Role.User.getId() %>"/></label>
                            <input id="element_6_3" name="roleObserver" class="element radio" type="checkbox" <%= user != null && user.hasRole(Role.Observer) ? "checked" : "" %> />
                            <label class="choice" for="element_6_3"><spring:message code="<%= Role.Observer.getId() %>"/></label>                            
                        </span> 
                    </li>                

                    <li class="buttons">
                        <input type="hidden" name="form_id" value="649749" />

                        <input id="saveForm" class="button_text" type="submit" name="submit" value="Сохранить" />
                    </li>
                </ul>
            </form>	
        </div>
        <img id="bottom" src="<c:url value = '/static/resources/images/bottom.png' />" alt="">
    </body>    
</html>
