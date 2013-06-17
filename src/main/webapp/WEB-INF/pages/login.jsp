<%-- 
    Document   : login
    Created on : Jun 9, 2013, 10:15:53 PM
    Author     : PetrK
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Login Page</title>
        <style>
        .errorblock {
                color: #ff0000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
        }
        </style>
        
        <link rel="stylesheet" type="text/css" href="<c:url value = '/static/resources/css/login_form.css' />" />        
    </head>
    
    <body onload='document.f.j_username.focus();'>

    <c:if test="${not empty error}">
        <div class="errorblock">
            Your login attempt was not successful, try again.<br /> Caused :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
        
    <div id="stylized" class="login_form">
         <form id="form" name="form" method="POST" action="<c:url value='j_spring_security_check' />">
            <label>Имя
             </label>
            <input type="text" name="j_username" id="name" />

             <label>Пароль
             </label>
            <input type="password" name="j_password" id="password" />

            <button type="submit">Вход</button>
            <div class="spacer"></div>
        </form>
    </div>
</body>
</html>