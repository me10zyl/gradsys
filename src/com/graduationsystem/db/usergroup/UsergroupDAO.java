package com.graduationsystem.db.usergroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;
import com.graduationsystem.db.predom.*;
import com.graduationsystem.db.student.*;
import com.graduationsystem.db.teacher.*;

public class UsergroupDAO extends DBMain<Usergroup>
{
	public void add(Usergroup usergroup) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into usergroup(userGroup_name) values (?)";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, usergroup.getUserGroup_name());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from users where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Usergroup newUsergroup) throws ClassNotFoundException, SQLException
	{
		String sql = "update usergroup set userGroup_name=? where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, newUsergroup.getUserGroup_name());
		pst.setInt(2, newUsergroup.getUserGroup_id());
		pst.executeUpdate();
		realese();
	}

	public ArrayList<Usergroup> getAll() throws ClassNotFoundException, SQLException
	{
		String sql = "select * from usergroup";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Usergroup> usergroups = new ArrayList<Usergroup>();
		while (rst.next())
		{
			usergroups.add(assemble(rst));
		}
		realese();
		return usergroups;
	}

	public Usergroup getById(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from usergroup where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Usergroup usergroup = null;
		if (rst.next())
		{
			usergroup= assemble(rst);
		}
		realese();
		return usergroup;
	}

	public Usergroup assemble(ResultSet rst) throws SQLException
	{
		Usergroup usergroup = new Usergroup(rst.getInt("userGroup_id"), rst.getString("userGroup_name"));
		return usergroup;
	}

	public Usergroup getDetailById(int id) throws ClassNotFoundException, SQLException
	{
		Usergroup usergroup = getById(id);
		setExportKeysMappingObject(usergroup, "predom", "userGroup_id", id);
		setExportKeysMappingObject(usergroup, "student", "userGroup_id", id);
		setExportKeysMappingObject(usergroup, "teacher", "userGroup_id", id);
		this.realese();
		return usergroup;
	}

	private void setImportKeysMappingObject(Usergroup usergroup,String tableName,String importKey,int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from usergroup join "+tableName+" on usergroup."+importKey+" = "+tableName+"."+importKey+" where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------处理结果---------------------
		if (rst.next()) {
			
		}
	}
	
	private void setExportKeysMappingObject(Usergroup usergroup, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from usergroup," + tableName + " where usergroup."+exportKey+" = " + tableName + "."+exportKey+" and usergroup.userGroup_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {
			if(tableName.equals("predom"))
			{
				usergroup.getPredom().add(new PredomDAO().assemble(rst));
			}
			else if(tableName.equals("student"))
			{
				usergroup.getStudent().add(new StudentDAO().assemble(rst));
			}
			else if(tableName.equals("teacher"))
			{
				usergroup.getTeacher().add(new TeacherDAO().assemble(rst));
			}
		}
	}
}