package com.apress.spring6recipes.course.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;

public class JpaCourseDao implements CourseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Course store(Course course) {
		return entityManager.merge(course);
	}

	@Override
	@Transactional
	public void delete(Long courseId) {
		Course course = entityManager.find(Course.class, courseId);
		entityManager.remove(course);
	}

	@Override
	@Transactional(readOnly = true)
	public Course findById(Long courseId) {
		return entityManager.find(Course.class, courseId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
		return query.getResultList();
	}
}
