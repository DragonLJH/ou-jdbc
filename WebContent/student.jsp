<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="jg.ou.commom.*,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.l-table {
	border: solid 1px #eee;
	display: inline-block;
	padding: 5px 20px;
	overflow: hidden;
}

.l-table> div > span {
	padding: 10px 10px 2px 10px;
	border-bottom: #ccc solid 1px;
	display: inline-block;
	width:100px;
	text-align: center;
}
</style>
<body>
<body>
	<div class="l-table">
		<div>
			<span>学号</span> <span>姓名</span> <span>性别</span> <span>年龄</span> <span>专业</span>
		</div>
		<%
			Student student = (Student) request.getAttribute("aStudent");
		%>
		<div>
		<span><%=student.getStu_id()%></span>
		<span><%=student.getStu_name()%></span>
		<span><%=student.getStu_sex()%></span>
		<span><%=student.getStu_age()%></span>
		<span><%=student.getStu_major()%></span>
		</div>


	</div>
	<div class="l-table">
		<div>
			<span>院系</span> <span>课室</span> <span>课程</span> <span>老师</span> <span>成绩</span>
		</div>
	</div>

</body>


</html>