package com.graduationsystem.db.usergroup;

import java.util.ArrayList;
import com.graduationsystem.db.predom.*;
import com.graduationsystem.db.student.*;
import com.graduationsystem.db.teacher.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsergroupDAOTest {
	private static UsergroupDAO usergroupDAO = new UsergroupDAO();

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
			//Usergroup usergroup = new Usergroup(1,"userGroup_name");
			//modifyTest(usergroup);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Usergroup> usergroups = usergroupDAO.getAll();
		for (Usergroup usergroup : usergroups) {
			System.out.print(usergroup + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Usergroup usergroup = usergroupDAO.getById(id);
		System.out.println(usergroup);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Usergroup usergroup = new Usergroup();
		usergroup.setUserGroup_name("userGroup_name");

		usergroupDAO.add(usergroup);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		usergroupDAO.delete(id);
	}

	private static void modifyTest(Usergroup usergroup) throws ClassNotFoundException, SQLException {
		usergroupDAO.modify(usergroup);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Usergroup usergroup = usergroupDAO.getDetailById(id);
		System.out.println(usergroup);
	}
}