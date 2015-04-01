package com.graduationsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.graduationsystem.db.notice.Notice;
import com.graduationsystem.db.notice.NoticeDAO;
import com.graduationsystem.db.student.Student;

/**
 * Servlet implementation class NoticeAjaxServlet
 */
@WebServlet("/ajax/notice/seeAll")
public class NoticeAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeDAO noticeDAO = new NoticeDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ArrayList<Notice> notices = noticeDAO.getAll();
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			PrintWriter pw = response.getWriter();
			// response.setHeader("Content-Type", "text/plain;charset=utf-8");
			pw.print("[");
			HttpSession session = request.getSession();
			String userType = (String) session.getAttribute("userType");
			NoticeDAO noticeDAO = new NoticeDAO();
			ArrayList<Student> relatedStudents = null;
			Student student = null;
			String ajaxResponse = "";
			if (userType != null && "student".equals(userType)) {
				student = (Student) session.getAttribute("student");
			}
			for (int i = 0; i < notices.size(); i++) {
				Notice notice = notices.get(i);
				String json = String.format("{\"notice_id\":%s,\"notice_title\":\"%s\",\"notice_detail\":\"%s\"},", notice.getNoticce_id(), notice.getNotice_title(), notice.getNotice_detail());
				relatedStudents = noticeDAO.getRelatedStudentsByNoticeId(notice.getNoticce_id());
				if (relatedStudents == null || student == null || relatedStudents.contains(student)) {//relatedStudents 判断是否登陆 student == null判断是否是老师
					ajaxResponse += json;
				}
			}
			ajaxResponse = ajaxResponse.substring(0, ajaxResponse.length() - 1 >= 0 ? ajaxResponse.length() - 1 : 0);
			pw.print(ajaxResponse + "]");
			pw.flush();
			pw.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
