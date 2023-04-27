package com.apress.spring6recipes.course.jpa;

import java.util.List;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;

import jakarta.persistence.EntityManagerFactory;


public class JpaCourseDao implements CourseDao {

	private final EntityManagerFactory entityManagerFactory;

	public JpaCourseDao(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public Course store(Course course) {
		var manager = entityManagerFactory.createEntityManager();
		try {
			manager.getTransaction().begin();
			var persisted = manager.merge(course);
			manager.getTransaction().commit();
			return persisted;
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public void delete(Long courseId) {
		var manager = entityManagerFactory.createEntityManager();
		try {
			manager.getTransaction().begin();
			var course = manager.getReference(Course.class, courseId);
			manager.remove(course);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public Course findById(Long courseId) {
		var manager = entityManagerFactory.createEntityManager();
		try {
			return manager.find(Course.class, courseId);
		} finally {
			manager.close();
		}
	}

	public List<Course> findAll() {
		var manager = entityManagerFactory.createEntityManager();
		try {
			return manager.createQuery("select course from Course course", Course.class).getResultList();
		} finally {
			manager.close();
		}
	}
}
