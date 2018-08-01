package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ChildBean;
import models.Child;

/**
 * Servlet implementation class EditChild
 */
@WebServlet("/EditChild")
public class EditChild extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditChild() {
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
		// Child Model
		Child child = new Child();

		// Child Bean
		ChildBean ch = new ChildBean(Integer.parseInt(request.getParameter("cid")), request.getParameter("cname"),
				request.getParameter("fname"), request.getParameter("mname"), request.getParameter("email"),
				request.getParameter("telephone"), null);

		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("/admin.html");
		rd.include(request, response);
		out.print("<script type='text/javascript'>$(document).ready(function() {");
		if (child.editChild(ch)) {
			out.print(
					"$('#resultModal .modal-title').text('Child Successfully Edited');$('#resultModal .modal-body').html('<b>"
							+ ch.getcName() + "</b> is successfully updated to our system.');");
		} else {
			out.print("$('#resultModal .modal-title').text('Not Edited');$('#resultModal .modal-body').html('<b>"
					+ ch.getcName() + "</b> is not updated on our system.');");
		}
		out.print("$('#resultModal').modal('show');})</script>");
		out.close();
	}

}
