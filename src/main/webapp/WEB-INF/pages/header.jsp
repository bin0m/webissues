<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="headerCont">
   <nav>
       <ul>
           <sec:authorize access="hasRole('ROLE_Admin')">
               <li class="arial11Grey">
                   <a href="<c:url value = '/app/admin/' />">Панель администратора</a>
               </li>
               <li class="arial11Grey spacerHeaderNav">
                   |
               </li>
          </sec:authorize>
           <li class="arial11Grey">
               <a href="<c:url value='/j_spring_security_logout' />">Выход</a>
           </li>                    
       </ul>
   </nav>
</div>