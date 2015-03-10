package com.graduationsystem.action;

import java.util.Locale;
import java.util.Map;

import org.apache.catalina.ant.FindLeaksTask;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class I18nAction extends ActionSupport implements SessionAware,RequestAware {
	private Map<String, Object> session;
	private Map<String,Object> request;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String locale = ActionContext.getContext().getValueStack().findString("locale");
		if ("en_US".equals(locale)) {
			session.put("WW_TRANS_I18N_LOCALE", Locale.CHINA);
		} else {
			session.put("WW_TRANS_I18N_LOCALE", Locale.US);
		}
		request.put("refresh",true);
		return super.execute();
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
