package beans;

public class MemberBean {
	private int memId;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private boolean isAdmin;

	public MemberBean(int memId, String username, String password, String email, String mobile, boolean isAdmin) {
		super();
		this.memId = memId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.isAdmin = isAdmin;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
