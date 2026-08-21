package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Departments")
@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super();
        this.departmentService = departmentService;
    }

//    antigo request para buscar todos os departamentos sem paginação
//
//    @Operation(summary = "Find all departments")
//    @ApiResponses({
//    	@ApiResponse(responseCode = "200", description = "OK")
//    })
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Department>> findAll() {
//        List<Department> departments = departmentService.findAll();
//        return new ResponseEntity<>(departments, HttpStatus.OK);
//    }

    @Operation(summary = "Find all Departments w/ Pagination")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> findAllWithPages(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size) {

        Page<Department> pageableList = departmentService.findAllWithPages(page, size);

        return new ResponseEntity<>(pageableList.getContent(), HttpStatus.OK);
    }

    @Operation(summary = "Find a department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @Operation(summary = "Save a department")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> save(@RequestBody Department department) {
        department = departmentService.save(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,
                                             @RequestBody Department department) {
        department.setId(id);
        department = departmentService.update(department);
        return new ResponseEntity<>(department, HttpStatus.OK);

    }

    @Operation(summary = "Delete a department")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping(path = "/{department_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}