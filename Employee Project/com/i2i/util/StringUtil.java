package com.i2i.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The StringUtil class contains the methods
 * that used to validate the names and words etc.
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class StringUtil {

    /**
     * <p>
     * Get the name and check it whether it contains 
     * atleast 3 character and not exceed 50, alphabets caps & small,
     * and not a numeric values, special character.
     * </p>
     * <p>
     * (e.g): name = ". Net", retrun true
     * (e.g): name = ". Net 3", retrun false
     * </p>
     *
     * @param String name
     *        for which the name needs to be validated.
     * @return boolean value true, if condition satisfied otherwise false.
     */
    public static boolean isValidName(String name) {
        return Pattern.matches("^(?!\\.)(?!.*\\.$)(?!.*\\.\\.)^(?!\\s)(?!.*\\s$)(?!.*\\s\\s)[a-zA-Z\\s\\.]{3,30}", name);
    }

    /**
     * <p>
     * Get the word and check it whether it contains 
     * atleast 3 character and not exceed 50, alphabets caps & small,
     * and not a numeric values, special character.
     * </p>
     *
     * (e.g): word = "Java", retrun true
     * (e.g): word = "java8", retrun false
     *
     * @param String word
     *        for which the word needs to be validated.
     * @return boolean value true, if condition satisfied otherwise false.
     */
    public static boolean isValidWord(String word) {
        return Pattern.matches("^(?!\\.)(?!.*\\.$)(?!.*\\.\\.)^(?!\\s)(?!.*\\s$)(?!.*\\s\\s)[a-zA-Z-\\.]+${3,50}", word);
    }

    /**
     * <p>
     * Get the gender and check it whether it contains 
     * MALE, FEMALE or TRANSGENDER.
     * </p>
     * 
     * (e.g): gender = "Male", retrun true
     * (e.g): gender = "boy", retrun false
     *
     * @param String gender
     *        for which the gender needs to be validated.
     * @return boolean value true, if condition satisfied otherwise false.
     */
    public static boolean isValidGender(String gender) {
        return Pattern.matches("MALE||FEMALE||TRANSGENDER", gender);
    }

    /**
     * <p>
     * Get the Email ID and check it whether it contains 
     * atleast one '@' symbol, atleast one '.' and must not 
     * exceed 254 character, without recipient name, domain name
     * and top level domain name.
     * </p>
     * 
     * (e.g): emailId = "java@sunmicro.com", retrun true
     * (e.g): emailId = "javasunmicrocom", retrun false
     *
     * @param String emailId
     *        for which the emailId needs to be validated.
     * @return boolean value true, if condition satisfied otherwise false.
     */
    public static boolean isValidEmailId(String emailId) {
        return Pattern.matches("^(?!\\.)(?!.*\\.$)(?!.*\\.\\.)^[a-z]+[a-z0-9\\.+-]+@[a-z0-9\\.]+[a-z]+$", emailId);
    }
}