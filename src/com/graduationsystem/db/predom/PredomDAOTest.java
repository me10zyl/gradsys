package com.graduationsystem.db.predom;

import java.util.ArrayList;
import com.graduationsystem.db.func.*;
import com.graduationsystem.db.usergroup.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class PredomDAOTest {
	private static PredomDAO predomDAO = new PredomDAO();

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
			//Predom predom = new Predom(1,1);
			//modifyTest(predom);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Predom> predoms = predomDAO.getAll();
		for (Predom predom : predoms) {
			System.out.print(predom + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Predom predom = predomDAO.getById(id);
		System.out.println(predom);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Predom predom = new Predom();
		predom.setFunc_id(1);

		predomDAO.add(predom);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		predomDAO.delete(id);
	}

	private static void modifyTest(Predom predom) throws ClassNotFoundException, SQLException {
		predomDAO.modify(predom);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Predom predom = predomDAO.getDetailById(id);
		System.out.println(predom);
	}
}