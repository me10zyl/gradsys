package com.graduationsystem.db.subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;
import com.graduationsystem.db.duty.*;
import com.graduationsystem.db.student.*;
import com.graduationsystem.db.teacher.Teacher;
import com.graduationsystem.db.teacher.TeacherDAO;

public class SubjectDAO extends DBMain<Subject> {
	public void add(Subject subject) throws ClassNotFoundException, SQLException {
		String sql = "insert into subject(subject_title,subject_description) values (?,?)";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, subject.getSubject_title());
		pst.setString(2, subject.getSubject_description());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		String sql = "delete from duty where subject_id=?";
		String sql2 = "delete from subject where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		pst = this.getPreparedStatement(sql2);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Subject newSubject) throws ClassNotFoundException, SQLException {
		String sql = "update subject set subject_title=?,subject_description=? where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, newSubject.getSubject_title());
		pst.setString(2, newSubject.getSubject_description());
		pst.setInt(3, newSubject.getSubject_id());
		pst.executeUpdate();
		realese();
	}

	// public ArrayList<Teacher> getDutyTeacherBySubjectId(int id) throws
	// ClassNotFoundException, SQLException {
	// TeacherDAO teacherDAO = new TeacherDAO();
	// Subject subject_detail = getDetailById(id);
	// ArrayList<Duty> arr_duty = subject_detail.getDuty();
	// ArrayList<Teacher> arr_teacher = new ArrayList<Teacher>();
	// for (Duty duty : arr_duty) {
	// arr_teacher.add(teacherDAO.getById(duty.getTeacher_id()));
	// }
	// return arr_teacher;
	// }
	public ArrayList<Teacher> getDutyTeachersBySubjectId(int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from teacher where teacher_id in (select teacher_id from duty where subject_id = ?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		while (rst.next()) {
			teachers.add(new TeacherDAO().assemble(rst));
		}
		realese();
		return teachers;
	}

	public ArrayList<Subject> getAll() throws ClassNotFoundException, SQLException {
		String sql = "select * from subject";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		while (rst.next()) {
			subjects.add(assemble(rst));
		}
		realese();
		return subjects;
	}

	public Subject getById(int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from subject where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Subject subject = null;
		if (rst.next()) {
			subject = assemble(rst);
		}
		realese();
		return subject;
	}

	public Subject assemble(ResultSet rst) throws SQLException {
		Subject subject = new Subject(rst.getInt("subject_id"), rst.getString("subject_title"), rst.getString("subject_description"));
		return subject;
	}

	public Subject getDetailById(int id) throws ClassNotFoundException, SQLException {
		Subject subject = getById(id);
		setExportKeysMappingObject(subject, "duty", "subject_id", id);
		setExportKeysMappingObject(subject, "student", "subject_id", id);
		this.realese();
		return subject;
	}

	private void setImportKeysMappingObject(Subject subject, String tableName, String importKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from subject join " + tableName + " on subject." + importKey + " = " + tableName + "." + importKey + " where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------������---------------------
		if (rst.next()) {
		}
	}

	private void setExportKeysMappingObject(Subject subject, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from subject," + tableName + " where subject." + exportKey + " = " + tableName + "." + exportKey + " and subject.subject_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {
			if (tableName.equals("duty")) {
				subject.getDuty().add(new DutyDAO().assemble(rst));
			} else if (tableName.equals("student")) {
				subject.getStudent().add(new StudentDAO().assemble(rst));
			}
		}
	}
}