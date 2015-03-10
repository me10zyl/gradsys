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

import com.graduationsystem.db.notice.Notice;
import com.graduationsystem.db.notice.NoticeDAO;

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
			PrintWriter pw = response.getWriter();
			pw.print("[");
			for(int i = 0;i < notices.size();i++)
			{
				Notice notice = notices.get(i);
				String json = String.format("{\"notice_id\":%s,\"notice_title\":\"%s\",\"notice_detail\":\"%s\"},",notice.getNoticce_id(),notice.getNotice_title(),notice.getNotice_detail());
				if(i == notices.size() - 1)
				{
					json = json.substring(0,json.length() - 1);
				}
				pw.print(json);
			}
			pw.print("]");
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
