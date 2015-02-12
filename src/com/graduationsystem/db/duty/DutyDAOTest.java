package com.graduationsystem.db.duty;

import java.util.ArrayList;
import com.graduationsystem.db.subject.*;
import com.graduationsystem.db.teacher.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class DutyDAOTest {
	private static DutyDAO dutyDAO = new DutyDAO();

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
			//Duty duty = new Duty(1,1);
			//modifyTest(duty);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Duty> dutys = dutyDAO.getAll();
		for (Duty duty : dutys) {
			System.out.print(duty + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Duty duty = dutyDAO.getById(id);
		System.out.println(duty);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Duty duty = new Duty();
		duty.setTeacher_id(1);

		dutyDAO.add(duty);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		dutyDAO.delete(id);
	}

	private static void modifyTest(Duty duty) throws ClassNotFoundException, SQLException {
		dutyDAO.modify(duty);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Duty duty = dutyDAO.getDetailById(id);
		System.out.println(duty);
	}
}