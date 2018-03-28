package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_tab") // 指定表名
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sid;
	@Column(length = 40)
	private String name;
	@Column
	private Integer age;
	@Column(name = "gender", length = 10)
	private String gender;

	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	// 定义中间表的名称为course_student 并且这个中间表的stuId字段需要参考 本表的sid字段   courseId参考另一张表的courseId字段
	@JoinTable(name = "course_student", joinColumns = {
			@JoinColumn(name = "stuId", referencedColumnName = "sid") }, 
	inverseJoinColumns = @JoinColumn(name = "courseId",referencedColumnName="courseId"))
	private List<Course> courses;

	// 设置student_tab的外键，和其参考的外键
	@JoinColumn(name = "schoolClassId", referencedColumnName = "classId")
	@ManyToOne(cascade=CascadeType.ALL)
	private SchoolClass schoolClass;

	// 指明一个外键字段“scoreId” 参考的是另一个表的主键“scoreId”
	@JoinColumn(name = "scoreId", unique = true, referencedColumnName = "scoreId", insertable = false)
	@OneToOne(cascade=CascadeType.ALL)
	private PersonalScore personalScore;

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	public PersonalScore getPersonalScore() {
		return personalScore;
	}

	public void setPersonalScore(PersonalScore personalScore) {
		this.personalScore = personalScore;
	}

}
