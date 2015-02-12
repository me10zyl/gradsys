package com.graduationsystem.db.teacher;

import java.util.ArrayList;
import com.graduationsystem.db.usergroup.*;

import com.graduationsystem.db.duty.*;

public class Teacher
{
	private int teacher_id;
	private int userGroup_id;
	private String teacher_num;
	private String teacher_name;
	private String teacher_gender;
	private String teacher_telephone;
	private String teacher_password;
	private Usergroup usergroup;
	private ArrayList<Duty> duty = new ArrayList<Duty>();

	public Teacher()
	{

	}

	public Teacher(int teacher_id,int userGroup_id,String teacher_num,String teacher_name,String teacher_gender,String teacher_telephone,String teacher_password)
	{
		this.teacher_id = teacher_id;
		this.userGroup_id = userGroup_id;
		this.teacher_num = teacher_num;
		this.teacher_name = teacher_name;
		this.teacher_gender = teacher_gender;
		this.teacher_telephone = teacher_telephone;
		this.teacher_password = teacher_password;
	}

	public void setTeacher_id(int teacher_id)
	{
		this.teacher_id = teacher_id;
	}

	public int getTeacher_id()
	{
		return teacher_id;
	}

	public void setUserGroup_id(int userGroup_id)
	{
		this.userGroup_id = userGroup_id;
	}

	public int getUserGroup_id()
	{
		return userGroup_id;
	}

	public void setTeacher_num(String teacher_num)
	{
		this.teacher_num = teacher_num;
	}

	public String getTeacher_num()
	{
		return teacher_num;
	}

	public void setTeacher_name(String teacher_name)
	{
		this.teacher_name = teacher_name;
	}

	public String getTeacher_name()
	{
		return teacher_name;
	}

	public void setTeacher_gender(String teacher_gender)
	{
		this.teacher_gender = teacher_gender;
	}

	public String getTeacher_gender()
	{
		return teacher_gender;
	}

	public void setTeacher_telephone(String teacher_telephone)
	{
		this.teacher_telephone = teacher_telephone;
	}

	public String getTeacher_telephone()
	{
		return teacher_telephone;
	}

	public void setTeacher_password(String teacher_password)
	{
		this.teacher_password = teacher_password;
	}

	public String getTeacher_password()
	{
		return teacher_password;
	}

	public void setUsergroup(Usergroup usergroup)
	{
		this.usergroup = usergroup;
	}

	public Usergroup getUsergroup()
	{
		return usergroup;
	}

	public void setDuty(ArrayList<Duty> duty)
	{
		this.duty = duty;
	}

	public ArrayList<Duty> getDuty()
	{
		return duty;
	}

	public String toString()
	{
		return teacher_id + "\t" + userGroup_id + "\t" + teacher_num + "\t" + teacher_name + "\t" + teacher_gender + "\t" + teacher_telephone + "\t" + teacher_password + "\t" + (this.usergroup == null ? "" : "\t" + usergroup) + (this.duty.size() == 0? "" : "\t" + duty);
	}
}
