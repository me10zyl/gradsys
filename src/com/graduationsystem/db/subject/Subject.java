package com.graduationsystem.db.subject;

import java.util.ArrayList;
import com.graduationsystem.db.duty.*;
import com.graduationsystem.db.student.*;

public class Subject
{
	private int subject_id;
	private String subject_title;
	private String subject_description;
	private ArrayList<Duty> duty = new ArrayList<Duty>();
	private ArrayList<Student> student = new ArrayList<Student>();

	public Subject()
	{

	}

	public Subject(int subject_id,String subject_title,String subject_description)
	{
		this.subject_id = subject_id;
		this.subject_title = subject_title;
		this.subject_description = subject_description;
	}

	public void setSubject_id(int subject_id)
	{
		this.subject_id = subject_id;
	}

	public int getSubject_id()
	{
		return subject_id;
	}

	public void setSubject_title(String subject_title)
	{
		this.subject_title = subject_title;
	}

	public String getSubject_title()
	{
		return subject_title;
	}

	public void setSubject_description(String subject_description)
	{
		this.subject_description = subject_description;
	}

	public String getSubject_description()
	{
		return subject_description;
	}

	public void setDuty(ArrayList<Duty> duty)
	{
		this.duty = duty;
	}

	public ArrayList<Duty> getDuty()
	{
		return duty;
	}

	public void setStudent(ArrayList<Student> student)
	{
		this.student = student;
	}

	public ArrayList<Student> getStudent()
	{
		return student;
	}

	public String toString()
	{
		return subject_id + "\t" + subject_title + "\t" + subject_description + "\t" + (this.duty.size() == 0? "" : "\t" + duty) + (this.student.size() == 0? "" : "\t" + student);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.subject_id == ((Subject)obj).getSubject_id();
	}
}
