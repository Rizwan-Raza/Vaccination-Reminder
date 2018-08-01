package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberBean;
import models.Member;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member mb = new Member();

		ArrayList<MemberBean> users = mb.getUsers();
		PrintWriter out = response.getWriter();
		out.println("{\"users\": [");
		boolean first = true;
		for (MemberBean member : users) {
			if (!first) {
				out.print(",");
			}
			out.println("{");
			out.println("\"memId\":\"" + member.getMemId() + "\",");
			out.println("\"username\":\"" + member.getUsername() + "\",");
			out.println("\"email\":\"" + member.getEmail() + "\",");
			out.println("\"mobile\":\"" + member.getMobile() + "\",");
			out.println("\"admin\":\"" + member.isAdmin() + "\"");
			out.println("}");
			first = false;
		}
		out.println("]}");
	}

}
