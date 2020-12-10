package jg.ou.commom;

public class Classroom {

	private int classroom_id;
	private int course_id;
	public int getClassroom_id() {
		return classroom_id;
	}
	public void setClassroom_id(int classroom_id) {
		this.classroom_id = classroom_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	@Override
	public String toString() {
		return "Classroom [classroom_id=" + classroom_id + ", course_id=" + course_id + "]";
	}
	
	
}
