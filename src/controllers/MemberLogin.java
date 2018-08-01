package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberBean;
import models.Member;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		if (sess.getAttribute("l-type") != null && sess.getAttribute("login") == "true") {
			request.getRequestDispatcher("/" + sess.getAttribute("l-type") + ".html").forward(request, response);
		} else {
			response.sendRedirect("/index.html");
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Member Model
		Member mem = new Member();

		// Member Bean
		MemberBean mb = new MemberBean(0, request.getParameter("username"), request.getParameter("password"), null,
				null, request.getParameter("login-type").contains("true"));

		PrintWriter out = response.getWriter();
		RequestDispatcher rd;

		String script = "<script type='text/javascript'>$(document).ready(function() {";

		if (mem.isUsernameExist(mb.getUsername())) {
			if (mem.login(mb)) {
				script += "sessionStorage.setItem('username', '" + mb.getUsername() + "');";
				HttpSession sess = request.getSession();
				sess.setAttribute("login", "true");
				sess.setAttribute("l-type", (mb.isAdmin() ? "admin" : "user"));
				rd = request.getRequestDispatcher("/" + (mb.isAdmin() ? "admin" : "dashboard") + ".html");
			} else {
				rd = request.getRequestDispatcher("/index.html");
				script += "$('#resultModal .modal-title').text('Authentication Error');$('#resultModal .modal-body').html('Incorrect Password for <b>"
						+ mb.getUsername() + "</b>. Are you sure you are <b>" + (mb.isAdmin() ? "an admin" : "a user")
						+ "</b>');$('#resultModal').modal('show');";
			}
		} else {
			rd = request.getRequestDispatcher("/index.html");
			script += "$('#resultModal .modal-title').text('User not Exist');$('#resultModal .modal-body').html('<b>"
					+ mb.getUsername()
					+ "</b> is not yet registered to our system. Re-check the username');$('#resultModal').modal('show');";
		}
		rd.include(request, response);
		out.print(script + "})</script>");
		out.close();
	}

}
