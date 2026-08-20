package com.project.professor.allocation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ProfessorRegisterDTO(

        @NotBlank(message = "Valor não pode ser null ou vazio")
        @Size(min = 10, max = 100, message = "O nome deve conter entre 10 e 100 caracteres")
        String name,

        @NotBlank(message = "Valor não pode ser null ou vazio")
        @Size(min = 11, max = 11, message = "Valor não pode ultrapassar 11 caracteres")
        @CPF
        String cpf,

        @NotNull(message = "O departamento precisa ser informado")
        Long departmentId
) {
}
