package date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class DruidDateUtils {

    public static String DruidAnalyticsIntervalsByDay(Date startDate, Date endDate, String pattern) throws Exception {
        if(startDate == null || endDate == null){
            throw new Exception();
        }

        return DateFormatUtils.format(startDate, pattern) + "/" + DateFormatUtils.format(endDate, pattern);
    }


    public static void main(String args[]) {
        try {
            String date = DruidDateUtils.DruidAnalyticsIntervalsByDay(
                    DateUtils.addDays(new Date(), -2)
                    , DateUtils.addDays(new Date(), 2)
                    , "yyyy-MM-dd");

            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
