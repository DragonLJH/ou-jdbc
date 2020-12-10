<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*,jg.ou.commom.Student"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%List<Student> stuList =(List<Student>)request.getAttribute("stuList"); %>
	<% for(int i = 0;i < stuList.size() ; i++){ 
		Student stu = stuList.get(i);
	%>
	<div
		style="float:left; width: 200px; height: 200px; border: solid 1px #aaa; box-shadow: #333 10px 10px 10px; text-align: center;">
		<p><%=stu.getStu_id()%></p>
		<p><%=stu.getStu_name()%></p>
		<p><%=stu.getStu_sex() == 1 ? "男" :"女"%></p>
		<p><%=stu.getStu_age()%></p>
		<p><%=stu.getStu_major()%></p>
	</div>
	<%} %>

</body>
</html>