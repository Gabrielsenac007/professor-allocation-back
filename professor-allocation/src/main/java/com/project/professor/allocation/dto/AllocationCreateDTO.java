package com.project.professor.allocation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record AllocationCreateDTO(
        @NotNull(message = "Professor precisa ser informado")
        Long professorId,
        @NotNull(message = "Curso precisa ser informado")
        Long courseId,
        @NotNull(message = "Dia da semana não pode ser null ou vazio")
        DayOfWeek dayOfWeek,
        @NotNull(message = "Data de inicio deve ser informada")
        @JsonFormat(pattern = "HH:mm")
        @Schema(example = "13:00")
        LocalTime startHour,
        @NotNull(message = "Data de fim deve ser informada")
        @JsonFormat(pattern = "HH:mm")
        @Schema(example = "17:00")
        LocalTime endHour
) {
}
