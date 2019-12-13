package io.mwk.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long employeeId) {
        super("Unable to find employee by given id : " + employeeId);
    }
}
