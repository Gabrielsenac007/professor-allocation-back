package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "allocation")
public class Allocation {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "day_of_week", nullable=false)
	private DayOfWeek dayOfWeek;
	
	@Column(name = "star_hour", nullable=false)
	private LocalTime startHour;
	
	@Column(name = "end_hour", nullable=false)
 	private LocalTime endHour;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	public void setProfessorID(Long id) {
		Professor professor = new Professor();
		professor.setId(id);
		this.setProfessor(professor);
		
	}
	
	public void setCourseID(Long id) {
		Course course = new Course();
		course.setId(id);
		this.setCourse(course);
		
	}

}