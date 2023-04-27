package com.apress.spring6recipes.course.jpa;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;

public class JpaCourseDao implements CourseDao {

	private final EntityManagerFactory entityManagerFactory
					= Persistence.createEntityManagerFactory("course");

	@Override
	public Course store(Course course) {
		var manager = entityManagerFactory.createEntityManager();
		var tx = manager.getTransaction();
		try {
			tx.begin();
			var persisted = manager.merge(course);
			tx.commit();
			return persisted;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(Long courseId) {
		var manager = entityManagerFactory.createEntityManager();
		var tx = manager.getTransaction();
		try {
			tx.begin();
			Course course = manager.find(Course.class, courseId);
			manager.remove(course);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	@Override
	public Course findById(Long courseId) {
		var manager = entityManagerFactory.createEntityManager();
		try {
			return manager.find(Course.class, courseId);
		} finally {
			manager.close();
		}
	}

	@Override
	public List<Course> findAll() {
		var manager = entityManagerFactory.createEntityManager();
		try {
			return manager.createQuery("select course from Course course", Course.class).getResultList();
		} finally {
			manager.close();
		}
	}
}
