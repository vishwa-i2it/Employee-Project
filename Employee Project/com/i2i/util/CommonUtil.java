package com.i2i.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommonUtil class contains commonly used methods
 * like mobile number validation etc.
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class CommonUtil {

    /**
     * <p>
     * Get the mobile number and check it whether it contains 
     * 10 digits number, not a alphabet values and starts between
     * 5 and 9.
     * </p>
     *
     * (e.g): mobileNo = "+919876543210", retrun true
     * (e.g): mobileNo = "9123456780", retrun true
     * (e.g): mobileNo = "187654321023", retrun false
     *
     * @param String mobileNo
     *        for which the mobile number needs to be validated.
     * @return the boolean value (true or false) based on the condition.
     */
    public static boolean isValidMobileNumber(String mobileNo) {
        return Pattern.matches("([+][9][1][6-9]{1}[0-9]{9})||([6-9]{1}[0-9]{9})", mobileNo);
    }

    /**
     * <p>
     * Get the string and check it whether it contains 
     * only number, not a alphabet and others.
     * </p>
     * 
     * (e.g): number = "987", retrun true
     * (e.g): number = "12h#", retrun false
     * 
     * @param String number
     *        for which the number needs to be validated.
     * @return the boolean value (true or false) based on the condition.
     */
    public static boolean isNumeric(String number) {
        return Pattern.matches("[0-9]", number);
    }
}