<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="jg.ou.commom.*,java.util.List"%>
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

.l-table>div>span {
	padding: 10px 10px 2px 10px;
	border-bottom: #ccc solid 1px;
	display: inline-block;
	width: 100px;
	text-align: center;
}
</style>
<body>
<body>
	<div class="l-table">
		<div>
			<span>学号</span> <span>姓名</span> <span>性别</span> <span>年龄</span> <span>专业</span><span>操作</span>
		</div>
		<%
			Student student = (Student) request.getAttribute("aStudent");
		%>
		<div>
			<span><%=student.getStu_id()%></span> <span><%=student.getStu_name()%></span>
			<span><%=student.getStu_sex()%></span> <span><%=student.getStu_age()%></span>
			<span><%=student.getStu_major()%></span><span><button onclick="openTC()">修改</button></span>
		</div>


	</div>
	<div class="l-table">
		<div>
			<span>课程</span><span>成绩</span>
		</div>
		<%
			List<Achievement> achieveList = (List<Achievement>) request.getAttribute("achieveList");

		for (Achievement achieve : achieveList) {
		%>

		<div>
			<span><%=achieve.getCourse_id()%></span><span><%=achieve.getResult()%></span>
		</div>
		<%
			}
		%>
	</div>

	<div class="l-table">
		<div>
			<span>课程序号</span><span>课程名称</span><span>系别</span><span>授课老师</span><span>操作</span>
		</div>
		<%
			List<Course> courseList = (List<Course>) request.getAttribute("courseList");

		for (Course course : courseList) {
		%>

		<div>
			<span><%=course.getCourse_id()%></span><span><%=course.getCourse_name()%></span>
			<span><%=course.getCourse_dept()%></span><span><%=course.getCourse_teacher()%></span>
			<span><button onclick="create(<%=course.getCourse_id()%>)">选课</button></span>
		</div>
		<%
			}
		%>
		<script type="text/javascript">
		function create(course_id){
			//创建XMLHttpRequest对象
			var xhr = new XMLHttpRequest();
			//true表示异步,false表示同步
			xhr.open('POST', "achievement", true);
			xhr.onreadystatechange = function() {
				// readyState == 4说明请求已完成
				if (xhr.readyState == 4 && xhr.status == 200
						|| xhr.status == 304) {
					//responseText：从服务器获得数据
					data = xhr.responseText.trim();
					console.log(data)
					if(data == "1"){
						alert("选课成功，刷新页面数据更新")
						
					}else{
						alert("选课失败，已选择该课程")
					}
					
					console.log("data",typeof data)
				}
			};
			xhr.setRequestHeader('content-type',
					'application/x-www-form-urlencoded; charset-UTF-8')
			xhr.send("course_id=" + course_id);

			}

		</script>
	</div>

</body>


</html>