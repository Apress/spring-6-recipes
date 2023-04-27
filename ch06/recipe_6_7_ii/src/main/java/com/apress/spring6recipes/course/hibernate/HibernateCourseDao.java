package com.apress.spring6recipes.course.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;

public class HibernateCourseDao implements CourseDao {

	private final SessionFactory sessionFactory;

	public HibernateCourseDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Course store(Course course) {
		var session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			if (course.getId() == null) {
				session.persist(course);
			} else {
				session.merge(course);
			}
			session.getTransaction().commit();
			return course;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void delete(Long courseId) {
		var session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			var course = session.getReference(Course.class, courseId);
			session.remove(course);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public Course findById(Long courseId) {
		try (var session = sessionFactory.openSession()) {
			return session.get(Course.class, courseId);
		}
	}

	public List<Course> findAll() {
		try (var session = sessionFactory.openSession()) {
			return session.createQuery("SELECT c FROM Course c", Course.class).list();
		}
	}

}
