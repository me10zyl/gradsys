package com.graduationsystem.db.teacher;

import java.util.ArrayList;
import com.graduationsystem.db.usergroup.*;

import com.graduationsystem.db.duty.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAOTest {
	private static TeacherDAO teacherDAO = new TeacherDAO();

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
			//Teacher teacher = new Teacher(1,1,"teacher_num","teacher_name","teacher_gender","teacher_telephone","teacher_password");
			//modifyTest(teacher);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Teacher> teachers = teacherDAO.getAll();
		for (Teacher teacher : teachers) {
			System.out.print(teacher + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Teacher teacher = teacherDAO.getById(id);
		System.out.println(teacher);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Teacher teacher = new Teacher();
		teacher.setUserGroup_id(1);
		teacher.setTeacher_num("teacher_num");
		teacher.setTeacher_name("teacher_name");
		teacher.setTeacher_gender("teacher_gender");
		teacher.setTeacher_telephone("teacher_telephone");
		teacher.setTeacher_password("teacher_password");

		teacherDAO.add(teacher);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		teacherDAO.delete(id);
	}

	private static void modifyTest(Teacher teacher) throws ClassNotFoundException, SQLException {
		teacherDAO.modify(teacher);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Teacher teacher = teacherDAO.getDetailById(id);
		System.out.println(teacher);
	}
}