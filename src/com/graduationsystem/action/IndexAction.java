package com.graduationsystem.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	@Override
	public String execute() throws Exception {
		String loginState = (String) session.get("loginState");
		String userType = (String) session.get("userType");
		if(userType == null || loginState == null || !loginState.equals("success"))
		{
			return super.execute();
		}
		if (userType.equals("student")) {
			return UserAction.STUDENT_LOGINED;
		} else {
			return UserAction.TEACHER_LOGINED;
		}
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
}
