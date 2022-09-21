import java.util.Date;

import com.i2i.util.DateUtil;

class Sample {
    public static void main(String... a) {
        String date = "30/02/2001";
        Date givenDate = DateUtil.checkValidDate(date);
        Date emptyDate;
        System.out.println(DateUtil.calculateYears(EmptyDate));
        System.out.println(DateUtil.calculateYearsMonths(givenDate));
    }
}