package com.graduationsystem.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.graduationsystem.db.notice.Notice;
import com.graduationsystem.db.notice.NoticeDAO;
import com.graduationsystem.db.teacher.Teacher;
import com.opensymphony.xwork2.ActionContext;

public class NoticeAction implements SessionAware, RequestAware {
	private NoticeDAO noticeDAO = new NoticeDAO();
	private String notice_title;
	private String notice_detail;
	private int notice_id;
	private Map<String, Object> session, request;
	public final static String NOTICE_DETAIL = "notice_detail";
	public static final String NOTICE_SET = "notice_set";
	public static final String ACTION_SEE_NOTICE = "action_see_notice";
	private static final String SERVLET_AJAX_SEE_ALL = "servlet_ajax_see_all";

	public String add() throws ClassNotFoundException, SQLException {
		Notice notice = new Notice();
		notice.setNotice_title(notice_title);
		notice.setNotice_detail(notice_detail);
		notice.setTeacher_id(((Teacher) session.get("teacher")).getTeacher_id());
		noticeDAO.add(notice);
		ArrayList<Notice> notices = noticeDAO.getAll();
		for (Notice notice2 : notices) {
			notice.setNoticce_id(notice2.getNoticce_id());
		}
		ActionContext.getContext().getValueStack().setValue("notice_id", notice.getNoticce_id());
		return ACTION_SEE_NOTICE;
	}

	public String getNotice_detail() {
		return notice_detail;
	}

	public int getNotice_id() {
		return notice_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}

	public String see() throws ClassNotFoundException, SQLException {
		Notice notice = noticeDAO.getDetailById(notice_id);
		request.put("notice", notice);
		request.put("notice_teacher", notice.getTeacher().getTeacher_name());
		return NOTICE_DETAIL;
	}

	public String seeAll() {
		return SERVLET_AJAX_SEE_ALL;
	}

	public String seeAdd() {
		return NOTICE_SET;
	}

	public void setNotice_detail(String notice_detail) {
		this.notice_detail = notice_detail;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
