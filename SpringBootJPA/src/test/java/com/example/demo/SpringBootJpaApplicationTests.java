package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.CourseRepository;
import com.example.demo.dao.PersonalScoreRepository;
import com.example.demo.dao.SchoolClassRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.PersonalScore;
import com.example.demo.domain.SchoolClass;
import com.example.demo.domain.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	PersonalScoreRepository personalScoreRepository;
	
	@Autowired
	SchoolClassRepository schoolClassRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	// @Test
	public void addCourse() {
		
		List<Course> courses=new ArrayList<>();
		for(int i=1;i<=20;i++) {
			
			Course course=new Course();
			course.setCourseName("课程名称 "+i);
			courses.add(course);
		}
		courseRepository.saveAll(courses);
		
	}
	
	
	// @Test
	public void addClass() {
		List<SchoolClass> SchoolClasss=new ArrayList<>();
		for(int i=1;i<=20;i++) {
			
			SchoolClass SchoolClass=new SchoolClass();
			SchoolClass.setClassName("班级名称 "+i);
			SchoolClasss.add(SchoolClass);
		}
		schoolClassRepository.saveAll(SchoolClasss);
	} 

	// @Test
	public void addScore() {
		List<PersonalScore> scores=new ArrayList<>();
		for(int i=1;i<=50;i++) {
			PersonalScore score=new PersonalScore();
		    float sco=(float)(Math.random()*(100-60+1))+60;
			score.setAvg_score(sco);
			scores.add(score);
		}
		personalScoreRepository.saveAll(scores);
	}
	
	
	
	// ---------------数据插入完毕
	
	@Test
	public void testSelect() {
		Optional<Student> findById = studentRepository.findById((long) 5);
		Student student = findById.get();
		System.out.println("姓名:"+student.getName());
		System.out.println("年龄:"+student.getAge());
		System.out.println("性别:"+student.getGender());
		SchoolClass schoolClass = student.getSchoolClass();
		System.out.println("班级名称:"+schoolClass.getClassName());
		PersonalScore personalScore = student.getPersonalScore();
		System.out.println("个人得分:"+personalScore.getAvg_score());
		List<Course> courses = student.getCourses();
		
		// Student类上如果不设置及时加载，这里是无法获取到课程数据的
		System.out.println("选修的课程有:");
		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getCourseName());
		}
	}
	
	@Test
	public void testSelect2() {
		
		Course course = courseRepository.findById((long)1).get();
		
		System.out.println("课程名："+course.getCourseName());
		
		System.out.println("选修该课程的学生名单：");
		// 同样的也是必须设置为即时加载才能获取到数据
		List<Student> students = course.getStudents();
		for (Student stu : students) {
			System.out.println(stu.getName());
		}
	}
	
	@Test
	public void testDel() {
		// 当删除id为50的得分的时候，对应的学生信息也会被删除
		personalScoreRepository.deleteById((long)50);
	}
	
	
	@Test
	public void testCascadeSave() {
		// 测试能不能级联保存
		// https://www.jianshu.com/p/e8caafce5445
		
		Student stu=new Student();
		stu.setAge(22);
		stu.setName("张健");
		stu.setGender("男");
		
		// 新建一个 PersonalScore 对象 测试在保存Student对象的时候能不能把得分对象也自动保存了
		PersonalScore score=new PersonalScore();
		score.setAvg_score((float)88.6352);
		stu.setPersonalScore(score);
		
		SchoolClass schoolClass=new SchoolClass();
		schoolClass.setClassName("网络工程");
		stu.setSchoolClass(schoolClass);
		
		Course course=new Course();
		course.setCourseName("地理学");
		List<Course> courses=new ArrayList<>();
		courses.add(course);
		stu.setCourses(courses);
		
		stu.setSid((long)15);
		
		
		List<Student> students=new ArrayList<>();
		students.add(stu);
		schoolClass.setStudents(students);
		
		course.setStudents(students);
		
		score.setStu(stu);
		
		studentRepository.save(stu);
		
		
	}
	
}
