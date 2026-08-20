package com.project.professor.allocation.service;

import java.util.List;

import com.project.professor.allocation.dto.ProfessorRegisterDTO;
import com.project.professor.allocation.exceptions.Allocation.InvalidProfessorException;
import com.project.professor.allocation.exceptions.AlreadyExistsException;
import com.project.professor.allocation.exceptions.Department.InvalidDepartmentException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Professor> findAllWithPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return professorRepository.findAll(pageable).map(professor -> Professor
                .builder()
                .id(professor.getId())
                .name(professor.getName())
                .cpf(professor.getCpf())
                .department(professor.getDepartment())
                .build());
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
    }

    public List<Professor> findByName(String partName) {
        return professorRepository.findByNameContainingIgnoreCase(partName);
    }

    public List<Professor> findByDepartment(Long departmentId) {
        Department department = new Department();
        department.setId(departmentId);
        return professorRepository.findByDepartment(department);
    }

    public Professor save(ProfessorRegisterDTO professor) {

        if (professorRepository.existsByCpf(professor.cpf())) {
            throw new AlreadyExistsException("Professor já existe");
        }

        Department department = departmentService.findById(professor.departmentId());

        Professor professorSave = Professor.builder()
                .cpf(professor.cpf())
                .name(professor.name())
                .department(department)
                .build();
        return saveInternal(professorSave);
    }

    public Professor update(Professor professor) {
        Long id = professor.getId();

        if (id == null || !professorRepository.existsById(id)) {
            throw new InvalidProfessorException("Professor inválido");
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
            throw new InvalidDepartmentException();
        }

        Department department = departmentService.findById(professor.getDepartment().getId());
        if (department == null) {
            throw new EntityNotFoundException("Departamento não encontrado.");
        }

        professor = professorRepository.save(professor);
        professor.setDepartment(department);

        return professor;
    }
}
