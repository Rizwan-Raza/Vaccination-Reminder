package controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GrowthBean;
import beans.VaccineBean;
import models.Child;
import util.Mailer;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Report() {
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
		int cid = Integer.parseInt(request.getParameter("cid"));
		ArrayList<VaccineBean> vb_arr = child.getVaccines(cid);
		String msg = "<!DOCTYPE>" + "<html>" + "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html charset=UTF-8\" />" + "</head>"
				+ "<body style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto,'Helvetica Neue', Arial, sans-serif, 'Apple Color Emoji','Segoe UI Emoji', 'Segoe UI Symbol'; margin: 0px; padding: 0px;\">"
				+ "    <table style=\"width: 100%;max-width: 100%;border-collapse: collapse;border: 1px solid #e9ecef;\">"
				+ "        <thead style=\"color: #fff;background-color: #212529;border-color: #32383e;\">"
				+ "            <tr>"
				+ "                <th style=\"border-bottom-width: 2px;border-bottom: 2px solid #e9ecef;border: 1px solid #e9ecef;padding: .75rem;\">Vaccine Name</th>"
				+ "                <th style=\"border-bottom-width: 2px;border-bottom: 2px solid #e9ecef;border: 1px solid #e9ecef;padding: .75rem;\">Vaccine Date</th>"
				+ "                <th style=\"border-bottom-width: 2px;border-bottom: 2px solid #e9ecef;border: 1px solid #e9ecef;padding: .75rem;\">Taken</th>"
				+ "            </tr>" + "        </thead>" + "        <tbody>";
		ArrayList<GrowthBean> gb_arr = child.getGrowths(cid);
		boolean odd = true;
		for (VaccineBean vaccine : vb_arr) {
			msg += "            <tr " + (odd ? "style=\"background-color: rgba(0, 0, 0, .05);\">" : ">")
					+ "                <td style=\"padding: .75rem;vertical-align: top;border-top: 1px solid #e9ecef\">"
					+ vaccine.getvName() + "</td>"
					+ "                <td style=\"padding: .75rem;vertical-align: top;border-top: 1px solid #e9ecef\">"
					+ vaccine.getvDate() + "</td>"
					+ "                <td style=\"padding: .75rem;vertical-align: top;border-top: 1px solid #e9ecef\">"
					+ (vaccine.getvId() == 0 ? "No" : "Yes") + "</td>" + "            </tr>";
			odd = !odd;
		}
		msg += "        </tbody>" + "    </table>" + "    <br>"
				+ "    	<table style=\"padding: 0px 0px 40px 0px; background-color: antiquewhite; width: 100%; border: 0; margin: 0px;\">"
				+ "		<tr>" + "			<td>"
				+ "				<table style=\"background-color: antiquewhite; width: 100%; height: 450px;\">"
				+ "					<tr valign=\"bottom\">" + "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">40</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">35</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">30</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">25</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">20</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">15</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">10</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">5</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "					<tr valign=\"bottom\">"
				+ "						<td>"
				+ "							<table style=\"width: 100%; border-bottom: 1px dashed black; text-align: center;\">"
				+ "								<tr>"
				+ "									<td><span style=\"padding: 0px 10px background-color: antiquewhite\">0</span></td>"
				+ "								</tr>" + "							</table>"
				+ "						</td>" + "					</tr>" + "				</table>"
				+ "			</td>";
		String backs[][] = { { "#007bff", "#fff" }, { "#6c757d", "#fff" }, { "#28a745", "#fff" }, { "#dc3545", "#fff" },
				{ "#ffc107", "#343a40" }, { "#17a2b8", "#fff" }, { "#f8f9fa", "#343a40" }, { "#343a40", "#fff" } };
		int i = 0;
		int size = gb_arr.size();
		for (GrowthBean growth : gb_arr) {
			float bmi = growth.getWeight() / (growth.getHeight() / 100.0f);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			msg += "			<td valign=\"bottom\" style=\"border-bottom: 3px solid transparent;\">"
					+ "				<table style=\"height: " + bmi * 10 + "px;  background-color:" + backs[i][0]
					+ ";color: " + backs[i][1] + "; font-size: 20px; width: 100%;\">" + "					<tr>"
					+ "						<td valign=\"top\" align=\"center\">" + df.format(bmi)
					+ " <span style=\"font-size: 10px\">BMI</span></td>" + "					</tr>"
					+ "				</table>" + "			</td>";
			i++;
		}
		msg += "</tr><tr><td align=\"center\">&uarr; BMI</td>" + "";
		for (GrowthBean growth : gb_arr) {
			msg += "			<td align=\"center\">" + growth.getWhen() + "</td>";
		}
		msg += "		</tr>" + "		<tr>" + "			<td colspan=\"" + (size + 1) + "\">"
				+ "				<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\""
				+ "					style=\"width: 100%; text-align: center; text-decoration: underline; font-size: 24px;\">"
				+ "					<tr>" + "						<td>Growth of Your Child</td>"
				+ "					</tr>" + "				</table>" + "			</td>" + "		</tr>"
				+ "	</table>" + "</body>" + "</html>";

		if (Mailer.send(child.getEmail(cid), "Report for your Child", msg)) {
			response.getWriter().print(msg);
			// response.getWriter().print("{\"response\":\"ok\",\"msg\":\"" + msg + "\"}");
		} else {
			response.getWriter().print("<Error>" + msg);
		}
	}

}