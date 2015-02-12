package com.graduationsystem.db.predom;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;
import com.graduationsystem.db.func.*;
import com.graduationsystem.db.usergroup.*;

public class PredomDAO extends DBMain<Predom>
{
	public void add(Predom predom) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into predom(func_id) values (?)";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, predom.getFunc_id());
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

	public void modify(Predom newPredom) throws ClassNotFoundException, SQLException
	{
		String sql = "update predom set func_id=? where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, newPredom.getFunc_id());
		pst.setInt(2, newPredom.getUserGroup_id());
		pst.executeUpdate();
		realese();
	}

	public ArrayList<Predom> getAll() throws ClassNotFoundException, SQLException
	{
		String sql = "select * from predom";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Predom> predoms = new ArrayList<Predom>();
		while (rst.next())
		{
			predoms.add(assemble(rst));
		}
		realese();
		return predoms;
	}

	public Predom getById(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from predom where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Predom predom = null;
		if (rst.next())
		{
			predom= assemble(rst);
		}
		realese();
		return predom;
	}

	public Predom assemble(ResultSet rst) throws SQLException
	{
		Predom predom = new Predom(rst.getInt("userGroup_id"), rst.getInt("func_id"));
		return predom;
	}

	public Predom getDetailById(int id) throws ClassNotFoundException, SQLException
	{
		Predom predom = getById(id);
		setImportKeysMappingObject(predom, "func", "func_id", id);
		setImportKeysMappingObject(predom, "usergroup", "func_id", id);
		this.realese();
		return predom;
	}

	private void setImportKeysMappingObject(Predom predom,String tableName,String importKey,int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from predom join "+tableName+" on predom."+importKey+" = "+tableName+"."+importKey+" where userGroup_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------处理结果---------------------
		if (rst.next()) {
			if(tableName.equals("func"))
			{
				predom.setFunc(new FuncDAO().assemble(rst));
			}
			else if(tableName.equals("usergroup"))
			{
				predom.setUsergroup(new UsergroupDAO().assemble(rst));
			}
		}
	}
	
	private void setExportKeysMappingObject(Predom predom, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from predom," + tableName + " where predom."+exportKey+" = " + tableName + "."+exportKey+" and predom.userGroup_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {
			
		}
	}
}