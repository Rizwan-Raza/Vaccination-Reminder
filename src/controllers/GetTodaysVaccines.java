package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TodayNot;
import models.Child;

/**
 * Servlet implementation class GetTodaysNoti
 */
@WebServlet("/GetTodaysVaccines")
public class GetTodaysVaccines extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTodaysVaccines() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Child child = new Child();

		ArrayList<TodayNot> ch = child.getTodaysVaccines();
		PrintWriter out = response.getWriter();
		out.println("{\"nots\": [");
		boolean first = true;
		for (TodayNot todayNot : ch) {
			if (!first) {
				out.print(",");
			}
			out.println("{");
			out.println("\"cid\":\"" + todayNot.getChild().getcId() + "\",");
			out.println("\"c_name\":\"" + todayNot.getChild().getcName() + "\",");
			out.println("\"f_name\":\"" + todayNot.getChild().getfName() + "\",");
			out.println("\"m_name\":\"" + todayNot.getChild().getmName() + "\",");
			out.println("\"email\":\"" + todayNot.getChild().getEmail() + "\",");
			out.println("\"v_name\":\"" + todayNot.getVaccine().getvName() + "\",");
			out.println("\"v_date\":\"" + todayNot.getVaccine().getvDate() + "\"");
			out.println("}");
			first = false;
		}
		out.println("]}");
	}

}
