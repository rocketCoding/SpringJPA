package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
