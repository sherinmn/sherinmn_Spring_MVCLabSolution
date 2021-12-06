package com.great.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.great.learning.model.Student;
import com.great.learning.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudents=studentService.getAllStudents();
		theModel.addAttribute("Students",theStudents);
		return "list-students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student",theStudent);
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel) {
		Student theStudent = studentService.getStudent(id);
		theModel.addAttribute("Student",theStudent);
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("department") String department,
			@RequestParam("country") String country) {
		
		System.out.println("Trying to save with Id: "+id);
		
		Student theStudent;
		if(id != 0) {
			theStudent = studentService.getStudent(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		} else {
			theStudent = new Student(name, department, country);
		}
		
		studentService.saveStudent(theStudent);
		
		return "redirect:/students/list";
		
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/students/list";
	}
	


}
