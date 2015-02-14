package com.graduationsystem.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/i18ns"})
public class I18nServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		System.out.println(locale.getCountry());
		if ("US".equals(locale.getCountry())) {
			session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.CHINA);
		} else {
			locale.setDefault(Locale.US);
			session.setAttribute("WW_TRANS_I18N_LOCALE", Locale.US);
		}
		res.sendRedirect(req.getContextPath());
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
