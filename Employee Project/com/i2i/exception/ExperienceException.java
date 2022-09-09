package com.i2i.exception;

import java.lang.Exception;

/**
 * This class is used to handle
 * experience exception. 
 *
 * @author  Vishwaeaswaran M
 * @version 1.0 19 Aug 2022
 */
public class ExperienceException extends Exception {
    public ExperienceException() { }

    public ExperienceException(String message) {
        super(message);
    }
}