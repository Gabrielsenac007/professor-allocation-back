package com.project.professor.allocation.controller.handler;

import com.project.professor.allocation.exceptions.Allocation.InvalidCourseException;
import com.project.professor.allocation.exceptions.Allocation.InvalidHoursException;
import com.project.professor.allocation.exceptions.Allocation.InvalidProfessorException;
import com.project.professor.allocation.exceptions.AlreadyExistsException;
import com.project.professor.allocation.exceptions.Department.InvalidDepartmentException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidHoursException.class)
    private ResponseEntity<ErrorMessageDTO> invalidHoursHandler(InvalidHoursException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidProfessorException.class)
    private ResponseEntity<ErrorMessageDTO> invalidProfessorHandler(InvalidProfessorException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidCourseException.class)
    private ResponseEntity<ErrorMessageDTO> invalidCourseHandler(InvalidCourseException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidDepartmentException.class)
    private ResponseEntity<ErrorMessageDTO> invalidDepartmentHandler(InvalidDepartmentException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(AlreadyExistsException.class)
        private ResponseEntity<ErrorMessageDTO> invalidHoursHandler(AlreadyExistsException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorMessageDTO> notFound(EntityNotFoundException exception){
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}
