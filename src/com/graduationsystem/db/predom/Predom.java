package com.graduationsystem.db.predom;

import java.util.ArrayList;
import com.graduationsystem.db.func.*;
import com.graduationsystem.db.usergroup.*;

public class Predom
{
	private int userGroup_id;
	private int func_id;
	private Func func;
	private Usergroup usergroup;

	public Predom()
	{

	}

	public Predom(int userGroup_id,int func_id)
	{
		this.userGroup_id = userGroup_id;
		this.func_id = func_id;
	}

	public void setUserGroup_id(int userGroup_id)
	{
		this.userGroup_id = userGroup_id;
	}

	public int getUserGroup_id()
	{
		return userGroup_id;
	}

	public void setFunc_id(int func_id)
	{
		this.func_id = func_id;
	}

	public int getFunc_id()
	{
		return func_id;
	}

	public void setFunc(Func func)
	{
		this.func = func;
	}

	public Func getFunc()
	{
		return func;
	}

	public void setUsergroup(Usergroup usergroup)
	{
		this.usergroup = usergroup;
	}

	public Usergroup getUsergroup()
	{
		return usergroup;
	}

	public String toString()
	{
		return userGroup_id + "\t" + func_id + "\t" + (this.func == null ? "" : "\t" + func) + (this.usergroup == null ? "" : "\t" + usergroup);
	}
}
