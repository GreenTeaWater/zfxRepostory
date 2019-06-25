<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:message code="home.page" /></title>
  </head>
  <body>
 	 <%-- 
 	 	*** commandName="user" 属性已经废弃，现在用 modelAttribute="user"
 	 	而且必须有
 	 	Controller响应这个页面后必须要送user这个对象
 	  --%>
 	
  	<sf:form action="login" method="post" modelAttribute="user" >
  		<sf:errors path="*" element="div" />	
  		姓名：<sf:input path="name" /><sf:errors path="name" /><br />
  		密码：<sf:input path="password" /><sf:errors path="password" /><br />
  		<input type="submit" value="登录">
  	</sf:form>
  </body>
</html>
