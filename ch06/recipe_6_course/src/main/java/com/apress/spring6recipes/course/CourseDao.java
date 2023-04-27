package com.apress.spring6recipes.course;

import java.util.List;

public interface CourseDao {

	Course store(Course course);
	void delete(Long courseId);
	Course findById(Long courseId);
	List<Course> findAll();
}
