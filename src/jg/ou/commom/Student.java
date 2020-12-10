package jg.ou.commom;

public class Student {

	private int stu_id;
	private int stu_sex;
	private int stu_age;
	private String stu_major;
	private String stu_name;
	
	
	
	public Student() {
		
	}

	public Student(int stu_id,int stu_sex,int stu_age,String stu_name,String stu_major) {
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.stu_sex = stu_sex;
		this.stu_age = stu_age;
		this.stu_major = stu_major;
		
	}
	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public int getStu_sex() {
		return stu_sex;
	}

	public void setStu_sex(int stu_sex) {
		this.stu_sex = stu_sex;
	}

	public int getStu_age() {
		return stu_age;
	}

	public void setStu_age(int stu_age) {
		this.stu_age = stu_age;
	}

	public String getStu_major() {
		return stu_major;
	}

	public void setStu_major(String stu_major) {
		this.stu_major = stu_major;
	}

	@Override
	public String toString() {
		return "Student [stu_id=" + stu_id + ", stu_name=" + stu_name + ", stu_sex=" + stu_sex + ", stu_age=" + stu_age
				+ ", stu_major=" + stu_major + "]";
	}
	 
	
}
