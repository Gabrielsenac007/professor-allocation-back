package com.project.professor.allocation.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(){super("Registro já existe");}

    public AlreadyExistsException(String message){super(message);}
}
