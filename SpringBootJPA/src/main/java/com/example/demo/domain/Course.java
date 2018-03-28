package com.example.demo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "course_tab") //指定表名
public class Course {
	@Column(length=50,nullable=false)
	private String courseName;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long courseId;
	
	// mappedBy是写另一方实体类中定义的 集合属性名称
	@ManyToMany(mappedBy="courses",fetch=FetchType.EAGER)
	private List<Student> students;

	
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
