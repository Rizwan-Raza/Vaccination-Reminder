package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.MemberBean;
import util.DBConnect;

public class Member {

	private PreparedStatement login;
	private PreparedStatement uAuth;
	private PreparedStatement signu;
	private PreparedStatement getUs;
	private PreparedStatement updUr;
	private PreparedStatement remUr;
	private PreparedStatement usrAd;
	private PreparedStatement getPs;
	private ResultSet rs;

	public Member() {
		Connection conn = DBConnect.getConnection();
		try {
			login = conn.prepareStatement(
					"SELECT username, password FROM members WHERE username=? AND password=? AND is_admin=?");
			uAuth = conn.prepareStatement("SELECT username FROM members WHERE username=?");
			signu = conn.prepareStatement("INSERT INTO members(username, password, email, mobile) VALUES (?,?,?,?)");
			getUs = conn.prepareStatement("SELECT mem_id, username, email, mobile, is_admin FROM members");
			updUr = conn.prepareStatement("UPDATE members SET username=?, email=?, mobile=? WHERE mem_id=?");
			remUr = conn.prepareStatement("DELETE FROM members WHERE mem_id=?");
			usrAd = conn.prepareStatement("UPDATE members SET is_admin=? WHERE mem_id=?");
			getPs = conn.prepareStatement("SELECT password FROM members WHERE email=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login(MemberBean mem) {
		try {
			login.setString(1, mem.getUsername());
			login.setString(2, mem.getPassword());
			login.setInt(3, mem.isAdmin() == true ? 1 : 0);
			rs = login.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<MemberBean> getUsers() {
		try {
			rs = getUs.executeQuery();
			ArrayList<MemberBean> users = new ArrayList<MemberBean>();
			while (rs.next()) {
				users.add(new MemberBean(rs.getInt(1), rs.getString(2), null, rs.getString(3), rs.getString(4),
						rs.getInt(5) == 1));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isUsernameExist(String uname) {
		try {
			uAuth.setString(1, uname);
			rs = uAuth.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean signup(MemberBean mem) {
		try {
			signu.setString(1, mem.getUsername());
			signu.setString(2, mem.getPassword());
			signu.setString(3, mem.getEmail());
			signu.setString(4, mem.getMobile());

			signu.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editUser(MemberBean mem) {
		try {
			updUr.setString(1, mem.getUsername());
			updUr.setString(2, mem.getEmail());
			updUr.setString(3, mem.getMobile());
			updUr.setInt(4, mem.getMemId());

			if (updUr.executeUpdate() != 0) {
				return true;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeUser(int memid) {
		try {
			remUr.setInt(1, memid);

			if (remUr.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean makeAdmin(int memid, int yesorno) {
		try {
			usrAd.setInt(1, yesorno);
			usrAd.setInt(2, memid);

			if (usrAd.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getPs(String email) {
		try {
			getPs.setString(1, email);

			rs = getPs.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
