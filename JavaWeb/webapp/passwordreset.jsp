<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>密码重置页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  	<link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
  <body>
	<div class="signinpanel">
		<div class="signin-desc">
			<h1>JavaWeb PasswordReset</h1>
			<h2>世界与我都在等你</h2>
			<p>别睡觉了打起精神来，我和猫猫都在看着你哦！</p>
		</div>
		<div class="signin-info">
			<div style="text-align:center;">
				<h1 class="signin-title">密码找回</h1>
				<p>您可以在这里重置您的密码哦！</p>
			</div>
			<form action="${pageContext.request.contextPath}/PasswordResetServlet" method="post">
				<table>
					<tr>
						<td colspan="2">
							<input type="text" name="username" value="${username }" placeholder="请输入需要找回密码的用户名"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="password" name="newpassword" value="${newpassword }" placeholder="请输入您的新密码"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="password" name="rpsw" value="${rpsw }" placeholder="请再次输入新密码"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<font color="red" size="2">${resetError }</font>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="signin-btn">确定修改</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
