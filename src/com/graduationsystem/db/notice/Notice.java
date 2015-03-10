package com.graduationsystem.db.notice;

import com.graduationsystem.db.teacher.Teacher;

public class Notice
{
	private int noticce_id;
	private int teacher_id;
	private String notice_title;
	private String notice_detail;
	private Teacher teacher;

	public Notice()
	{

	}

	public Notice(int noticce_id,int teacher_id,String notice_title,String notice_detail)
	{
		this.noticce_id = noticce_id;
		this.teacher_id = teacher_id;
		this.notice_title = notice_title;
		this.notice_detail = notice_detail;
	}

	public void setNoticce_id(int noticce_id)
	{
		this.noticce_id = noticce_id;
	}

	public int getNoticce_id()
	{
		return noticce_id;
	}

	public void setTeacher_id(int teacher_id)
	{
		this.teacher_id = teacher_id;
	}

	public int getTeacher_id()
	{
		return teacher_id;
	}

	public void setNotice_title(String notice_title)
	{
		this.notice_title = notice_title;
	}

	public String getNotice_title()
	{
		return notice_title;
	}

	public void setNotice_detail(String notice_detail)
	{
		this.notice_detail = notice_detail;
	}

	public String getNotice_detail()
	{
		return notice_detail;
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
		return noticce_id + "\t" + teacher_id + "\t" + notice_title + "\t" + notice_detail + "\t" + (this.teacher == null ? "" : "\t" + teacher);
	}
}
