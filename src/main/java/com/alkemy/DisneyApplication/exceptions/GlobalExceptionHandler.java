package com.alkemy.DisneyApplication.exceptions;

import com.alkemy.DisneyApplication.dto.ApiErrorDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest wr) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) cause);
        }

        String message = (ex.getMessage());
        List<String> details = new ArrayList();
        details.add("Request body is not readable");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, status, timestamp);
        return ResponseEntity.status(status).body(error);

    }

    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
        String message = "Invalid format";
        List<String> details = new ArrayList();
        details.add("Check the format of the input data");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, HttpStatus.BAD_REQUEST, timestamp);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = (ex.getMessage());
        List<String> details = new ArrayList();
        details.add("Mismatch of type");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, status, timestamp);
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = (ex.getMessage());
        List<String> details = new ArrayList();
        details.add("Request param is missing");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, status, timestamp);
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = (ex.getMessage());
        List<String> details = new ArrayList();
        details.add("Path variable is missing");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, status, timestamp);
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = (ex.getMessage());
        List<String> details = new ArrayList();
        details.add("Media not supported");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, status, timestamp);
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = (ex.getMessage());
        List<String> details = new ArrayList();
        details.add("Request method not supported");
        Date timestamp = new Date();
        ApiErrorDTO error = new ApiErrorDTO(message, details, status, timestamp);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFounException(RuntimeException ex, WebRequest request) {
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                "Data not found",
                Arrays.asList(ex.getMessage()),
                HttpStatus.NOT_FOUND,
                new Date());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {DuplicateValueException.class})
    protected ResponseEntity<Object> handleDuplicateValueException(RuntimeException ex, WebRequest request) {
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                "Duplicated value",
                Arrays.asList(ex.getMessage()),
                HttpStatus.CONFLICT,
                new Date());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {DateTimeException.class})
    protected ResponseEntity<Object> handleDateTimeException(DateTimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                "Invalid date",
                Arrays.asList(ex.getMessage()),
                HttpStatus.BAD_REQUEST,
                new Date());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiErrorDTO errorDTO = new ApiErrorDTO("Invalid data sent", errors, HttpStatus.BAD_REQUEST, new Date());
        return handleExceptionInternal(ex, errorDTO, headers, errorDTO.getStatus(), request);
    }
    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                "Bad credentials",
                Arrays.asList(ex.getMessage()),
                HttpStatus.UNAUTHORIZED,
                new Date());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

}
