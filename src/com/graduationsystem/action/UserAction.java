package com.graduationsystem.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.graduationsystem.db.student.Student;
import com.graduationsystem.db.student.StudentDAO;
import com.graduationsystem.db.teacher.Teacher;
import com.graduationsystem.db.teacher.TeacherDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	// login
	private String username;
	private String password;
	private String userType;
	// profile
	private String student_id;
	private String student_num;
	private String student_name;
	private String student_gender;
	private String student_grade;
	private String student_major;
	private String student_telphone;
	private String student_password;
	private String teacher_id;
	private String teacher_num;
	private String teacher_name;
	private String teacher_gender;
	private String teacher_telephone;
	private String teacher_password;
	// not params
	private TeacherDAO teacherDAO = new TeacherDAO();
	private StudentDAO studentDAO = new StudentDAO();
	private HttpSession session;
	private HttpServletRequest request;
	public static final String STUDENT_PROFILE = "student_profile";// 学生个人信息
	public static final String TEACHER_PROFILE = "teacher_profile";// 老师个人信息
	public static final String STUDENT_LOGINED = "student_logined";// 学生登录后的页面
	public static final String TEACHER_LOGINED = "teacher_logined";// 老师登录后的页面
	public static final String INDEX = "index";
	public static final String TEACHER_PROFILE_LOOK = "teacher_profile_look";

	public UserAction() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String getPassword() {
		return password;
	}

	public HttpSession getSession() {
		return session;
	}

	public String getStudent_gender() {
		return student_gender;
	}

	public String getStudent_grade() {
		return student_grade;
	}

	public String getStudent_id() {
		return student_id;
	}

	public String getStudent_major() {
		return student_major;
	}

	public String getStudent_name() {
		return student_name;
	}

	public String getStudent_num() {
		return student_num;
	}

	public String getStudent_password() {
		return student_password;
	}

	public String getStudent_telphone() {
		return student_telphone;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public String getTeacher_gender() {
		return teacher_gender;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public String getTeacher_num() {
		return teacher_num;
	}

	public String getTeacher_password() {
		return teacher_password;
	}

	public String getTeacher_telephone() {	
		return teacher_telephone;
	}

	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public String getUsername() {
		return username;
	}

	public String getUserType() {
		return userType;
	}

	public String login() throws ClassNotFoundException, SQLException {
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
			return STUDENT_LOGINED;
		} else {
			return TEACHER_LOGINED;
		}
	}

	public String logout() {
		removeStatus(session);
		return INDEX;
	}

	public String modify() throws ClassNotFoundException, SQLException {
		String userType = (String) session.getAttribute("userType");
		if (userType.equals("student")) {
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
			return STUDENT_PROFILE;
		} else if (userType.equals("teacher")) {
			Teacher teacher = (Teacher) session.getAttribute("teacher");
			teacher.setTeacher_id(Integer.parseInt(teacher_id));
			teacher.setTeacher_num(teacher_num);
			teacher.setTeacher_name(teacher_name);
			teacher.setTeacher_gender(teacher_gender);
			teacher.setTeacher_telephone(teacher_telephone);
			teacher.setTeacher_password(teacher_password);
			teacherDAO.modify(teacher);
			return TEACHER_PROFILE;
		}
		return INDEX;
	}

	public String seeOtherStudent() {
		return INDEX;
	}

	public String seeOtherTeacher() throws NumberFormatException, ClassNotFoundException, SQLException {
		if (teacher_id != null) {
			Teacher teacher = teacherDAO.getById(Integer.parseInt(teacher_id));
			request.setAttribute("teacher", teacher);
			return TEACHER_PROFILE_LOOK;
		}
		return INDEX;
	}

	public String seeSelf() {
		String loginState = (String) session.getAttribute("loginState");
		boolean turnToHomePage = false;
		if (loginState != null) {
			if (loginState.equals("success")) {
				String userType = (String) session.getAttribute("userType");
				if (userType.equals("student")) {
					return STUDENT_PROFILE;
				} else {
					return TEACHER_PROFILE;
				}
			} else {
				turnToHomePage = true;
			}
		} else {
			turnToHomePage = true;
		}
		if (turnToHomePage) {
			return INDEX;
		}
		return SUCCESS;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setStudent_gender(String student_gender) {
		this.student_gender = student_gender;
	}

	public void setStudent_grade(String student_grade) {
		this.student_grade = student_grade;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public void setStudent_major(String student_major) {
		this.student_major = student_major;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public void setStudent_num(String student_num) {
		this.student_num = student_num;
	}

	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}

	public void setStudent_telphone(String student_telphone) {
		this.student_telphone = student_telphone;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void setTeacher_gender(String teacher_gender) {
		this.teacher_gender = teacher_gender;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public void setTeacher_num(String teacher_num) {
		this.teacher_num = teacher_num;
	}

	public void setTeacher_password(String teacher_password) {
		this.teacher_password = teacher_password;
	}

	public void setTeacher_telephone(String teacher_telphone) {
		this.teacher_telephone = teacher_telphone;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	private void removeStatus(HttpSession session) {
		session.removeAttribute("loginState");
		session.removeAttribute("student");
		session.removeAttribute("teacher");
	}
}
