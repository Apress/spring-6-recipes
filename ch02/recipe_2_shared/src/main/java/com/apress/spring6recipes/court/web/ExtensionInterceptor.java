package com.apress.spring6recipes.court.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ExtensionInterceptor implements HandlerInterceptor {

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
												 Object handler, ModelAndView modelAndView) throws Exception {
		String reportName = null;
		var reportDate = request.getParameter("date").replace("-", "_");
		var path = request.getServletPath();
		if (path.endsWith(".pdf")) {
			reportName = "ReservationSummary_" + reportDate + ".pdf";
		} else if (path.endsWith(".xlsx")) {
			reportName = "ReservationSummary_" + reportDate + ".xlsx";
		}
		if (reportName != null) {
			response.setHeader("Content-Disposition", "attachment; filename=" + reportName);
		}
	}
}
