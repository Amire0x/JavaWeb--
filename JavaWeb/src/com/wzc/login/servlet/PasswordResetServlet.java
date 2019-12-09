package com.wzc.login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wzc.login.dao.UserDao;

/**
 * @description 密码重置请求处理
 * @author Amire0x
 */
@WebServlet("/PasswordResetServlet")
public class PasswordResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//接收表单信息
		String username = request.getParameter("username");
		String newpassword = request.getParameter("newpassword");
		String rpsw = request.getParameter("rpsw");
		//校验
		if(username==null||username.trim().isEmpty()){
			request.setAttribute("resetError", "用户名不能为空");
			request.getRequestDispatcher("/passwordreset.jsp").forward(request, response);
			return;
		}
		if(newpassword==null||newpassword.trim().isEmpty()){
			request.setAttribute("resetError", "密码不能为空");
			request.getRequestDispatcher("/passwordreset.jsp").forward(request, response);
			return;
		}
		if(!newpassword.equals(rpsw)){
			request.setAttribute("resetError", "密码不一致");
			request.getRequestDispatcher("/passwordreset.jsp").forward(request, response);
			return;
		}
		//根据用户名修改密码
		UserDao ud = new UserDao();
		boolean res = ud.passwordUpdata(username, newpassword);
		if(res) {
			System.out.println("密码修改成功，请使用新的密码登录吧！");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			request.setAttribute("resetError", "修改失败，请尝试再次修改");
			request.getRequestDispatcher("/passwordreset.jsp").forward(request, response);
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
	doPost(request, response);
	}

}
