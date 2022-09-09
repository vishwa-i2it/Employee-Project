package com.i2i.util;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * The DateUtil class contains commonly used date manipulating
 * methods like age calculation, experience calculation
 * and date validation etc.
 *
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class DateUtil {

    /**
     * <p>
     * Get the date and calculate the difference years
     * between the current date.
     * </p>
     *
     * (e.g): date = "10/08/2020", currentDate = 10/08/2022 return 2
     *
     * @param String date
     *        for which the date needs to be calculated.
     * @return the integer value.
     *
     */
    public static int calculateYears(String date) {
        if (DateUtil.isValidDate(date)) {
            try {
                Date givenDate = new SimpleDateFormat("dd/MM/yyyy").parse(date); 
                SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate formatGivenDate = LocalDate.parse(simpleDateFormater.format(givenDate));
                LocalDate currentDate = LocalDate.now();

                if ((givenDate != null) && (currentDate != null)) {
                    return Period.between(formatGivenDate, currentDate).getYears();
                } else {
                    return 0;
                }
            } catch (ParseException exception) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * <p>
     * Get the date and calculate the difference
     * years and months between the current date.
     * </p>
     * 
     * (e.g): date = "10/06/2020", currentDate = 10/08/2022 return 2.2
     *
     * @param String date
     *        for which the date needs to be calculated.
     * @return float value.
     *
     * @throws ParseException if date contains alphabets or empty.
     */
    public static Float calculateYearsMonths(String date) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        if (DateUtil.isValidDate(date)) {
            try {
                Date givenDate = new SimpleDateFormat("dd/MM/yyyy").parse(date); 
                SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate formatGivenDate = LocalDate.parse(simpleDateFormater.format(givenDate));
                LocalDate currentDate = LocalDate.now();

                if ((givenDate != null) && (currentDate != null)) {
                    return (Period.between(formatGivenDate, currentDate).getYears() + Float.parseFloat(decimalFormat.format(Period.between(formatGivenDate, currentDate).getMonths() * 0.08)));
                } else {
                    return (float)0;
                }
            } catch (ParseException exception) {
                return (float)0;
            }
        } else {
            return (float)0;
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
     * @return boolean true if the condition satisfied otherwise false.
     */
    public static boolean isValidDate(String date) {
        for (int index = 1; index < date.length(); index++) {
            if (!(date.length() == 10 && date.charAt(index) >= 47 && date.charAt(index) <= 57)) {
                return false;
            }
        }
        try {
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            formatDate.setLenient(false);
            formatDate.parse(date);
            return true;
        } catch (ParseException exception) {
            return false;
        }
    }

    /**
     * <p>
     * Get the joining date and birth date and check whether it has 
     * correct joining date like joining date greater than or equal to 18 years
     * of the birth date.
     * </p>
     *
     * (e.g): joinDate = "12/12/2018", birthDate = "12/12/2000", return true
     * (e.g): joinDate = "12/12/2017", birthDate = "12/12/2000", return false
     *
     * @param String date
     *        for which the date needs to be validated.
     * @return boolean true if the condition satisfied otherwise false.
     */
    public static boolean isValidDateOfJoin(String joinDate, String birthDate) {
        try {
            if (DateUtil.isValidDate(joinDate) && DateUtil.isValidDate(birthDate)) {
                SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd");

                Date givenJoinDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinDate); 
                LocalDate formatGivenJoinDate = LocalDate.parse(simpleDateFormater.format(givenJoinDate));

                Date givenBirthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate); 
                LocalDate formatGivenBirthDate = LocalDate.parse(simpleDateFormater.format(givenBirthDate));
                formatGivenBirthDate = formatGivenBirthDate.plusYears(18);

                LocalDate currentDate = LocalDate.now();
                if (formatGivenJoinDate.compareTo(formatGivenBirthDate) >= 0) {
                    if (formatGivenJoinDate.compareTo(currentDate) <= 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (ParseException exception) {
            return false;
        }
    }
}
