package io.mwk.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(Long departmentId) {
        super("Unable to find department by given id : " + departmentId);
    }
}
