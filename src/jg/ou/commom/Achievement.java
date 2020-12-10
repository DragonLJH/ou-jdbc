package jg.ou.commom;

public class Achievement {

	private int achievement_id;
	private int stu_id;
	private int course_id;
	private double result;

	public Achievement() {

	}

	public Achievement(int achievement_id, int stu_id, int course_id, double result) {
		this.achievement_id = achievement_id;
		this.stu_id = stu_id;
		this.course_id = course_id;
		this.result = result;
	}

	public int getAchievement_id() {
		return achievement_id;
	}

	public void setAchievement_id(int achievement_id) {
		this.achievement_id = achievement_id;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Achievement [achievement_id=" + achievement_id + ", stu_id=" + stu_id + ", course_id=" + course_id
				+ ", result=" + result + "]";
	}

}
