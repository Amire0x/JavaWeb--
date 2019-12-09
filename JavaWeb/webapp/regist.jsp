<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  	<link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
  <body>
	<div class="signinpanel">
		<div class="signin-desc">
			<h1>JavaWeb Register</h1>
			<h2>世界与我都在等你</h2>
			<p>别睡觉了打起精神来，我和猫猫都在看着你哦！</p>
		</div>
		<div class="signin-info">
			<div style="text-align:center;">
				<h1 class="signin-title">老板请注册</h1>
			</div>
			<form action="${pageContext.request.contextPath}/RegistServlet" method="post">
				<table>
					<tr>
						<td colspan="2">
							<input type="text" name="username" value="${username }" placeholder="请输入您的大名"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="password" name="password" value="${password }" placeholder="请输入您的密码"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="password" name="rpsw" value="${rpsw }" placeholder="请再次输入密码"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="red" size="2"> ${registError }</font>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="signin-btn">注册</button>
						</td>
					</tr>
					<tr>
					    <td colspan="2" valign="middle" align="left">
					    	<a href="${pageContext.request.contextPath}/login.jsp" class="signup-link">已有账号？返回登录</a>
					    </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>