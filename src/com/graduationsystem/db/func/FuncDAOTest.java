package com.graduationsystem.db.func;

import java.util.ArrayList;
import com.graduationsystem.db.predom.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuncDAOTest {
	private static FuncDAO funcDAO = new FuncDAO();

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
			//Func func = new Func(1,"func_name");
			//modifyTest(func);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Func> funcs = funcDAO.getAll();
		for (Func func : funcs) {
			System.out.print(func + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Func func = funcDAO.getById(id);
		System.out.println(func);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Func func = new Func();
		func.setFunc_name("func_name");

		funcDAO.add(func);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		funcDAO.delete(id);
	}

	private static void modifyTest(Func func) throws ClassNotFoundException, SQLException {
		funcDAO.modify(func);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Func func = funcDAO.getDetailById(id);
		System.out.println(func);
	}
}