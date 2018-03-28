package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long>{

}
