package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Child;
import util.Mailer;

/**
 * Servlet implementation class Mail
 */
@WebServlet("/Mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Mailer.send(request.getParameter("email"), "Reminder for Vaccination",
				"The Vaccination " + request.getParameter("v_name") + " has to be given to your child on "
						+ request.getParameter("v_date") + " at Al-Shifa Hospital within 9:00 AM to 4:00 PM.");
		Child child = new Child();
		if (child.removeVaccineNoti(Integer.parseInt(request.getParameter("vid")))) {
			response.getWriter().print("{\"response\":\"ok\"}");
		} else {
			response.getWriter().print("{\"response\":\"ko\"}");
		}

	}
}
