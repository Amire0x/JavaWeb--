<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  	<link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
  <body>
	<div class="signinpanel">
		<div class="signin-desc">
			<h1>JavaWeb Login</h1>
			<h2>世界与我都在等你</h2>
			<p>别睡觉了打起精神来，我和猫猫都在看着你哦！</p>
		</div>
		<div class="signin-info">
			<div style="text-align:center;">
				<h1 class="signin-title">老板请登录</h1>
				<p>欢迎登录后台管理系统</p>
			</div>
			<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
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
					    <td width="60%" valign="middle">
					    	<input type="text" name="verifycode" value="${verifycode }" placeholder="请输入验证码"/>
					    </td>
					    <td width="40%" valign="middle">
					    	<img src="VerifyCodeServlet" id="verify" onclick="document.getElementById('verify').src='VerifyCodeServlet?'+Math.random();">
					    </td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="red" size="2">${loginError }</font>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="signin-btn">登录</button>
						</td>
					</tr>
					<tr>
					    <td width="50%" valign="middle" align="left">
					    	<a href="${pageContext.request.contextPath}/passwordreset.jsp" class="signup-link">忘记密码</a>
					    </td>
					    <td width="50%" valign="middle" align="right">
					    	<a href="${pageContext.request.contextPath}/regist.jsp" class="signup-link">账号注册</a>
					    </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
