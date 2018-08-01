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
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUser() {
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
		MemberBean mb = new MemberBean(Integer.parseInt(request.getParameter("mem-id")),
				request.getParameter("username"), null, request.getParameter("email"),
				request.getParameter("telephone"), false);

		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("/admin.html");
		rd.include(request, response);
		out.print("<script type='text/javascript'>$(document).ready(function() {");
		if (mem.editUser(mb)) {
			out.print(
					"$('#resultModal .modal-title').text('User Successfully Edited');$('#resultModal .modal-body').html('<b>"
							+ mb.getUsername() + "</b> is successfully updated to our system.');");
		} else {
			out.print("$('#resultModal .modal-title').text('Not Edited');$('#resultModal .modal-body').html('<b>"
					+ mb.getUsername() + "</b> is not updated on our system.');");
		}
		out.print("$('#resultModal').modal('show');})</script>");
		out.close();
	}

}
