<%-- 
    Document   : admin
    Created on : Jun 12, 2013, 10:48:09 PM
    Author     : PetrK
--%>

<%@page import="ru.spb.petrk.webissues.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        
        <link rel="stylesheet" type="text/css" href="<c:url value = '/static/resources/css/main.css' />" />        
        
        <script src="<c:url value = '/static/resources/scripts/jquery-2.0.2.min.js' />"></script>
                
        <script type="text/javascript" >
            function createUser() {
                window.location.href = "<c:url value = '/app/admin/createUser' />";
            }
            
            function blockUser() {
                var checkedRadio = $('input[type="radio"]:checked', '#users_form');
                
                if (checkedRadio !== null && checkedRadio.length === 1) {
                    var login = checkedRadio.val();
                    var base = "<c:url value = '/app/admin/blockUser' />";
                    window.location.href = base + "?" + "login=" + login;
                }                
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
                    <a href="<c:url value = '/app/' />">Главная</a> > 
                    <a href="<c:url value = '/app/admin/' />">Панель администратора</a>
                </p>
            </div>            
                        
            <form id="users_form" class="appnitro"  method="post" action="">                
                <div class="form_description">
                    <h2>Список пользователей</h2>
                    <p></p>
                </div>
                
                <table width='100%' border='2px'>
                    <thead>
                        <tr>
                            <th style="width: 20px"></th>
                            <th>Логин</th>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Роли</th>
                        </tr>
                    </thead>

                    <c:forEach var="user" items="${users}" varStatus="rowCounter">
                        <tr <c:if test="${user.blocked}">style='background: #cd5c5c;'</c:if> >
                            <td><input type="radio" name="user_${rowCounter}" value="${user.login}" style='width: 20px'/></td>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>
                                <c:forEach var="role" items="${user.roles}">
                                    <spring:message code="${role.id}"/><br>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
                <div class="buttons">
                    <input type="button" value="Создать" onclick="createUser();"/>     
                    <input type="button" value="Блокировать" onclick="blockUser();"/>                    
                </div>                
            </form>	
        </div>
        <img id="bottom" src="<c:url value = '/static/resources/images/bottom.png' />" alt="">
    </body>  
</html>
