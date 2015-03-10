package com.graduationsystem.action;

/**
 * ���ڴ��븴�ã�����������DB��������е���ͬ����
 * ������DB�������ĸ���
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

/**
 * @author ZyL
 *
 */
public abstract class DBMain<T>
{
	/**
	 * ����DB������඼��ͬӵ�еı���
	 */
	protected Connection con = null;
	protected PreparedStatement pst = null;
	protected ResultSet rst = null;
	
	/**
	 * ���PreparedStatement����
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException
	{
		//------������ݿ���---------------------
		Class.forName("com.mysql.jdbc.Driver");
		//------�����ݿ�����----------------------
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/graduationsystem", "root", "");
		//-------��װSQL���---------------------
		//String sql = "select * from users";
		 pst =  con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
		 return pst;
	}
	/**
	 * �ͷ���ݿ�����
	 * @throws SQLException
	 */
	public void realese() throws SQLException
	{
		if(rst != null)
		{
			rst.close();
		}
		if(pst != null)
		{
			pst.close();
		}
		if(con != null)
		{
			con.close();
		}

	}
	
	public abstract ArrayList<T> getAll() throws ClassNotFoundException,SQLException;
	
	public abstract T getById(int id) throws ClassNotFoundException,SQLException;
	
	public abstract T getDetailById(int id) throws ClassNotFoundException,SQLException;
	
	public abstract void add(T user) throws ClassNotFoundException,SQLException;
	
	public abstract void modify(T newUser) throws ClassNotFoundException,SQLException;
	
	public abstract void delete(int id) throws ClassNotFoundException,SQLException;
	
	public abstract T assemble(ResultSet rst) throws SQLException;
	
}
