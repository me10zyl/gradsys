package com.graduationsystem.db.func;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.graduationsystem.db.DBMain;
import java.util.ArrayList;
import com.graduationsystem.db.predom.*;

public class FuncDAO extends DBMain<Func>
{
	public void add(Func func) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into func(func_name) values (?)";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, func.getFunc_name());
		pst.executeUpdate();
		realese();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from users where func_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		realese();
	}

	public void modify(Func newFunc) throws ClassNotFoundException, SQLException
	{
		String sql = "update func set func_name=? where func_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setString(1, newFunc.getFunc_name());
		pst.setInt(2, newFunc.getFunc_id());
		pst.executeUpdate();
		realese();
	}

	public ArrayList<Func> getAll() throws ClassNotFoundException, SQLException
	{
		String sql = "select * from func";
		pst = this.getPreparedStatement(sql);
		rst = pst.executeQuery();
		ArrayList<Func> funcs = new ArrayList<Func>();
		while (rst.next())
		{
			funcs.add(assemble(rst));
		}
		realese();
		return funcs;
	}

	public Func getById(int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from func where func_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		Func func = null;
		if (rst.next())
		{
			func= assemble(rst);
		}
		realese();
		return func;
	}

	public Func assemble(ResultSet rst) throws SQLException
	{
		Func func = new Func(rst.getInt("func_id"), rst.getString("func_name"));
		return func;
	}

	public Func getDetailById(int id) throws ClassNotFoundException, SQLException
	{
		Func func = getById(id);
		setExportKeysMappingObject(func, "predom", "func_id", id);
		this.realese();
		return func;
	}

	private void setImportKeysMappingObject(Func func,String tableName,String importKey,int id) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from func join "+tableName+" on func."+importKey+" = "+tableName+"."+importKey+" where func_id=?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		// ---------处理结果---------------------
		if (rst.next()) {
			
		}
	}
	
	private void setExportKeysMappingObject(Func func, String tableName, String exportKey, int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from func," + tableName + " where func."+exportKey+" = " + tableName + "."+exportKey+" and func.func_id = ?";
		pst = this.getPreparedStatement(sql);
		pst.setInt(1, id);
		rst = pst.executeQuery();
		while (rst.next()) {
			if(tableName.equals("predom"))
			{
				func.getPredom().add(new PredomDAO().assemble(rst));
			}
		}
	}
}