package com.apress.spring6recipes.course;

import java.time.LocalDate;

import com.apress.spring6recipes.course.hibernate.HibernateCourseDao;

public class Main {

	public static void main(String[] args) throws Exception {

		var courseDao = new HibernateCourseDao();

		var course = new Course();
		course.setTitle("Core Spring");
		course.setBeginDate(LocalDate.of(2007, 8, 1));
		course.setEndDate(LocalDate.of(2007, 9, 1));
		course.setFee(1000);

		System.out.println("\nCourse before persisting");
		System.out.println(course);

		courseDao.store(course);

		System.out.println("\nCourse after persisting");
		System.out.println(course);

		var courseId = course.getId();
		var courseFromDb = courseDao.findById(courseId);

		System.out.println("\nCourse fresh from database");
		System.out.println(courseFromDb);

		courseDao.delete(courseId);

		System.exit(0);
	}

}
