package com.graduationsystem.db.duty;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;
import com.graduationsystem.db.subject.*;
import com.graduationsystem.db.teacher.*;

public class DutyDAO extends DBMain<Duty> {
	public void add(Duty duty) throws ClassNotFoundException, SQLException {
		String sql = "insert into duty(subject_id,teacher_id) values (?,?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, duty.getSubject_id());
		pst.setInt(2, duty.getTeacher_id());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		String sql = "delete from duty where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}
	
	public void deleteBySubjectIdAndTeacherId(int subject_id,int teacher_id) throws ClassNotFoundException, SQLException {
		String sql = "delete from duty where subject_id=? and teacher_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, subject_id);
		pst.setInt(2, teacher_id);
		pst.executeUpdate();
		realese();
	}

	public void deleteByTeacherId(int id) throws ClassNotFoundException, SQLException {
		String sql = "delete from duty where teacher_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Duty newDuty) throws ClassNotFoundException, SQLException {
		String sql = "update duty set teacher_id=? where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newDuty.getTeacher_id());
		pst.setInt(2, newDuty.getSubject_id());
		pst.executeUpdate();
		realese();
	}

	public ArrayList<Duty> getAll() throws ClassNotFoundException, SQLException {
		String sql = "select * from duty";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Duty> dutys = new ArrayList<Duty>();
		while (rst.next()) {
			dutys.add(assemble(rst));
		}
		realese();
		return dutys;
	}

	public Duty getById(int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from duty where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Duty duty = null;
		if (rst.next()) {
			duty = assemble(rst);
		}
		realese();
		return duty;
	}

	public Duty assemble(ResultSet rst) throws SQLException {
		Duty duty = new Duty(rst.getInt("subject_id"), rst.getInt("teacher_id"));
		return duty;
	}

	public Duty getDetailById(int id) throws ClassNotFoundException, SQLException {
		Duty duty = getById(id);
		setImportKeysMappingObject(duty, "subject", "subject_id", id);
		setImportKeysMappingObject(duty, "teacher", "subject_id", id);
		this.realese();
		return duty;
	}

	private void setImportKeysMappingObject(Duty duty, String tableName, String importKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from duty join " + tableName + " on duty." + importKey + " = " + tableName + "." + importKey + " where subject_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------������---------------------
		if (rst.next()) {
			if (tableName.equals("subject")) {
				duty.setSubject(new SubjectDAO().assemble(rst));
			} else if (tableName.equals("teacher")) {
				duty.setTeacher(new TeacherDAO().assemble(rst));
			}
		}
	}

	private void setExportKeysMappingObject(Duty duty, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from duty," + tableName + " where duty." + exportKey + " = " + tableName + "." + exportKey + " and duty.subject_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {

		}
	}
}