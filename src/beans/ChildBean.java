package beans;

import java.sql.Date;

public class ChildBean {
	private int cId;
	private String cName;
	private String fName;
	private String mName;
	private String email;
	private String mobile;
	private Date dob;

	public ChildBean(int cId, String cName, String fName, String mName, String email, String mobile, Date dob) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.fName = fName;
		this.mName = mName;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
