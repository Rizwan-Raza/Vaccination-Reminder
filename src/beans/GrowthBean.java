package beans;

import java.sql.Date;

public class GrowthBean {
	private int gId;
	private int cId;
	private int height;
	private int weight;
	private Date when;
	private String slot;

	public GrowthBean(int gId, int cId, int height, int weight, Date when, String slot) {
		super();
		this.gId = gId;
		this.cId = cId;
		this.height = height;
		this.weight = weight;
		this.when = when;
		this.slot = slot;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

}
