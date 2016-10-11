<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- //[START imports]--%>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ page import="ee.dbg4f.iot.hub.gae.TelemetryEntry" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Telemetry</title>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

    <h1>Telemetry</h1>


    <%
        List<TelemetryEntry> telemetryEntries = ObjectifyService.ofy()
                .load()
                .type(TelemetryEntry.class) // We want only Greetings
                .order("-date")       // Most recent first - date is indexed.
                .limit(50)             // Only show 5 of them.
                .list();

        if (telemetryEntries.isEmpty()) {
    %>
    <p>No messages.</p>
    <%
    } else {
    %>
    <p>Messages: </p>
    <%
        // Look at all of our telemetryEntries
        for (TelemetryEntry telemetryEntry : telemetryEntries) {
            pageContext.setAttribute("entry", telemetryEntry);
    %>
    <blockquote>${fn:escapeXml(entry)}</blockquote>
    <%
            }
        }
    %>


</body>
</html>
