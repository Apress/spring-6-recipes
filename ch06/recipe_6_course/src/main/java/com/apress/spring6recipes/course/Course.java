package com.apress.spring6recipes.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "COURSE")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", length = 100, nullable = false)
	private String title;

	@Column(name = "BEGIN_DATE")
	private LocalDate beginDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "FEE")
	private int fee;

	public Course() {
	}

	public Course(String title, LocalDate startDate, LocalDate endDate, int fee) {
		this.title = title;
		this.beginDate = startDate;
		this.endDate = endDate;
		this.fee = fee;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		var fmt = "Course [id=%d, title='%s', beginDate=%tF, endDate=%tF, fee=%d]";
		return String.format(fmt, id, title, beginDate, endDate, fee);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (this.id != null && o instanceof Course course) {
			return Objects.equals(this.id, course.id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
