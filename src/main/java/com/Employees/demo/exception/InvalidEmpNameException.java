package com.Employees.demo.exception;

public class InvalidEmpNameException extends RuntimeException {

    public InvalidEmpNameException(String message) {
        super(message);
    }
}
