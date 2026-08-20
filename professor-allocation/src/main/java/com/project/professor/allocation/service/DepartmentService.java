package com.project.professor.allocation.service;
import java.util.List;

import com.project.professor.allocation.exceptions.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.project.professor.allocation.Repository.DepartmentRepository;
import com.project.professor.allocation.entity.Department;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado"));
	}

	public Department save(Department department) {
		department.setId(null);

		if (departmentRepository.existsByName(department.getName())){
			throw new AlreadyExistsException();
		}

		return departmentRepository.save(department);
	}

	public Department update(Department department) {
		Long id = department.getId();

		if (id == null || !departmentRepository.existsById(id)) {
			throw new EntityNotFoundException("Department not found");
		}

		return departmentRepository.save(department);
	}

	public void deleteById(Long id) {
		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}
}
