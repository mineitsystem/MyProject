<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetailsService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<style>
table{
  width:800px;
}
table, th, td
{
  border-collapse:collapse;
  border:1px solid gray;
}
</style>
<script type="text/javascript">
$(document).ready(function () {
   
});
</script>   
</head>
<body>
<div class="col-lg-6">
    <div class="panel panel-red">
        <div class="panel-heading">
            Auth Error
        </div>
        <div class="panel-body">
	              접근권한이 없습니다.<br> 담당자에게 문의하여 주시기 바랍니다. <br>
		    ${SPRING_SECURITY_403_EXCEPTION}             
		    <br>
		    <%
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Object principal = auth.getPrincipal();
		    if (principal instanceof UserDetails) {
		      String username = ((UserDetails) principal).getUsername();
		      String password = ((UserDetails) principal).getPassword();
		      out.println("Account : " + username.toString() + "<br>");
		    }
		    %>
	    
	    </div>
        </div>
        <div class="panel-footer">  
        	<a class="btn btn-primary btn-lg btn-block" href="<c:url value='/'/>">확인</a>          
        </div>
    </div>
    <!-- /.col-lg-4 -->
</body>
</html>