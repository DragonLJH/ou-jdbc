package jg.ou.commom;

public class Manage {

	private int manage_id;
	private String manage_name;
	private int manage_admin;
	public int getManage_id() {
		return manage_id;
	}
	public void setManage_id(int manage_id) {
		this.manage_id = manage_id;
	}
	public String getManage_name() {
		return manage_name;
	}
	public void setManage_name(String manage_name) {
		this.manage_name = manage_name;
	}
	public int getManage_admin() {
		return manage_admin;
	}
	public void setManage_admin(int manage_admin) {
		this.manage_admin = manage_admin;
	}
	@Override
	public String toString() {
		return "Manage [manage_id=" + manage_id + ", manage_name=" + manage_name + ", manage_admin=" + manage_admin
				+ "]";
	}
	
	
}
