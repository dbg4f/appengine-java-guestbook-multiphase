<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- //[START imports]--%>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ page import="ee.dbg4f.iot.hub.gae.TelemetryEntry" %>
<%@ page import="ee.dbg4f.iot.hub.gae.Config" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Config</title>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

    <h1>Config</h1>



    <%
        List<Config> config1 = ObjectifyService.ofy()
                .load()
                .type(Config.class)
                .list();

        pageContext.setAttribute("config", config1);

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			pageContext.setAttribute("config", config1);
		}
		else 
		{
			pageContext.setAttribute("config", "Not authorized");
		}


        %>
            <blockquote>${fn:escapeXml(config)}</blockquote>

    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>


<form action="/config" method="post">
    <div><input type="text" name="configKey" /></div>
    <div><input type="text" name="configValue" /></div>
    <div><input type="submit" value="Confirm"/></div>
</form>


</body>
</html>
