package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "class_tab") //指定表名
public class SchoolClass {

	@Column(length=50,nullable=false)
	private String className;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long classId;
	
	
    //  mappedBy是指定“多”的那一方中定义的与之对应的的属性名称
	@OneToMany(cascade=CascadeType.ALL,mappedBy="schoolClass")
	private List<Student> students;


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}

	
	
}
