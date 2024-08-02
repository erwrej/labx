package com.example.labx.exceptions;


import com.example.labx.responses.InternalServerErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handle(ConstraintViolationException exception) {
        StringBuilder stringBuilder = new StringBuilder();
        exception.getConstraintViolations()
                .forEach((constraintViolation -> stringBuilder.append(constraintViolation.getPropertyPath()).append(" ").append(constraintViolation.getMessage()).append(";")));
        return ResponseEntity.status(400).body(new InternalServerErrorResponse(400, stringBuilder.toString()));
    }
}
