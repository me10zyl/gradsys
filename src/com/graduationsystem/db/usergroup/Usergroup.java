package com.graduationsystem.db.usergroup;

import java.util.ArrayList;
import com.graduationsystem.db.predom.*;
import com.graduationsystem.db.student.*;
import com.graduationsystem.db.teacher.*;

public class Usergroup
{
	private int userGroup_id;
	private String userGroup_name;
	private ArrayList<Predom> predom = new ArrayList<Predom>();
	private ArrayList<Student> student = new ArrayList<Student>();
	private ArrayList<Teacher> teacher = new ArrayList<Teacher>();

	public Usergroup()
	{

	}

	public Usergroup(int userGroup_id,String userGroup_name)
	{
		this.userGroup_id = userGroup_id;
		this.userGroup_name = userGroup_name;
	}

	public void setUserGroup_id(int userGroup_id)
	{
		this.userGroup_id = userGroup_id;
	}

	public int getUserGroup_id()
	{
		return userGroup_id;
	}

	public void setUserGroup_name(String userGroup_name)
	{
		this.userGroup_name = userGroup_name;
	}

	public String getUserGroup_name()
	{
		return userGroup_name;
	}

	public void setPredom(ArrayList<Predom> predom)
	{
		this.predom = predom;
	}

	public ArrayList<Predom> getPredom()
	{
		return predom;
	}

	public void setStudent(ArrayList<Student> student)
	{
		this.student = student;
	}

	public ArrayList<Student> getStudent()
	{
		return student;
	}

	public void setTeacher(ArrayList<Teacher> teacher)
	{
		this.teacher = teacher;
	}

	public ArrayList<Teacher> getTeacher()
	{
		return teacher;
	}

	public String toString()
	{
		return userGroup_id + "\t" + userGroup_name + "\t" + (this.predom.size() == 0? "" : "\t" + predom) + (this.student.size() == 0? "" : "\t" + student) + (this.teacher.size() == 0? "" : "\t" + teacher);
	}
}
