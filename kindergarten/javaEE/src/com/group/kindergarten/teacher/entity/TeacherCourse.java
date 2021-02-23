package com.group.kindergarten.teacher.entity;

public class TeacherCourse {
	private int teacherId;
	private int courseId;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public TeacherCourse() {
		super();
	}

	public TeacherCourse(int teacherId, int courseId) {
		super();
		this.teacherId = teacherId;
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "TeacherCourse [teacherId=" + teacherId + ", courseId=" + courseId + "]";
	}

}
