package com.group.kindergarten.teacher.entity;

public class TeacherClass {
	private int teacherId;
	private int classId;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public TeacherClass() {
		super();
	}

	public TeacherClass(int teacherId, int classId) {
		super();
		this.teacherId = teacherId;
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "TeacherClass [teacherId=" + teacherId + ", classId=" + classId + "]";
	}

}
