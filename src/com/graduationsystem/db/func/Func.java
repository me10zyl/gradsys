package com.graduationsystem.db.func;

import java.util.ArrayList;
import com.graduationsystem.db.predom.*;

public class Func
{
	private int func_id;
	private String func_name;
	private ArrayList<Predom> predom = new ArrayList<Predom>();

	public Func()
	{

	}

	public Func(int func_id,String func_name)
	{
		this.func_id = func_id;
		this.func_name = func_name;
	}

	public void setFunc_id(int func_id)
	{
		this.func_id = func_id;
	}

	public int getFunc_id()
	{
		return func_id;
	}

	public void setFunc_name(String func_name)
	{
		this.func_name = func_name;
	}

	public String getFunc_name()
	{
		return func_name;
	}

	public void setPredom(ArrayList<Predom> predom)
	{
		this.predom = predom;
	}

	public ArrayList<Predom> getPredom()
	{
		return predom;
	}

	public String toString()
	{
		return func_id + "\t" + func_name + "\t" + (this.predom.size() == 0? "" : "\t" + predom);
	}
}
