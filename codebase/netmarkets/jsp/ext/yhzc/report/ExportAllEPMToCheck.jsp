<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="ext.yhzc.report.ExportSystemObject"%>
<%@ include file="/netmarkets/jsp/util/beginPopup.jspf"%>
<%
String path = ExportSystemObject.exportAllEPMToCheck();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ExportAllEPMToCheck</title>
    
	

  </head>
  
  <body>
  	<center><a href="temp/<%=path%>">导出系统中所有EPM信息</a></center>
    <br>
  </body>
</html>
<%@ include file="/netmarkets/jsp/util/end.jspf"%>