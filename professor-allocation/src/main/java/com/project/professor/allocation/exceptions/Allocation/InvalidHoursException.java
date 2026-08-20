package com.project.professor.allocation.exceptions.Allocation;

public class InvalidHoursException extends RuntimeException{

    public InvalidHoursException(){super("O horário final deve ser maior que o horário inicial.");}

    public InvalidHoursException(String message){super(message);}

}
