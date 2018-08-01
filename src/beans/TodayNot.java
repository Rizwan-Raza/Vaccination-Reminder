package beans;

public class TodayNot {
	private ChildBean child;
	private VaccineBean vaccine;

	public TodayNot(ChildBean child, VaccineBean vaccine) {
		super();
		this.child = child;
		this.vaccine = vaccine;
	}

	public ChildBean getChild() {
		return child;
	}

	public void setChild(ChildBean child) {
		this.child = child;
	}

	public VaccineBean getVaccine() {
		return vaccine;
	}

	public void setVaccine(VaccineBean vaccine) {
		this.vaccine = vaccine;
	}

}
