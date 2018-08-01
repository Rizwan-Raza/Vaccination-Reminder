package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GrowthBean;
import models.Child;

/**
 * Servlet implementation class GetTodaysNoti
 */
@WebServlet("/GetGrowth")
public class GetGrowth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetGrowth() {
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

		ArrayList<GrowthBean> growths = child.getGrowths(Integer.parseInt(request.getParameter("cid")));
		PrintWriter out = response.getWriter();
		out.println("{\"growths\": [");
		boolean first = true;
		for (GrowthBean growth : growths) {
			if (!first) {
				out.print(",");
			}
			out.println("{");
			out.println("\"weight\":\"" + growth.getWeight() + "\",");
			out.println("\"height\":\"" + growth.getHeight() + "\",");
			out.println("\"timeslot\":\"" + growth.getWhen() + "\"");
			out.println("}");
			first = false;
		}
		out.println("]}");
	}

}
