package com.graduationsystem.db.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;
import com.graduationsystem.db.subject.*;
import com.graduationsystem.db.usergroup.*;

public class StudentDAO extends DBMain<Student> {
	public void add(Student student) throws ClassNotFoundException, SQLException {
		String sql = "insert into student(subject_id,userGroup_id,student_num,student_name,student_gender,student_grade,student_major,student_telphone,student_password) values (?,?,?,?,?,?,?,?,?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, student.getSubject_id());
		pst.setInt(2, student.getUserGroup_id());
		pst.setString(3, student.getStudent_num());
		pst.setString(4, student.getStudent_name());
		pst.setString(5, student.getStudent_gender());
		pst.setString(6, student.getStudent_grade());
		pst.setString(7, student.getStudent_major());
		pst.setString(8, student.getStudent_telphone());
		pst.setString(9, student.getStudent_password());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		String sql = "delete from users where student_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}
	
	public void modify(Student newStudent) throws ClassNotFoundException, SQLException {
		String sql = "update student set subject_id=?,userGroup_id=?,student_num=?,student_name=?,student_gender=?,student_grade=?,student_major=?,student_telphone=?,student_password=? where student_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newStudent.getSubject_id());
		pst.setInt(2, newStudent.getUserGroup_id());
		pst.setString(3, newStudent.getStudent_num());
		pst.setString(4, newStudent.getStudent_name());
		pst.setString(5, newStudent.getStudent_gender());
		pst.setString(6, newStudent.getStudent_grade());
		pst.setString(7, newStudent.getStudent_major());
		pst.setString(8, newStudent.getStudent_telphone());
		pst.setString(9, newStudent.getStudent_password());
		pst.setInt(10, newStudent.getStudent_id());
		pst.executeUpdate();
		realese();
	}
	
	public void modifyWithOutSubject(Student newStudent) throws ClassNotFoundException, SQLException {
		String sql = "update student set userGroup_id=?,student_num=?,student_name=?,student_gender=?,student_grade=?,student_major=?,student_telphone=?,student_password=? where student_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newStudent.getUserGroup_id());
		pst.setString(2, newStudent.getStudent_num());
		pst.setString(3, newStudent.getStudent_name());
		pst.setString(4, newStudent.getStudent_gender());
		pst.setString(5, newStudent.getStudent_grade());
		pst.setString(6, newStudent.getStudent_major());
		pst.setString(7, newStudent.getStudent_telphone());
		pst.setString(8, newStudent.getStudent_password());
		pst.setInt(9, newStudent.getStudent_id());
		pst.executeUpdate();
		realese();
	}

	public ArrayList<Student> getAll() throws ClassNotFoundException, SQLException {
		String sql = "select * from student";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Student> students = new ArrayList<Student>();
		while (rst.next()) {
			students.add(assemble(rst));
		}
		realese();
		return students;
	}

	public Student getById(int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from student where student_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Student student = null;
		if (rst.next()) {
			student = assemble(rst);
		}
		realese();
		return student;
	}

	public Student assemble(ResultSet rst) throws SQLException {
		Student student = new Student(rst.getInt("student_id"), rst.getInt("subject_id"), rst.getInt("userGroup_id"), rst.getString("student_num"), rst.getString("student_name"), rst.getString("student_gender"), rst.getString("student_grade"), rst.getString("student_major"), rst.getString("student_telphone"), rst.getString("student_password"));
		return student;
	}

	public Student getDetailById(int id) throws ClassNotFoundException, SQLException {
		Student student = getById(id);
		setImportKeysMappingObject(student, "subject", "subject_id", id);
		setImportKeysMappingObject(student, "usergroup", "subject_id", id);
		this.realese();
		return student;
	}

	public Student getByNum(String num) throws ClassNotFoundException, SQLException {
		String sql = "select * from student where student_num=?";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, num);
		rst = pst.executeQuery();
		Student student = null;
		if (rst.next()) {
			student = assemble(rst);
		}
		realese();
		return student;
	}

	private void setImportKeysMappingObject(Student student, String tableName, String importKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from student join " + tableName + " on student." + importKey + " = " + tableName + "." + importKey + " where student_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------������---------------------
		if (rst.next()) {
			if (tableName.equals("subject")) {
				student.setSubject(new SubjectDAO().assemble(rst));
			} else if (tableName.equals("usergroup")) {
				student.setUsergroup(new UsergroupDAO().assemble(rst));
			}
		}
	}

	private void setExportKeysMappingObject(Student student, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from student," + tableName + " where student." + exportKey + " = " + tableName + "." + exportKey + " and student.student_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {

		}
	}
}