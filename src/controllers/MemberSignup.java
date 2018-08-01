package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberBean;
import models.Member;

/**
 * Servlet implementation class MemberSignup
 */
@WebServlet("/MemberSignup")
public class MemberSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberSignup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/index.html");
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
		MemberBean mb = new MemberBean(0, request.getParameter("username"), request.getParameter("pwd"),
				request.getParameter("email"), request.getParameter("telephone"), false);

		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("/index.html");
		rd.include(request, response);
		out.print("<script type='text/javascript'>$(document).ready(function() {");
		if (mem.signup(mb)) {
			out.print(
					"$('#resultModal .modal-title').text('Successfully Registered');$('#resultModal .modal-body').html('<b>"
							+ mb.getUsername()
							+ "</b> is successfully registered to our system.');$('#resultModal').modal('show');");
		} else {
			out.print("$('#resultModal .modal-title').text('Not Registered');$('#resultModal .modal-body').html('<b>"
					+ mb.getUsername() + "</b> is not registered to our system.');$('#resultModal').modal('show');");
		}
		out.print("})</script>");
		out.close();
	}

}
