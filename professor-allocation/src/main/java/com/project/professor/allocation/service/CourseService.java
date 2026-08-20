package com.project.professor.allocation.service;
import java.util.List;

import com.project.professor.allocation.exceptions.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.project.professor.allocation.entity.Course;

@Service
public class CourseService {

	private final com.project.professor.allocation.Repository.CourseRepository courseRepository;

	public CourseService(com.project.professor.allocation.Repository.CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
	}

	public Course save(Course course) {
		course.setId(null);
		if (courseRepository.existsByName(course.getName())){
			throw new AlreadyExistsException();
		}
		return courseRepository.save(course);
	}

	public Course update(Course course) {
		Long id = course.getId();

		if (id == null || !courseRepository.existsById(id)) {
			throw new EntityNotFoundException("Course not found");
		}

		return courseRepository.save(course);
	}

	public void deleteById(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}
}
