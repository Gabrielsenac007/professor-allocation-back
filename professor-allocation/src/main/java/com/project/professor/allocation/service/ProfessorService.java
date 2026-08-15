package com.project.professor.allocation.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.Repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final com.project.professor.allocation.Repository.ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public ProfessorService(com.project.professor.allocation.Repository.ProfessorRepository professorRepository, DepartmentService departmentService) {
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}

	public List<Professor> findByName(String partName) {
		return professorRepository.findByNameContainingIgnoreCase(partName);
	}

	public List<Professor> findByDepartment(Long departmentId) {
		Department department = new Department();
		department.setId(departmentId);
		return professorRepository.findByDepartment(department);
	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();

		if (id == null || !professorRepository.existsById(id)) {
			return null;
		}

		return saveInternal(professor);
	}

	public void deleteById(Long id) {
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	private Professor saveInternal(Professor professor) {
		if (professor.getDepartment() == null || professor.getDepartment().getId() == null) {
			throw new IllegalArgumentException("Departamento inválido.");
		}

		Department department = departmentService.findById(professor.getDepartment().getId());
		if (department == null) {
			throw new IllegalArgumentException("Departamento não encontrado.");
		}

		professor = professorRepository.save(professor);
		professor.setDepartment(department);

		return professor;
	}
}
