package com.graduationsystem.db.student;

import java.util.ArrayList;
import com.graduationsystem.db.subject.*;
import com.graduationsystem.db.usergroup.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOTest {
	private static StudentDAO studentDAO = new StudentDAO();

	/**
	 * @author ZyL
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			System.out.println("getAllTest:");
			getAllTest();
			System.out.println("getByIdTest:");
			getbyIdTest(1);
			System.out.println("addTest:");
			// addTest();
			System.out.println("deleteTest:");
			// deleteTest(1);
			System.out.println("modifyTest:");
			//Student student = new Student(1,1,1,"student_num","student_name","student_gender","student_grade","student_major","student_telphone","student_password");
			//modifyTest(student);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Student> students = studentDAO.getAll();
		for (Student student : students) {
			System.out.print(student + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Student student = studentDAO.getById(id);
		System.out.println(student);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Student student = new Student();
		student.setSubject_id(1);
		student.setUserGroup_id(1);
		student.setStudent_num("student_num");
		student.setStudent_name("student_name");
		student.setStudent_gender("student_gender");
		student.setStudent_grade("student_grade");
		student.setStudent_major("student_major");
		student.setStudent_telphone("student_telphone");
		student.setStudent_password("student_password");

		studentDAO.add(student);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		studentDAO.delete(id);
	}

	private static void modifyTest(Student student) throws ClassNotFoundException, SQLException {
		studentDAO.modify(student);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Student student = studentDAO.getDetailById(id);
		System.out.println(student);
	}
}