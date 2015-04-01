package com.graduationsystem.db.student;

import java.util.ArrayList;

import com.graduationsystem.db.subject.*;
import com.graduationsystem.db.usergroup.*;

public class Student
{
	private int student_id;
	private int subject_id;
	private int userGroup_id;
	private String student_num;
	private String student_name;
	private String student_gender;
	private String student_grade;
	private String student_major;
	private String student_telphone;
	private String student_password;
	private Subject subject;
	private Usergroup usergroup;

	public Student()
	{

	}

	public Student(int student_id,int subject_id,int userGroup_id,String student_num,String student_name,String student_gender,String student_grade,String student_major,String student_telphone,String student_password)
	{
		this.student_id = student_id;
		this.subject_id = subject_id;
		this.userGroup_id = userGroup_id;
		this.student_num = student_num;
		this.student_name = student_name;
		this.student_gender = student_gender;
		this.student_grade = student_grade;
		this.student_major = student_major;
		this.student_telphone = student_telphone;
		this.student_password = student_password;
	}

	public void setStudent_id(int student_id)
	{
		this.student_id = student_id;
	}

	public int getStudent_id()
	{
		return student_id;
	}

	public void setSubject_id(int subject_id)
	{
		this.subject_id = subject_id;
	}

	public int getSubject_id()
	{
		return subject_id;
	}

	public void setUserGroup_id(int userGroup_id)
	{
		this.userGroup_id = userGroup_id;
	}

	public int getUserGroup_id()
	{
		return userGroup_id;
	}

	public void setStudent_num(String student_num)
	{
		this.student_num = student_num;
	}

	public String getStudent_num()
	{
		return student_num;
	}

	public void setStudent_name(String student_name)
	{
		this.student_name = student_name;
	}

	public String getStudent_name()
	{
		return student_name;
	}

	public void setStudent_gender(String student_gender)
	{
		this.student_gender = student_gender;
	}

	public String getStudent_gender()
	{
		return student_gender;
	}

	public void setStudent_grade(String student_grade)
	{
		this.student_grade = student_grade;
	}

	public String getStudent_grade()
	{
		return student_grade;
	}

	public void setStudent_major(String student_major)
	{
		this.student_major = student_major;
	}

	public String getStudent_major()
	{
		return student_major;
	}

	public void setStudent_telphone(String student_telphone)
	{
		this.student_telphone = student_telphone;
	}

	public String getStudent_telphone()
	{
		return student_telphone;
	}

	public void setStudent_password(String student_password)
	{
		this.student_password = student_password;
	}

	public String getStudent_password()
	{
		return student_password;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public Subject getSubject()
	{
		return subject;
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
		return student_id + "\t" + subject_id + "\t" + userGroup_id + "\t" + student_num + "\t" + student_name + "\t" + student_gender + "\t" + student_grade + "\t" + student_major + "\t" + student_telphone + "\t" + student_password + "\t" + (this.subject == null ? "" : "\t" + subject) + (this.usergroup == null ? "" : "\t" + usergroup);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.student_id == ((Student)obj).getStudent_id();
	}
}
