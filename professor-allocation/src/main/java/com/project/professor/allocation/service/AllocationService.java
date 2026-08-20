package com.project.professor.allocation.service;

import java.util.List;

import com.project.professor.allocation.dto.AllocationCreateDTO;
import com.project.professor.allocation.exceptions.Allocation.InvalidCourseException;
import com.project.professor.allocation.exceptions.Allocation.InvalidHoursException;
import com.project.professor.allocation.exceptions.Allocation.InvalidProfessorException;
import com.project.professor.allocation.exceptions.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.Repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(
			AllocationRepository allocationRepository,
			ProfessorService professorService,
			CourseService courseService) {

		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}

	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Allocation not found"));
	}

	public List<Allocation> findByProfessor(Long professorId) {
		Professor professor = new Professor();
		professor.setId(professorId);
		return allocationRepository.findByProfessor(professor);
	}

	public List<Allocation> findByCourse(Long courseId) {
		Course course = new Course();
		course.setId(courseId);
		return allocationRepository.findByCourse(course);
	}

	public Allocation save(AllocationCreateDTO allocation) {

		Professor professor = professorService.findById(allocation.professorId());
		if (professor == null) {
			throw new EntityNotFoundException("Professor não encontrado.");
		}

		Course course = courseService.findById(allocation.courseId());
		if (course == null) {
			throw new EntityNotFoundException("Curso não encontrado.");
		}
		Allocation saveAllocation = Allocation.builder()
				.professor(professor)
				.course(course)
				.dayOfWeek(allocation.dayOfWeek())
				.startHour(allocation.startHour())
				.endHour(allocation.endHour())
				.build();
		return saveInternal(saveAllocation);
	}

	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();

		if (id == null || !allocationRepository.existsById(id)) {
			return null;
		}

		return saveInternal(allocation);
	}

	public void deleteById(Long id) {
		if (allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}

	private Allocation saveInternal(Allocation allocation) {
		if (!isEndHourGreaterThanStartHour(allocation)) {
			throw new InvalidHoursException();
		}

		if (allocation.getProfessor() == null || allocation.getProfessor().getId() == null) {
			throw new InvalidProfessorException();
		}

		if (allocation.getCourse() == null || allocation.getCourse().getId() == null) {
			throw new InvalidCourseException();
		}


		if (hasCollision(allocation)) {
			throw new AlreadyExistsException("O professor já possui uma alocação nesse horário.");
		}

		allocation = allocationRepository.save(allocation);
		allocation.setCourse(allocation.getCourse());
		allocation.setProfessor(allocation.getProfessor());

		return allocation;
	}

	private boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation.getStartHour() != null
				&& allocation.getEndHour() != null
				&& allocation.getEndHour().isAfter(allocation.getStartHour());
	}

	private boolean hasCollision(Allocation allocation) {
		List<Allocation> allocations = allocationRepository.findByProfessor(allocation.getProfessor());

		return allocations.stream()
				.filter(existing -> !existing.getId().equals(allocation.getId()))
				.anyMatch(existing -> hasCollision(existing, allocation));
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
				&& newAllocation.getStartHour().isBefore(currentAllocation.getEndHour())
				&& currentAllocation.getStartHour().isBefore(newAllocation.getEndHour());
	}
}
