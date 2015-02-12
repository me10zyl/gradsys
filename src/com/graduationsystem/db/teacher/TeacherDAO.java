package com.graduationsystem.db.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;

import com.graduationsystem.db.subject.Subject;
import com.graduationsystem.db.subject.SubjectDAO;
import com.graduationsystem.db.usergroup.*;

import com.graduationsystem.db.duty.*;

public class TeacherDAO extends DBMain<Teacher> {
	public void add(Teacher teacher) throws ClassNotFoundException, SQLException {
		String sql = "insert into teacher(userGroup_id,teacher_num,teacher_name,teacher_gender,teacher_telephone,teacher_password) values (?,?,?,?,?,?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, teacher.getUserGroup_id());
		pst.setString(2, teacher.getTeacher_num());
		pst.setString(3, teacher.getTeacher_name());
		pst.setString(4, teacher.getTeacher_gender());
		pst.setString(5, teacher.getTeacher_telephone());
		pst.setString(6, teacher.getTeacher_password());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		String sql = "delete from users where teacher_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Teacher newTeacher) throws ClassNotFoundException, SQLException {
		String sql = "update teacher set userGroup_id=?,teacher_num=?,teacher_name=?,teacher_gender=?,teacher_telephone=?,teacher_password=? where teacher_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newTeacher.getUserGroup_id());
		pst.setString(2, newTeacher.getTeacher_num());
		pst.setString(3, newTeacher.getTeacher_name());
		pst.setString(4, newTeacher.getTeacher_gender());
		pst.setString(5, newTeacher.getTeacher_telephone());
		pst.setString(6, newTeacher.getTeacher_password());
		pst.setInt(7, newTeacher.getTeacher_id());
		pst.executeUpdate();
		realese();
	}

	public Teacher getByNum(String num) throws ClassNotFoundException, SQLException {
		String sql = "select * from teacher where teacher_num=?";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, num);
		rst = pst.executeQuery();
		Teacher teacher = null;
		if (rst.next()) {
			teacher = assemble(rst);
		}
		realese();
		return teacher;
	}

	public ArrayList<Teacher> getAll() throws ClassNotFoundException, SQLException {
		String sql = "select * from teacher";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		while (rst.next()) {
			teachers.add(assemble(rst));
		}
		realese();
		return teachers;
	}

	public ArrayList<Subject> getSubjectsByTeacherId(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from subject where subject_id in (select subject_id from duty where teacher_id = ?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		while (rst.next()) {
			subjects.add(new SubjectDAO().assemble(rst));
		}
		realese();
		return subjects;
	}
	
	public Teacher getById(int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from teacher where teacher_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Teacher teacher = null;
		if (rst.next()) {
			teacher = assemble(rst);
		}
		realese();
		return teacher;
	}

	public Teacher assemble(ResultSet rst) throws SQLException {
		Teacher teacher = new Teacher(rst.getInt("teacher_id"), rst.getInt("userGroup_id"), rst.getString("teacher_num"), rst.getString("teacher_name"), rst.getString("teacher_gender"), rst.getString("teacher_telephone"), rst.getString("teacher_password"));
		return teacher;
	}

	public Teacher getDetailById(int id) throws ClassNotFoundException, SQLException {
		Teacher teacher = getById(id);
		setExportKeysMappingObject(teacher, "duty", "teacher_id", id);
		setImportKeysMappingObject(teacher, "usergroup", "userGroup_id", id);
		this.realese();
		return teacher;
	}

	private void setImportKeysMappingObject(Teacher teacher, String tableName, String importKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from teacher join " + tableName + " on teacher." + importKey + " = " + tableName + "." + importKey + " where teacher_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------������---------------------
		if (rst.next()) {
			if (tableName.equals("usergroup")) {
				teacher.setUsergroup(new UsergroupDAO().assemble(rst));
			}
		}
	}

	private void setExportKeysMappingObject(Teacher teacher, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from teacher," + tableName + " where teacher." + exportKey + " = " + tableName + "." + exportKey + " and teacher.teacher_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {
			if (tableName.equals("duty")) {
				teacher.getDuty().add(new DutyDAO().assemble(rst));
			}
		}
	}
}