package io.mwk.controller;

import io.mwk.exception.DepartmentNotFoundException;
import io.mwk.exception.EmployeeNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
class ExceptionHandlerControllerAdvice {

	@ResponseBody
	@ExceptionHandler(TransactionSystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorResponse transactionSystemExceptionHandler(TransactionSystemException exception) {
	    String exceptionMessage = getExceptionMessage(exception);
		return new ErrorResponse(exceptionMessage, "", HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse employeeNotFoundHandler(EmployeeNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), "", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse departmentNotFoundHandler(DepartmentNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), "", HttpStatus.NOT_FOUND);
    }

    private Throwable findCauseUsing(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    private String getExceptionMessage(Exception exception){
        String message = "";
        Exception exception1 = (Exception) findCauseUsing(exception);
        if(exception1 instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception1;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            message =constraintViolations.stream()
                    .map( cv -> cv == null ? "null" : cv.getMessage() )
                    .collect( Collectors.joining( ", " ) );
        }else{
            message = exception1.getMessage();
        }

	    return message;
    }

    @Data
    class ErrorResponse {
        private final Instant timestamp;
        private final String status;
        private final String error;
        private final String message;
        private final String path;

        ErrorResponse(String message, String path, HttpStatus httpStatus) {
            this.timestamp = Instant.now();
            this.status = String.valueOf(httpStatus.value());
            this.error = httpStatus.getReasonPhrase();
            this.message = message;
            this.path = path;
        }

    }
}