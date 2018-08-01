package beans;

import java.sql.Date;

public class VaccineBean {
	private int vId;
	private int cId;
	private String vName;
	private Date vDate;

	public VaccineBean(int vId, int cId, String vName, Date vDate) {
		super();
		this.vId = vId;
		this.cId = cId;
		this.vName = vName;
		this.vDate = vDate;
	}

	public int getvId() {
		return vId;
	}

	public void setvId(int vId) {
		this.vId = vId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

}
