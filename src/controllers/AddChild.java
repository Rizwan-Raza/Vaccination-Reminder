package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ChildBean;
import beans.GrowthBean;
import beans.VaccineBean;
import models.Child;

/**
 * Servlet implementation class AddChild
 */
@WebServlet("/AddChild")
public class AddChild extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddChild() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
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
		ChildBean ch = new ChildBean(0, request.getParameter("childname"), request.getParameter("fathername"),
				request.getParameter("mothername"), request.getParameter("email"), request.getParameter("telephone"),
				stringToSQLDate(request.getParameter("dob")));
		// System.out.println("Form Date: " + request.getParameter("dob"));
		// System.out.println("SQL Date: " +
		// stringToSQLDate(request.getParameter("dob")).toString());

		int cId = child.addChild(ch);
		if (cId != -1) {
			GrowthBean gb = new GrowthBean(0, cId, Integer.parseInt(request.getParameter("height")),
					Integer.parseInt(request.getParameter("weight")), stringToSQLDate(request.getParameter("dob")),
					null);
			child.addGrowth(gb);
			VaccineBean vb[] = {
					new VaccineBean(0, cId, "DPT Primary Dose1 And Hept1",
							stringToSQLDate(request.getParameter("dptdatep1"))),
					new VaccineBean(0, cId, "DPT Primary Dose2 And Hept2",
							stringToSQLDate(request.getParameter("dptdatep2"))),
					new VaccineBean(0, cId, "DPT Primary Dose3 And Hept3",
							stringToSQLDate(request.getParameter("dptdatep3"))),
					new VaccineBean(0, cId, "DPT 1st Booster,Measles2,JE2" + "",
							stringToSQLDate(request.getParameter("dpt1_me2_je2"))),
					new VaccineBean(0, cId, "DPT 2nd Booster", stringToSQLDate(request.getParameter("dpt2boost"))),
					new VaccineBean(0, cId, "Measles1,JE1", stringToSQLDate(request.getParameter("me1_je1"))),
					new VaccineBean(0, cId, "Tetanus 1st Dose", stringToSQLDate(request.getParameter("tt1"))),
					new VaccineBean(0, cId, "Tetanus 2nd Dose", stringToSQLDate(request.getParameter("tt2"))) };

			child.addVaccines(vb);
			response.getWriter().print("{\"response\":\"ok\", \"child\":\"" + ch.getcName() + "\"}");
		} else {
			response.getWriter().print("{\"response\":\"ko\",\"child\":\"" + ch.getcName() + "\"}");
		}
	}

	public java.sql.Date stringToSQLDate(String sDate) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf1.parse(sDate);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
