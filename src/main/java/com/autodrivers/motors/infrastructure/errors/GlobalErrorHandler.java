package com.autodrivers.motors.infrastructure.errors;

import com.autodrivers.motors.infrastructure.errors.exception.BusinessRulesValidationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> noFoundErrorHandler(
            EntityNotFoundException ex,
            HttpServletRequest request){

        return buildResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(BusinessRulesValidationException.class)
    public ResponseEntity<ErrorDto> businessLogicValidationHandler(
            BusinessRulesValidationException ex,
            HttpServletRequest request){
        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> noValidDataHandler(
            MethodArgumentNotValidException e,
            HttpServletRequest request){


        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();

        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors()
        .forEach(error ->
            errorMap.put(error.getField(), error.getDefaultMessage())
        );

        ErrorDto errorDto = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getTitleMessageCode(),
                request.getRequestURI()
        );
        errorDto.setErrors(errorMap);
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> dataIntegrityErrorHandler(
            DataIntegrityViolationException e,
            HttpServletRequest request){

        return buildResponse(HttpStatus.BAD_REQUEST, e, request);
    }



    private ResponseEntity<ErrorDto> buildResponse(HttpStatus status, Exception ex, HttpServletRequest request){

        String message = (ex.getMessage() != null && !ex.getMessage().isEmpty())
                ? ex.getMessage()
                : "Unexpected Error Occurred";


        ErrorDto apiErrorDto = new ErrorDto(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(apiErrorDto);
    }

    public record ErrorValidationData(String field, String error){
        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
