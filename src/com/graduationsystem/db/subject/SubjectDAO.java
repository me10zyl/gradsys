package com.graduationsystem.db.subject;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.graduationsystem.db.DBMain;

import java.util.ArrayList;

import com.graduationsystem.db.teacher.*;
import com.graduationsystem.db.duty.*;
import com.graduationsystem.db.student.*;

public class SubjectDAO extends DBMain<Subject> {
	public void add(Subject subject) throws ClassNotFoundException, SQLException {
		String sql = "insert into subject(teacher_id,subject_title,subject_description) values (?,?,?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, subject.getTeacher_id());
		pst.setString(2, subject.getSubject_title());
		pst.setString(3, subject.getSubject_description());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		String sql = "delete from subject where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Subject newSubject) throws ClassNotFoundException, SQLException {
		String sql = "update subject set teacher_id=?,subject_title=?,subject_description=? where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newSubject.getTeacher_id());
		pst.setString(2, newSubject.getSubject_title());
		pst.setString(3, newSubject.getSubject_description());
		pst.setInt(4, newSubject.getSubject_id());
		pst.executeUpdate();
		realese();
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
	
	public ArrayList<Student> getStudentsWhoChooseBySubjectId(int subject_id) throws ClassNotFoundException, SQLException {
		String sql = "select * from student where subject_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, subject_id);
		rst = pst.executeQuery();
		ArrayList<Student> students = new ArrayList<Student>();
		while (rst.next()) {
			students.add(new StudentDAO().assemble(rst));
		}
		realese();
		return students;
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
		Subject subject = new Subject(rst.getInt("subject_id"), rst.getInt("teacher_id"), rst.getString("subject_title"), rst.getString("subject_description"));
		return subject;
	}

	public Subject getDetailById(int id) throws ClassNotFoundException, SQLException {
		Subject subject = getById(id);
		setExportKeysMappingObject(subject, "duty", "subject_id", id);
		setExportKeysMappingObject(subject, "student", "subject_id", id);
		setImportKeysMappingObject(subject, "teacher", "teacher_id", id);
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
			if (tableName.equals("teacher")) {
				subject.setTeacher(new TeacherDAO().assemble(rst));
			}
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
}