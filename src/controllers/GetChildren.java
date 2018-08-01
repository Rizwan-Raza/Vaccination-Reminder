package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ChildBean;
import models.Child;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetChildren")
public class GetChildren extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetChildren() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Child cb = new Child();

		ArrayList<ChildBean> ch = cb.getChildren();
		PrintWriter out = response.getWriter();
		out.println("{\"children\": [");
		boolean first = true;
		for (ChildBean child : ch) {
			if (!first) {
				out.print(",");
			}
			out.println("{");
			out.println("\"cid\":\"" + child.getcId() + "\",");
			out.println("\"c_name\":\"" + child.getcName() + "\",");
			out.println("\"f_name\":\"" + child.getfName() + "\",");
			out.println("\"m_name\":\"" + child.getmName() + "\",");
			out.println("\"email\":\"" + child.getEmail() + "\",");
			out.println("\"mobile\":\"" + child.getMobile() + "\",");
			out.println("\"dob\":\"" + child.getDob() + "\"");
			out.println("}");
			first = false;
		}
		out.println("]}");
	}

}
