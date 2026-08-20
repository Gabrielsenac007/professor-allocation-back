package com.project.professor.allocation.controller;

import java.util.List;

import com.project.professor.allocation.dto.AllocationCreateDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Allocations")
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        super();
        this.allocationService = allocationService;
    }

//    antiga request find all sem paginação
//
//    @Operation(summary = "Find all allocations")
//    @ApiResponses({
//    	@ApiResponse(responseCode = "200", description = "OK")
//    })
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Allocation>> findAll() {
//        List<Allocation> allocations = allocationService.findAll();
//        return new ResponseEntity<>(allocations, HttpStatus.OK);
//    }

    @Operation(summary = "Find all w/ Pages")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allocation>> findAllWithPages(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "5") int size) {
        Page<Allocation> pageableList = allocationService.findAllWithPages(page, size);

        return new ResponseEntity<>(pageableList.getContent(), HttpStatus.OK);
    }

    @Operation(summary = "Find an allocation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
        Allocation allocation = allocationService.findById(id);
        return new ResponseEntity<>(allocation, HttpStatus.OK);

    }

    @Operation(summary = "Find allocations by professor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
        List<Allocation> allocations = allocationService.findByProfessor(id);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @Operation(summary = "Find allocations by course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long id) {
        List<Allocation> allocations = allocationService.findByCourse(id);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @Operation(summary = "Save an allocation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allocation> save(@RequestBody @Valid AllocationCreateDTO allocation) {
        Allocation allocationSaved = allocationService.save(allocation);
        return new ResponseEntity<>(allocationSaved, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an allocation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id") Long id,
                                             @RequestBody Allocation allocation) {
        allocation.setId(id);
        allocation = allocationService.update(allocation);
        return new ResponseEntity<>(allocation, HttpStatus.OK);

    }

    @Operation(summary = "Delete an allocation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping(path = "/{allocation_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
        allocationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}