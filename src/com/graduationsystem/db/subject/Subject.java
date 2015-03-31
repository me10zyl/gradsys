package com.graduationsystem.db.subject;

import java.util.ArrayList;

import com.graduationsystem.db.teacher.*;
import com.graduationsystem.db.duty.*;
import com.graduationsystem.db.student.*;

public class Subject
{
	private int subject_id;
	private int teacher_id;
	private String subject_title;
	private String subject_description;
	private Teacher teacher;
	private ArrayList<Duty> duty = new ArrayList<Duty>();
	private ArrayList<Student> student = new ArrayList<Student>();

	public Subject()
	{

	}

	public Subject(int subject_id,int teacher_id,String subject_title,String subject_description)
	{
		this.subject_id = subject_id;
		this.teacher_id = teacher_id;
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

	public void setTeacher_id(int teacher_id)
	{
		this.teacher_id = teacher_id;
	}

	public int getTeacher_id()
	{
		return teacher_id;
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

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public Teacher getTeacher()
	{
		return teacher;
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
		return subject_id + "\t" + teacher_id + "\t" + subject_title + "\t" + subject_description + "\t" + (this.teacher == null ? "" : "\t" + teacher) + (this.duty.size() == 0? "" : "\t" + duty) + (this.student.size() == 0? "" : "\t" + student);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.subject_id == ((Subject)obj).getSubject_id();
	}
	
}
