package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import beans.ChildBean;
import beans.GrowthBean;
import beans.TodayNot;
import beans.VaccineBean;
import util.DBConnect;

public class Child {
	private PreparedStatement addCh = null;
	private PreparedStatement addGr0 = null;
	private PreparedStatement addGr = null;
	private PreparedStatement addVc = null;
	private PreparedStatement getTN = null;
	private PreparedStatement getTV = null;
	private PreparedStatement getGr = null;
	private PreparedStatement getCh = null;
	private PreparedStatement getEm = null;
	private PreparedStatement updCh = null;
	private PreparedStatement remCh1 = null;
	private PreparedStatement remCh2 = null;
	private PreparedStatement remCh3 = null;
	private PreparedStatement remVc = null;
	private PreparedStatement getVc = null;

	private ResultSet rs = null;

	public Child() {
		Connection conn = DBConnect.getConnection();
		try {
			addCh = conn.prepareStatement(
					"INSERT INTO children(c_name, f_name, m_name, email, mobile, dob) VALUES (?, ?, ? ,? ,? ,?)",
					new String[] { "cid" });
			addGr0 = conn.prepareStatement("INSERT INTO growth(cid, height, weight, g_date) VALUES (?, ?, ?, ?)");
			addGr = conn.prepareStatement("INSERT INTO growth(cid, height, weight) VALUES (?, ?, ?)");
			addVc = conn.prepareStatement("INSERT INTO vaccines(cid, v_name, v_date) VALUES (?, ?, ?)");
			Date dt = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			dt = c.getTime();
			@SuppressWarnings("deprecation")
			String date0 = (dt.getYear() + 1900) + "-" + String.format("%02d", dt.getMonth() + 1) + "-"
					+ String.format("%02d", dt.getDate());
			c.add(Calendar.DATE, 2);
			dt = c.getTime();
			@SuppressWarnings("deprecation")
			String date2 = (dt.getYear() + 1900) + "-" + String.format("%02d", dt.getMonth() + 1) + "-"
					+ String.format("%02d", dt.getDate());
			// System.out.println("+0 Date: " + date0);
			// System.out.println("+2 Date: " + date2);
			// Oracle
			// getTN = conn.prepareStatement(
			// "SELECT children.cid, c_name, f_name, m_name, email, v_name, v_date, vid FROM
			// children, vaccines WHERE children.cid=vaccines.cid AND vaccines.mailed=0 AND
			// vaccines.v_date=to_date('"
			// + date2 + "', 'YYYY-MM-DD')");
			// getTV = conn.prepareStatement(
			// "SELECT children.cid, c_name, f_name, m_name, email, v_name, v_date FROM
			// children, vaccines WHERE children.cid=vaccines.cid AND
			// vaccines.v_date=to_date('"
			// + date0 + "', 'YYYY-MM-DD')");

			// Mysql
			getTN = conn.prepareStatement(
					"SELECT children.cid, c_name, f_name, m_name, email, v_name, v_date, vid FROM children, vaccines WHERE children.cid=vaccines.cid AND vaccines.mailed=0 AND vaccines.v_date='"
							+ date2 + "'");
			getTV = conn.prepareStatement(
					"SELECT children.cid, c_name, f_name, m_name, email, v_name, v_date FROM children, vaccines WHERE children.cid=vaccines.cid AND vaccines.v_date='"
							+ date0 + "'");
			getGr = conn.prepareStatement(
					"SELECT children.dob, growth.height, growth.weight, growth.g_date FROM children, growth WHERE children.cid=growth.cid AND children.cid=?");
			getEm = conn.prepareStatement("SELECT children.email FROM children WHERE children.cid=?");
			getCh = conn.prepareStatement("SELECT * FROM children");
			updCh = conn.prepareStatement(
					"UPDATE children SET c_name=?, f_name=?, m_name=?, email=?, mobile=? WHERE cid=?");
			remCh1 = conn.prepareStatement("DELETE FROM vaccines WHERE cid=?");
			remCh2 = conn.prepareStatement("DELETE FROM growth WHERE cid=?");
			remCh3 = conn.prepareStatement("DELETE FROM children WHERE cid=?");
			remVc = conn.prepareStatement("UPDATE vaccines SET mailed=1 WHERE vid=?");
			getVc = conn.prepareStatement("SELECT v_name, v_date, mailed FROM vaccines WHERE cid=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int addChild(ChildBean ch) {
		try {
			addCh.setString(1, ch.getcName());
			addCh.setString(2, ch.getfName());
			addCh.setString(3, ch.getmName());
			addCh.setString(4, ch.getEmail());
			addCh.setString(5, ch.getMobile());
			addCh.setDate(6, ch.getDob());

			if (addCh.executeUpdate() != 0) {
				rs = addCh.getGeneratedKeys();
				if (rs.next()) {
					return (int) rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean removeChild(int cid) {
		try {
			remCh1.setInt(1, cid);
			remCh2.setInt(1, cid);
			remCh3.setInt(1, cid);

			if (remCh3.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeVaccineNoti(int vid) {
		try {
			remVc.setInt(1, vid);

			if (remVc.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editChild(ChildBean ch) {
		try {
			updCh.setString(1, ch.getcName());
			updCh.setString(2, ch.getfName());
			updCh.setString(3, ch.getmName());
			updCh.setString(4, ch.getEmail());
			updCh.setString(5, ch.getMobile());
			updCh.setInt(6, ch.getcId());

			if (updCh.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addGrowth(GrowthBean gb) {
		if (gb.getWhen() != null) {
			addGr = addGr0;
			try {
				addGr.setDate(4, gb.getWhen());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			addGr.setInt(1, gb.getcId());
			addGr.setInt(2, gb.getHeight());
			addGr.setInt(3, gb.getWeight());
			addGr.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addVaccines(VaccineBean vb[]) {
		try {
			for (VaccineBean v : vb) {
				addVc.setInt(1, v.getcId());
				addVc.setString(2, v.getvName());
				addVc.setDate(3, v.getvDate());

				addVc.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<TodayNot> getTodaysNoti() {
		try {
			rs = getTN.executeQuery();
			ArrayList<TodayNot> notBeans = new ArrayList<TodayNot>();
			while (rs.next()) {
				notBeans.add(
						new TodayNot(
								new ChildBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
										rs.getString(5), null, null),
								new VaccineBean(rs.getInt(8), 0, rs.getString(6), rs.getDate(7))));
			}
			return notBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<VaccineBean> getVaccines(int cid) {
		try {
			getVc.setInt(1, cid);
			rs = getVc.executeQuery();
			ArrayList<VaccineBean> vaccines = new ArrayList<VaccineBean>();
			while (rs.next()) {
				vaccines.add(new VaccineBean(rs.getInt(3), cid, rs.getString(1), rs.getDate(2)));
			}
			return vaccines;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<TodayNot> getTodaysVaccines() {
		try {
			rs = getTV.executeQuery();
			ArrayList<TodayNot> notBeans = new ArrayList<TodayNot>();
			while (rs.next()) {
				notBeans.add(new TodayNot(new ChildBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), null, null), new VaccineBean(0, 0, rs.getString(6), rs.getDate(7))));
			}
			return notBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChildBean> getChildren() {
		try {
			rs = getCh.executeQuery();
			ArrayList<ChildBean> children = new ArrayList<ChildBean>();
			while (rs.next()) {
				children.add(new ChildBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7)));
			}
			return children;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<GrowthBean> getGrowths(int cid) {
		try {
			getGr.setInt(1, cid);
			rs = getGr.executeQuery();
			ArrayList<GrowthBean> growths = new ArrayList<GrowthBean>();
			while (rs.next()) {
				Date dob = rs.getDate(1);
				Date when = rs.getDate(4);
				long between = ChronoUnit.DAYS.between(LocalDate.parse(dob.toString()),
						LocalDate.parse(when.toString()));
				between = between / 7;
				growths.add(new GrowthBean(0, cid, Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)),
						rs.getDate(4), between + "weeks"));
			}
			return growths;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getEmail(int cid) {
		try {
			getEm.setInt(1, cid);
			rs = getEm.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
