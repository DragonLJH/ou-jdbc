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
					<label>学号：</label> <input id="stu_id" type="text" name="stu_id"
						placeholder="请输入">
				</div>
				<div>
					<label>姓名：</label> <input id="stu_name" type="text" name="stu_name"
						placeholder="请输入">
				</div>
				<div>
					<label>性别：</label> <input id="stu_sex" type="radio" name="stu_sex"
						value="1"> 男 <input id="stu_sex" type="radio"
						name="stu_sex" value="2"> 女
				</div>
				<div>
					<label>年龄：</label> <input id="stu_age" type="text" name="stu_age"
						placeholder="请输入">
				</div>
				<div>
					<label>专业：</label> <input id="stu_major" type="text"
						name="stu_major" placeholder="请输入">
				</div>
				<div>
					<input type="submit" name="submit" />
					<button type="button" onclick="create()">新增</button>
				</div>
			</form>
			<script type="text/javascript">
				function reset() {
					document.getElementById("stu_id").value = "";
					document.getElementById("stu_name").value = "";
					document.getElementById("stu_age").value = "";
					document.getElementById("stu_major").value = "";
				}

				function create() {
					let stu_id = document.getElementById("stu_id").value;
					let stu_name = document.getElementById("stu_name").value;
					let stu_sex = document.getElementById("stu_sex").value;
					let stu_age = document.getElementById("stu_age").value;
					let stu_major = document.getElementById("stu_major").value;
					console.log(stu_id, stu_name, stu_sex, stu_age, stu_major);
					if (stu_id === "" || stu_name === "" || stu_sex === ""
							|| stu_age === "" || stu_major === "") {
						alert("任意一个都不能为空");
						return;
					}
					//创建XMLHttpRequest对象
					var xhr = new XMLHttpRequest();
					//true表示异步,false表示同步
					xhr.open('POST', "student", true);
					xhr.onreadystatechange = function() {
						// readyState == 4说明请求已完成
						if (xhr.readyState == 4 && xhr.status == 200
								|| xhr.status == 304) {
							//responseText：从服务器获得数据
							data = xhr.responseText
							reset()
							alert("已更新" + data + "数据")
							console.log("data", data)
						}
					};
					xhr.setRequestHeader('content-type',
							'application/x-www-form-urlencoded; charset-UTF-8')
					xhr.send("stu_id=" + stu_id + "&stu_name=" + stu_name
							+ "&stu_sex=" + stu_sex + "&stu_age=" + stu_age
							+ "&stu_major=" + stu_major);
				}
			</script>
		</div>
		<div class="res" style="margin: 10px; padding: 10px; float: left;">
			<div
				style="float: left; width: 200px; height: 200px; border: solid 1px #aaa; box-shadow: #333 10px 10px 10px; text-align: center;">
				<p>学号</p>
				<p>姓名</p>
				<p>性别</p>
				<p>年龄</p>
				<p>专业</p>
			</div>
			<%
				List<Student> stuList = (List<Student>) request.getAttribute("stuList") != null
					? (List<Student>) request.getAttribute("stuList")
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
		<div class="res" style="margin: 10px; padding: 10px; float: left;">
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
	</div>


</body>
</html>