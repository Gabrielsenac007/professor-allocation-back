package com.project.professor.allocation.controller.handler;

import org.springframework.http.HttpStatus;

public record ErrorMessageDTO(
        HttpStatus httpStatus,
        String message
) {
}
