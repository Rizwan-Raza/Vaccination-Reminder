package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GrowthBean;
import models.Child;

/**
 * Servlet implementation class AddChild
 */
@WebServlet("/AddGrowth")
public class AddGrowth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGrowth() {
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
		GrowthBean gb = new GrowthBean(0, Integer.parseInt(request.getParameter("cid")),
				Integer.parseInt(request.getParameter("height")), Integer.parseInt(request.getParameter("weight")),
				null, null);
		// System.out.println("Form Date: " + request.getParameter("dob"));
		// System.out.println("SQL Date: " +
		// stringToSQLDate(request.getParameter("dob")).toString());

		if (child.addGrowth(gb)) {
			response.getWriter().print("{\"response\":\"ok\"}");
		} else {
			response.getWriter().print("{\"response\":\"ko\"}");
		}
	}
}
