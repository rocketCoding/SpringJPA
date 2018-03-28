package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity 
@Table(name = "score_tab") //指定表名
public class PersonalScore {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long scoreId;
	@Column
	private float avg_score;
	//  mappedBy 写的是另一方 与之对应的属性名称
	@OneToOne(cascade=CascadeType.ALL,mappedBy="personalScore")
	private Student stu;
	public Long getScoreId() {
		return scoreId;
	}
	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}
	public float getAvg_score() {
		return avg_score;
	}
	public void setAvg_score(float avg_score) {
		this.avg_score = avg_score;
	}
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	
}
