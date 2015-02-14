package com.graduationsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.graduationsystem.db.student.Student;
import com.graduationsystem.db.student.StudentDAO;
import com.graduationsystem.db.teacher.Teacher;
import com.graduationsystem.db.teacher.TeacherDAO;

public class UserServlet extends HttpServlet {

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
		TeacherDAO teacherDAO = new TeacherDAO();
		StudentDAO studentDAO = new StudentDAO();
		HttpSession session = request.getSession();
		String pathInfo = request.getPathInfo();
		request.setCharacterEncoding("utf-8");
		try {
			if (pathInfo.equals("/Login")) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String userType = request.getParameter("userType");
				if (userType.equals("student")) {
					Student student = studentDAO.getByNum(username);
					if (student != null) {
						String real_pass = student.getStudent_password();
						if (real_pass.equals(password)) {
							session.setAttribute("loginState", "success");
							session.setAttribute("userType", userType);
							session.setAttribute("student", student);
						} else {
							session.setAttribute("loginState", "wrong");
						}
					} else {
						session.setAttribute("loginState", "notexist");
					}
				} else if (userType.equals("teacher")) {
					Teacher teacher = teacherDAO.getByNum(username);
					if (teacher != null) {
						String real_pass = teacher.getTeacher_password();
						if (real_pass.equals(password)) {
							session.setAttribute("loginState", "success");
							session.setAttribute("userType", userType);
							session.setAttribute("teacher", teacher);
						} else {
							session.setAttribute("loginState", "wrong");
						}
					} else {
						session.setAttribute("loginState", "notexist");
					}
				}
				if (userType.equals("student")) {
					request.getRequestDispatcher("/personLogin.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/personLogin_t.jsp").forward(request, response);
				}
			} else if (pathInfo.equals("/Logout")) {
				removeStatus(session);
				response.sendRedirect(request.getContextPath() + "/index_.jsp");
			} else if (pathInfo.equals("/SeeSelf")) {
				String loginState = (String) session.getAttribute("loginState");
				boolean turnToHomePage = false;
				if (loginState != null) {
					if (loginState.equals("success")) {
						String userType = (String) session.getAttribute("userType");
						if (userType.equals("student"))
							response.sendRedirect(request.getContextPath() + "/studentMessage.jsp");
						else
							response.sendRedirect(request.getContextPath() + "/teacherMessage.jsp");
					} else {
						turnToHomePage = true;
					}
				} else {
					turnToHomePage = true;
				}
				if (turnToHomePage) {
					response.sendRedirect(request.getContextPath() + "/index_.jsp");
				}
			} else if (pathInfo.equals("/Modify"))// must login success
			{
				String userType = (String) session.getAttribute("userType");
				if (userType.equals("student")) {
					String student_id = request.getParameter("student_id");
					String student_num = request.getParameter("student_num");
					String student_name = request.getParameter("student_name");
					String student_gender = request.getParameter("student_gender");
					String student_grade = request.getParameter("student_grade");
					String student_major = request.getParameter("student_major");
					String student_telphone = request.getParameter("student_telphone");
					String student_password = request.getParameter("student_password");
					Student newStudent = (Student) session.getAttribute("student");
					newStudent.setStudent_id(Integer.parseInt(student_id));
					newStudent.setStudent_num(student_num);
					newStudent.setStudent_name(student_name);
					newStudent.setStudent_gender(student_gender);
					newStudent.setStudent_grade(student_grade);
					newStudent.setStudent_major(student_major);
					newStudent.setStudent_telphone(student_telphone);
					newStudent.setStudent_password(student_password);
					studentDAO.modifyWithOutSubject(newStudent);
					request.getRequestDispatcher("/studentMessage.jsp").forward(request, response);
				} else if (userType.equals("teacher")) {
					String teacher_id = request.getParameter("teacher_id");
					String teacher_num = request.getParameter("teacher_num");
					String teacher_name = request.getParameter("teacher_name");
					String teacher_gender = request.getParameter("teacher_gender");
					String teacher_telphone = request.getParameter("teacher_telephone");
					String teacher_password = request.getParameter("teacher_password");
					Teacher teacher = (Teacher) session.getAttribute("teacher");
					teacher.setTeacher_id(Integer.parseInt(teacher_id));
					teacher.setTeacher_num(teacher_num);
					teacher.setTeacher_name(teacher_name);
					teacher.setTeacher_gender(teacher_gender);
					teacher.setTeacher_telephone(teacher_telphone);
					teacher.setTeacher_password(teacher_password);
					teacherDAO.modify(teacher);
					request.getRequestDispatcher("/teacherMessage.jsp").forward(request, response);
				}
			} else if (pathInfo.equals("/SeeOtherStudent")) {
				String student_id = request.getParameter("student_id");
			} else if (pathInfo.equals("/SeeOtherTeacher")) {
				String teacher_id = request.getParameter("teacher_id");
				if (teacher_id != null) {
					Teacher teacher = teacherDAO.getById(Integer.parseInt(teacher_id));
					request.setAttribute("teacher", teacher);
					request.getRequestDispatcher("/lookteacherMessage.jsp").forward(request, response);
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

	private void removeStatus(HttpSession session) {
		session.removeAttribute("loginState");
		session.removeAttribute("student");
		session.removeAttribute("teacher");
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
