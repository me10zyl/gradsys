package com.graduationsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.graduationsystem.db.duty.Duty;
import com.graduationsystem.db.duty.DutyDAO;
import com.graduationsystem.db.student.Student;
import com.graduationsystem.db.student.StudentDAO;
import com.graduationsystem.db.subject.Subject;
import com.graduationsystem.db.subject.SubjectDAO;
import com.graduationsystem.db.teacher.Teacher;
import com.graduationsystem.db.teacher.TeacherDAO;

public class SubjectServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SubjectDAO subjectDAO = new SubjectDAO();
		DutyDAO dutyDAO = new DutyDAO();
		StudentDAO studentDAO = new StudentDAO();
		String pathInfo = request.getPathInfo();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		try {
			if (pathInfo.equals("/Set")) {
				Teacher teacher = (Teacher) session.getAttribute("teacher");
				String subject_title = request.getParameter("subject_title");
				String subject_detail = request.getParameter("subject_detail");
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
				request.getRequestDispatcher("/Subject/SeeSubjectDetail?subject_id="+subject.getSubject_id()).forward(request, response);
			} else if (pathInfo.equals("/SeeSubject")) {
				String loginState = (String) session.getAttribute("loginState");
				boolean turnToHomePage = false;
				if (loginState != null) {
					if (loginState.equals("success")) {
						String userType = (String) session.getAttribute("userType");
						String page = request.getParameter("page");
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
							request.getRequestDispatcher("/studentChoiceSubject.jsp").forward(request, response);
						} else
							request.getRequestDispatcher("/teacherChoiceSubject.jsp").forward(request, response);
					} else {
						turnToHomePage = true;
					}
				} else {
					turnToHomePage = true;
				}
				if (turnToHomePage) {
					response.sendRedirect(request.getContextPath() + "/index_.jsp");
				}
			} else if (pathInfo.equals("/StudentChoose")) {
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
				request.getRequestDispatcher("/Subject/SeeSubject?page=" + page_).forward(request, response);
			} else if (pathInfo.equals("/TeacherChoose")) {
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
					for(int i =0; i < arr_subject_page.size();i++)
					{
						dutyDAO.deleteBySubjectIdAndTeacherId(arr_subject_page.get(i).getSubject_id(),teacher.getTeacher_id());
					}
//					dutyDAO.deleteByTeacherId(teacher.getTeacher_id());
					for (String subject_id : subject_ids) {
						dutyDAO.add(new Duty(Integer.parseInt(subject_id), teacher.getTeacher_id()));
					}
				} else {
					if(teacher != null)//防止直接访问Servlet
					{
						for(int i =0; i < arr_subject_page.size();i++)
						{
							dutyDAO.deleteBySubjectIdAndTeacherId(arr_subject_page.get(i).getSubject_id(),teacher.getTeacher_id());
						}
					}
				}
				request.getRequestDispatcher("/Subject/SeeSubject?page=" + page_).forward(request, response);
			} else if (pathInfo.equals("/SeeSubjectDetail")) {
				String subject_id = request.getParameter("subject_id");
				if (subject_id != null) {
					Subject subject = subjectDAO.getById(Integer.parseInt(subject_id));
					request.setAttribute("subject", subject);
					request.getRequestDispatcher("/subjectMessage.jsp").forward(request, response);
				}
			} else if (pathInfo.equals("/Modify")) {
				String subject_id = request.getParameter("subject_id");
				String subject_title = request.getParameter("subject_title");
				String subject_description = request.getParameter("subject_description");
				if (subject_id != null) {
					int subject_id_int = Integer.parseInt(subject_id);
					Subject subject = new Subject(subject_id_int,subjectDAO.getById(subject_id_int).getTeacher_id(), subject_title, subject_description);
					subjectDAO.modify(subject);
					request.setAttribute("subject", subject);
					request.getRequestDispatcher("/subjectMessage.jsp").forward(request, response);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<Subject> getSubjectsByPage(ArrayList<Subject> arr_subject, int pagesize, int page_) {
		int index_previous = (page_ - 1) * pagesize;
		int index_now = index_previous + pagesize > arr_subject.size() ? arr_subject.size() : index_previous + pagesize;
		List<Subject> subList = arr_subject.subList(index_previous, index_now);
		ArrayList<Subject> arr_subject_page = new ArrayList<Subject>();
		arr_subject_page.addAll(subList);
		return arr_subject_page;
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
