package com.graduationsystem.db.notice;

import java.sql.SQLException;
import java.util.ArrayList;

public class NoticeDAOTest {
	private static NoticeDAO noticeDAO = new NoticeDAO();

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
			//Notice notice = new Notice(1,1,"notice_title","notice_detail");
			//modifyTest(notice);
			System.out.println("getDetailByIdTest:");
			//getDetailByIdTest(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getAllTest() throws ClassNotFoundException, SQLException {
		ArrayList<Notice> notices = noticeDAO.getAll();
		for (Notice notice : notices) {
			System.out.print(notice + "\t");
			System.out.println();
		}
	}

	private static void getbyIdTest(int id) throws ClassNotFoundException, SQLException {
		Notice notice = noticeDAO.getById(id);
		System.out.println(notice);
	}

	private static void addTest() throws ClassNotFoundException, SQLException {
		Notice notice = new Notice();
		notice.setTeacher_id(1);
		notice.setNotice_title("notice_title");
		notice.setNotice_detail("notice_detail");

		noticeDAO.add(notice);
	}

	private static void deleteTest(int id) throws ClassNotFoundException, SQLException {
		noticeDAO.delete(id);
	}

	private static void modifyTest(Notice notice) throws ClassNotFoundException, SQLException {
		noticeDAO.modify(notice);
	}
	
	private static void getDetailByIdTest(int id) throws ClassNotFoundException, SQLException {
		Notice notice = noticeDAO.getDetailById(id);
		System.out.println(notice);
	}
}