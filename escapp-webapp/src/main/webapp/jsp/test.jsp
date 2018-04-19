
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

</head>

<body>

<h2>Test</h2>

<s:actionerror/>
<ul>
	<s:iterator value="listSite">
		<li><s:property value="id"/> - <s:property value="name"/> - <s:property value="description"/></li>
	</s:iterator>
</ul>
</body>
</html>