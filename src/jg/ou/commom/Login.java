package jg.ou.commom;

public class Login {
	private int stu_id;
	private String pwd;

	public Login() {

	}

	public Login(int stu_id, String pwd) {
		this.stu_id = stu_id;
		this.pwd = pwd;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
