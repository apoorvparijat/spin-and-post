<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Struts 2 Application - Welcome</title>
<s:head />
</head>
<body>
<h1>Welcome To Struts 2!</h1>
<p>Hello count is : <s:property value="HelloWorldAction.helloCount" />
<p><a href="<s:url action='hello'/>">Hello World</a></p>
Using struts tag.
<s:url action="hello" var="helloLink">
	<s:param name="userName">Bruce Phillips</s:param>
</s:url>
<a href="${helloLink}">Constructed url </a>


<p> Get your own personal hello by filling the form </p>
<form method="post" action="hello">
	<input type="text" name="person.userName" value="blah" />	
	<input type="text" name="person.email" value="test@test.com" />
	<input type="submit" name="submit" value="Submit" />
<form>

</body>
</html>
