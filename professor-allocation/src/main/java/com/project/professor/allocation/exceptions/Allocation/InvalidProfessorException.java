package com.project.professor.allocation.exceptions.Allocation;

public class InvalidProfessorException extends RuntimeException{
    public InvalidProfessorException(){super("Professor inválido para alocação");}

    public InvalidProfessorException(String message){super(message);}
}
