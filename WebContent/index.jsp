<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*,jg.ou.commom.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main" style="overflow: hidden;">
		<div style="float: left; margin: 10px 50px;">
			<h1>学生表按需查询</h1>
			<p>按需查询，全空为查询全部</p>
			<form method="get" action="student">
				<div>
					<label>学号：</label> <input type="text" name="stu_id"
						placeholder="请输入">
				</div>
				<div>
					<label>姓名：</label> <input type="text" name="stu_name"
						placeholder="请输入">
				</div>
				<div>
					<label>性别：</label> <input type="radio" name="stu_sex" value="1">
					男 <input type="radio" name="stu_sex" value="2"> 女
				</div>
				<div>
					<label>年龄：</label> <input type="text" name="stu_age"
						placeholder="请输入">
				</div>
				<div>
					<label>专业：</label> <input type="text" name="stu_major"
						placeholder="请输入">
				</div>
				<div>
					<input type="submit" name="submit" />
				</div>
			</form>
		</div>

	</div>

	<div class="res" style="margin: 10px; padding: 10px;">
		<%
			List<Student> stuList = new ArrayList<Student>();

		stuList = (List<Student>) request.getAttribute("stuList") != null ? (List<Student>) request.getAttribute("stuList")
				: new ArrayList<Student>();
		%>
		<%
			if (stuList.size() == 0) {

		} else {
			for (int i = 0; i < stuList.size(); i++) {
				Student stu = stuList.get(i);
		%>
		<div
			style="float: left; width: 200px; height: 200px; border: solid 1px #aaa; box-shadow: #333 10px 10px 10px; text-align: center;">
			<p><%=stu.getStu_id()%></p>
			<p><%=stu.getStu_name()%></p>
			<p><%=stu.getStu_sex() == 1 ? "男" : "女"%></p>
			<p><%=stu.getStu_age()%></p>
			<p><%=stu.getStu_major()%></p>
		</div>
		<%
			}
		}
		%>
	</div>
	<div class="main" style="overflow: hidden;">
		<div style="float: left; margin: 10px 50px;">
			<h1>成绩表按需查询</h1>
			<p>按需查询，全空为查询全部</p>
			<form method="get" action="achievement">
				<div>
					<label>序号：</label> <input type="text" name="achievement_id"
						placeholder="请输入">
				</div>
				<div>
					<label>学号：</label> <input type="text" name="stu_id"
						placeholder="请输入">
				</div>
				<div>
					<label>课程：</label> <input type="text" name="course_id"
						placeholder="请输入">
				</div>
				<div>
					<label>成绩：</label> <input type="text" name="result"
						placeholder="请输入">
				</div>
				<div>
					<input type="submit" name="submit" />
				</div>
			</form>
		</div>

	</div>

	<div class="res" style="margin: 10px; padding: 10px;">
		<%
			List<Achievement> achievelsit = (List<Achievement>) request.getAttribute("achievelsit") != null
				? (List<Achievement>) request.getAttribute("achievelsit")
				: new ArrayList<Achievement>();
		%>
		<%
			if (achievelsit.size() == 0) {

		} else {
			for (int i = 0; i < achievelsit.size(); i++) {
				Achievement achi = achievelsit.get(i);
		%>
		<div
			style="float: left; width: 200px; height: 200px; border: solid 1px #aaa; box-shadow: #333 10px 10px 10px; text-align: center;">
			<p><%=achi.getAchievement_id()%></p>
			<p><%=achi.getStu_id()%></p>
			<p><%=achi.getCourse_id()%></p>
			<p><%=achi.getResult()%></p>
		</div>
		<%
			}
		}
		%>
	</div>
</body>
</html>