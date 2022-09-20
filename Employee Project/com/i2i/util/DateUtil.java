package com.i2i.util;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.time.ZoneId;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.i2i.exception.EmployeeException;

/**
 * The DateUtil class contains commonly used date manipulating
 * methods like age calculation, experience calculation
 * and date validation etc.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class DateUtil {
    private static Logger log = LogManager.getLogger();
    /**
     * <p>
     * Get the date and calculate the difference years
     * between the current date.
     * </p>
     *
     * (e.g): date = 10/08/2020, currentDate = 10/08/2022 return 2
     *
     * @param Date date
     *        for which the date needs to be calculated.
     * @return the integer value.
     *
     * @throws EmployeeExcepiton
     */
    public static int calculateYears(Date date) throws EmployeeException {
        if (date != null) {
            LocalDate formatGivenDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            if (currentDate != null) {
                return Period.between(formatGivenDate, currentDate).getYears();
            } else {
                throw new EmployeeException("Current Date is null");
            }
        } else {
            throw new EmployeeException("Given Date is null");
        }
    }

    /**
     * <p>
     * Get the date and calculate the difference
     * years and months between the current date.
     * </p>
     * 
     * (e.g): date = 10/06/2020, currentDate = 10/08/2022 return 2.2
     *
     * @param Date date
     *        for which the date needs to be calculated.
     * @return float value.
     *
     * @throws EmployeeException if date is null.
     */
    public static Float calculateYearsMonths(Date date) throws EmployeeException {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        if (date != null) {
            LocalDate formatGivenDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();

            if (currentDate != null) {
                return (Period.between(formatGivenDate, currentDate).getYears() + Float.parseFloat(decimalFormat.format(Period.between(formatGivenDate, currentDate).getMonths() * 0.08)));
            } else {
                throw new EmployeeException("Current Date is null");
            }
        } else {
            throw new EmployeeException("Given Date is null");
        }
    }

    /**
     * <p>
     * Get the date and check whether it has 
     * right format like dd/mm/yyyy.
     * </p>
     *
     * (e.g): date = "12/12/2000", return true
     * (e.g): word = "30/02/2000", return false
     *
     * @param String date
     *        for which the date needs to be validated.
     * @return Date if the given date is valid.
     *
     * @throws EmployeeException
     */
    public static Date checkValidDate(String date) throws EmployeeException {
        for (int index = 1; index < date.length(); index++) {
            if (!(date.length() == 10 && date.charAt(index) >= 47 && date.charAt(index) <= 57)) {
                throw new EmployeeException("Given Date is invalid!!");
            }
        }
        try {
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            formatDate.setLenient(false);
            Date validDate = formatDate.parse(date);
            return validDate;
        } catch (ParseException parseException) {
            log.warn(parseException);
            throw new EmployeeException("Given Date is invalid!!");
        }
    }

    /**
     * <p>
     * Get the joining date and birth date and check whether it has 
     * correct joining date like joining date greater than or equal to 18 years
     * of the birth date.
     * </p>
     *
     * (e.g): joinDate = 12/12/2018, birthDate = 12/12/2000, return true
     * (e.g): joinDate = 12/12/2017, birthDate = 12/12/2000, return false
     *
     * @param Date joinDate
     *        for which the date needs to be validated.
     * @param Date birthDate
     *        for which the date needs to be validated.
     * @return boolean true if the condition satisfied otherwise false.
     */
    public static Date checkValidDateOfJoin(Date joinDate, Date birthDate) throws EmployeeException {
        if (joinDate != null && birthDate != null) {
            LocalDate formatGivenJoinDate = joinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate formatGivenBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            formatGivenBirthDate = formatGivenBirthDate.plusYears(18);

            LocalDate currentDate = LocalDate.now();
            if (formatGivenJoinDate.compareTo(formatGivenBirthDate) >= 0) {
                if (formatGivenJoinDate.compareTo(currentDate) <= 0) {
                    return joinDate;
                } else {
                throw new EmployeeException("Given Date is invalid");
                }
            } else {
                throw new EmployeeException("Given Date is invalid");
            }
        } else {
            throw new EmployeeException("Given Date is null");
        }
    }
}
