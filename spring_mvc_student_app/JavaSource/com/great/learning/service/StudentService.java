package com.great.learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.great.learning.model.Student;

@Service
public interface StudentService {
	
	List<Student> getAllStudents();
	
	Student getStudent(int studentId);
	
	void saveStudent(Student theStudent);
	
	void deleteById(int studentId);
	
	

}
