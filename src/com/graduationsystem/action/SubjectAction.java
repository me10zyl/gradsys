package com.graduationsystem.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.graduationsystem.db.duty.Duty;
import com.graduationsystem.db.duty.DutyDAO;
import com.graduationsystem.db.student.Student;
import com.graduationsystem.db.student.StudentDAO;
import com.graduationsystem.db.subject.Subject;
import com.graduationsystem.db.subject.SubjectDAO;
import com.graduationsystem.db.teacher.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class SubjectAction extends ActionSupport {
	private HttpSession session;
	private HttpServletRequest request;
	private SubjectDAO subjectDAO = new SubjectDAO();
	private DutyDAO dutyDAO = new DutyDAO();
	private StudentDAO studentDAO = new StudentDAO();
	// params
	private String subject_title;
	private String subject_detail;
	private String subject_id;
	private String subject_description;
	private String page;
	public static final String STUDENT_SUBJECT_CHOOSE = "student_subject_choose";
	public static final String TEACHER_SUBJECT_CHOOSE = "teacher_subject_choose";
	public static final String SUBJECT_DETAIL = "subject_detail";
	public static final String ACTION_SEE_SUBEJECT_DETAIL = "actionSeeSubjectDetail";
	public static final String ACTION_SEE_SUBJECT = "actionSeeSubject";

	public SubjectAction() throws UnsupportedEncodingException {
		// TODO Auto-generated constructor stub
		request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String getPage() {
		return page;
	}

	public String getSubject_description() {
		return subject_description;
	}

	public String getSubject_detail() {
		return subject_detail;
	}

	public String getSubject_id() {
		return subject_id;
	}

	public String getSubject_title() {
		return subject_title;
	}

	public String modify() throws ClassNotFoundException, SQLException {
		if (subject_id != null) {
			Subject subject = new Subject(Integer.parseInt(subject_id), subject_title, subject_description);
			subjectDAO.modify(subject);
			request.setAttribute("subject", subject);
			return SUBJECT_DETAIL;
		}
		return UserAction.INDEX;
	}

	public String seeSubject() throws ClassNotFoundException, SQLException {
		String loginState = (String) session.getAttribute("loginState");
		boolean turnToHomePage = false;
		if (loginState != null) {
			if (loginState.equals("success")) {
				String userType = (String) session.getAttribute("userType");
				int page_ = 1;
				ArrayList<Subject> arr_subject = (ArrayList<Subject>) subjectDAO.getAll();
				int recordcount = arr_subject.size();
				int pagesize = 5;
				int pagecount = (recordcount + pagesize - 1) / pagesize;
				if (page != null)
					page_ = Integer.parseInt(page);
				ArrayList<Subject> arr_subject_page = getSubjectsByPage(arr_subject, pagesize, page_);
				request.setAttribute("pagecount", pagecount);
				request.setAttribute("page", page_);
				request.setAttribute("arr_subject", arr_subject_page);
				if (userType.equals("student")) {
					return STUDENT_SUBJECT_CHOOSE;
				} else {
					return TEACHER_SUBJECT_CHOOSE;
				}
			} else {
				turnToHomePage = true;
			}
		} else {
			turnToHomePage = true;
		}
		if (turnToHomePage) {
			return UserAction.INDEX;
		}
		return UserAction.INDEX;
	}

	public String seeSubjectDetail() throws NumberFormatException, ClassNotFoundException, SQLException {
		if (subject_id != null) {
			Subject subject = subjectDAO.getById(Integer.parseInt(subject_id));
			request.setAttribute("subject", subject);
			return SUBJECT_DETAIL;
		}
		return ERROR;
	}

	public String set() throws ClassNotFoundException, SQLException {
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		Subject subject = new Subject();
		subject.setSubject_title(subject_title);
		subject.setSubject_description(subject_detail);
		subjectDAO.add(subject);
		for (Subject subject2 : subjectDAO.getAll()) {
			subject.setSubject_id(subject2.getSubject_id());
		}
		Duty duty = new Duty();
		duty.setSubject_id(subject.getSubject_id());
		duty.setTeacher_id(teacher.getTeacher_id());
		dutyDAO.add(duty);
		ActionContext.getContext().getValueStack().setValue("subject_id", subject.getSubject_id());
		return ACTION_SEE_SUBEJECT_DETAIL;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setSubject_description(String subject_description) {
		this.subject_description = subject_description;
	}

	public void setSubject_detail(String subject_detail) {
		this.subject_detail = subject_detail;
	}

	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	public void setSubject_title(String subject_title) {
		this.subject_title = subject_title;
	}

	public String studentChoose() throws ClassNotFoundException, SQLException {
		String page = request.getParameter("page");
		int page_ = 1;
		if (page != null) {
			page_ = Integer.parseInt(page);
		}
		String subject_id = request.getParameter("subject_id");
		Student student = (Student) session.getAttribute("student");
		if (subject_id != null) {
			student.setSubject_id(Integer.parseInt(subject_id));
			studentDAO.modify(student);
		}
		ActionContext.getContext().getValueStack().setValue("page", page_);
		return ACTION_SEE_SUBJECT;
	}

	public String teacherChoose() throws ClassNotFoundException, SQLException {
		String[] subject_ids = request.getParameterValues("subject_id");
		String page = request.getParameter("page");
		int page_ = 1;
		if (page != null) {
			page_ = Integer.parseInt(page);
		}
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		ArrayList<Subject> arr_subject = (ArrayList<Subject>) subjectDAO.getAll();
		ArrayList<Subject> arr_subject_page = getSubjectsByPage(arr_subject, 5, page_);
		if (subject_ids != null) {
			for (int i = 0; i < arr_subject_page.size(); i++) {
				dutyDAO.deleteBySubjectIdAndTeacherId(arr_subject_page.get(i).getSubject_id(), teacher.getTeacher_id());
			}
			// dutyDAO.deleteByTeacherId(teacher.getTeacher_id());
			for (String subject_id : subject_ids) {
				dutyDAO.add(new Duty(Integer.parseInt(subject_id), teacher.getTeacher_id()));
			}
		} else {
			if (teacher != null)// 防止直接访问Servlet
			{
				for (int i = 0; i < arr_subject_page.size(); i++) {
					dutyDAO.deleteBySubjectIdAndTeacherId(arr_subject_page.get(i).getSubject_id(), teacher.getTeacher_id());
				}
			}
		}
		ActionContext.getContext().getValueStack().setValue("page", page_);
		return ACTION_SEE_SUBJECT;
	}

	private ArrayList<Subject> getSubjectsByPage(ArrayList<Subject> arr_subject, int pagesize, int page_) {
		int index_previous = (page_ - 1) * pagesize;
		int index_now = index_previous + pagesize > arr_subject.size() ? arr_subject.size() : index_previous + pagesize;
		List<Subject> subList = arr_subject.subList(index_previous, index_now);
		ArrayList<Subject> arr_subject_page = new ArrayList<Subject>();
		arr_subject_page.addAll(subList);
		return arr_subject_page;
	}
}
