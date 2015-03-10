package com.graduationsystem.db.notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.graduationsystem.db.DBMain;
import com.graduationsystem.db.teacher.TeacherDAO;

public class NoticeDAO extends DBMain<Notice>
{
	public void add(Notice notice) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into notice(teacher_id,notice_title,notice_detail) values (?,?,?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, notice.getTeacher_id());
		pst.setString(2, notice.getNotice_title());
		pst.setString(3, notice.getNotice_detail());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from notice where noticce_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Notice newNotice) throws ClassNotFoundException, SQLException
	{
		String sql = "update notice set teacher_id=?,notice_title=?,notice_detail=? where noticce_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newNotice.getTeacher_id());
		pst.setString(2, newNotice.getNotice_title());
		pst.setString(3, newNotice.getNotice_detail());
		pst.setInt(4, newNotice.getNoticce_id());
		pst.executeUpdate();
		realese();
	}

	public ArrayList<Notice> getAll() throws ClassNotFoundException, SQLException
	{
		String sql = "select * from notice";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Notice> notices = new ArrayList<Notice>();
		while (rst.next())
		{
			notices.add(assemble(rst));
		}
		realese();
		return notices;
	}

	public Notice getById(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from notice where noticce_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Notice notice = null;
		if (rst.next())
		{
			notice= assemble(rst);
		}
		realese();
		return notice;
	}

	public Notice assemble(ResultSet rst) throws SQLException
	{
		Notice notice = new Notice(rst.getInt("noticce_id"), rst.getInt("teacher_id"), rst.getString("notice_title"), rst.getString("notice_detail"));
		return notice;
	}

	public Notice getDetailById(int id) throws ClassNotFoundException, SQLException
	{
		Notice notice = getById(id);
		setImportKeysMappingObject(notice, "teacher", "teacher_id", id);
		this.realese();
		return notice;
	}

	private void setImportKeysMappingObject(Notice notice,String tableName,String importKey,int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from notice join "+tableName+" on notice."+importKey+" = "+tableName+"."+importKey+" where noticce_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------������---------------------
		if (rst.next()) {
			if(tableName.equals("teacher"))
			{
				notice.setTeacher(new TeacherDAO().assemble(rst));
			}
		}
	}
	
	private void setExportKeysMappingObject(Notice notice, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from notice," + tableName + " where notice."+exportKey+" = " + tableName + "."+exportKey+" and notice.noticce_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {
			
		}
	}
}