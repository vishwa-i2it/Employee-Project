package com.i2i.exception;

import java.lang.Exception;

/**
 * This class is used to handle
 * employee exception. 
 *
 * @author  Vishwaeaswaran M
 * @version 1.0 19 Aug 2022
 */
public class EmployeeException extends Exception {
    public EmployeeException() { }

    public EmployeeException(String message) {
        super(message);
    }
}