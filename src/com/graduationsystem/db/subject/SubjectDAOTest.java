package com.graduationsystem.db.subject;

import java.util.ArrayList;
import com.graduationsystem.db.duty.*;
import com.graduationsystem.db.student.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDAOTest {
	private static SubjectDAO subjectDAO = new SubjectDAO();

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
			//Subject subject = new Subject(1,"subject_title","subject_description");
			//modifyTest(subject);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Subject> subjects = subjectDAO.getAll();
		for (Subject subject : subjects) {
			System.out.print(subject + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Subject subject = subjectDAO.getById(id);
		System.out.println(subject);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Subject subject = new Subject();
		subject.setSubject_title("subject_title");
		subject.setSubject_description("subject_description");

		subjectDAO.add(subject);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		subjectDAO.delete(id);
	}

	private static void modifyTest(Subject subject) throws ClassNotFoundException, SQLException {
		subjectDAO.modify(subject);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Subject subject = subjectDAO.getDetailById(id);
		System.out.println(subject);
	}
}