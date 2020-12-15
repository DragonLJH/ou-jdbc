<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
</head>
<body>
	<dvi
		style="width: 400px;height: 400px;margin: 0 auto;display: flex;justify-content: center;align-items: center;">
	<div
		style="text-align: center; font-size: 20px; background-color: #eee; padding: 40px;">
		<h2>登录</h2>
		<form action="login" method="get">
			<div style="margin: 20px 0px;">
				<label>账号：<input style="padding: 5px 0px;" type="text"
					name="stu_id" id="stu_id" /></label>
			</div>
			<div style="margin: 20px 0px;">
				<label>密码：<input style="padding: 5px 0px;" type="password"
					name="pwd" id="pwd" /></label>
			</div>
			<div>
				<input type="submit" style="padding: 5px 40px;" />
			</div>
		</form>
	</div>
	</dvi>
</body>
</html>