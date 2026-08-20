package com.project.professor.allocation.exceptions.Allocation;

public class InvalidCourseException extends RuntimeException{
    public InvalidCourseException(){super("Curso inválido para alocação");}

    public InvalidCourseException(String message){super(message);}
}
