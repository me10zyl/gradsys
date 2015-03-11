package com.graduationsystem.db;

/**
 * 用于代码复用，即放置所有DB表操作类中的相同代码
 * 是所有DB表操作类的父类
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
	 * 所有DB表操作类都共同拥有的变量
	 */
	protected Connection con = null;
	protected PreparedStatement pst = null;
	protected ResultSet rst = null;
	
	/**
	 * 获得PreparedStatement对象
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException
	{
		//------加载数据库驱动---------------------
		Class.forName("com.mysql.jdbc.Driver");
		//------获得数据库连接----------------------
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:10000/graduationsystem", "root", "");
		//-------封装SQL语句---------------------
		//String sql = "select * from users";
		 pst =  con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
		 return pst;
	}
	/**
	 * 释放数据库连接
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
