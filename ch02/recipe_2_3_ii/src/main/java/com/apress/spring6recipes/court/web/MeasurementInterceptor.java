package com.apress.spring6recipes.court.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MeasurementInterceptor implements HandlerInterceptor {

	private static final String NAME = "MeasurementInterceptor.TIMER";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
													 Object handler) {
		var sw = new StopWatch();
		sw.start();
		request.setAttribute(NAME, sw);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
												 Object handler, ModelAndView modelAndView) {
		var timer = (StopWatch) request.getAttribute(NAME);
		timer.stop();
		modelAndView.addObject("processingTime", timer.getTotalTimeMillis());
	}
}
