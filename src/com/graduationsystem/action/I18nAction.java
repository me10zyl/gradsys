package com.graduationsystem.action;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class I18nAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Locale locale = (Locale) session.get("WW_TRANS_I18N_LOCALE");
		if ("US".equals(locale.getCountry())) {
			session.put("WW_TRANS_I18N_LOCALE", Locale.CHINA);
		} else {
			locale.setDefault(Locale.US);
			session.put("WW_TRANS_I18N_LOCALE", Locale.US);
		}
		return super.execute();
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
