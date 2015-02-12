package com.graduationsystem.db.duty;

import java.util.ArrayList;
import com.graduationsystem.db.subject.*;
import com.graduationsystem.db.teacher.*;

public class Duty
{
	private int subject_id;
	private int teacher_id;
	private Subject subject;
	private Teacher teacher;

	public Duty()
	{

	}

	public Duty(int subject_id,int teacher_id)
	{
		this.subject_id = subject_id;
		this.teacher_id = teacher_id;
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

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public String toString()
	{
		return subject_id + "\t" + teacher_id + "\t" + (this.subject == null ? "" : "\t" + subject) + (this.teacher == null ? "" : "\t" + teacher);
	}
}
