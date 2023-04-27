package com.apress.spring6recipes.course.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository("courseDao")
public class JpaCourseDao implements CourseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Course store(Course course) {
		return entityManager.merge(course);
	}

	@Transactional
	public void delete(Long courseId) {
		var course = entityManager.getReference(Course.class, courseId);
		entityManager.remove(course);
	}

	@Transactional(readOnly = true)
	public Course findById(Long courseId) {
		return entityManager.find(Course.class, courseId);
	}

	@Transactional(readOnly = true)
	public List<Course> findAll() {
		return entityManager.createQuery("select c from Course c", Course.class).getResultList();
	}
}
