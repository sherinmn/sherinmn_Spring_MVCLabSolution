package com.great.learning.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.great.learning.model.Student;
import com.great.learning.service.StudentService;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();

		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	public List<Student> getAllStudents() {
		List<Student> students = session.createQuery("from Student").list();
		return students;
	}

	public Student getStudent(int studentId) {
		Student theStudent = new Student();
		theStudent = session.get(Student.class, studentId);
		return theStudent;
	}

	@Transactional
	public void saveStudent(Student theStudent) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(theStudent);
		transaction.commit();

	}

	@Transactional
	public void deleteById(int studentId) {
		Transaction transaction = session.beginTransaction();
		Student theStudent = session.get(Student.class, studentId);
		session.delete(theStudent);
		transaction.commit();

	}

	public void print(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}

	

}
