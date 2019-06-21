import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.is;

public class DateTest {

    @Test
    public void checkRateDateTimeTest() {
        String given = "20190128235959";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        LocalDateTime testDateTime = LocalDateTime.parse(given,formatter);


        Assert.assertFalse(  checkRateDateTime("1", given) );
    }

    private boolean checkRateDateTime(String rate, String endDate){
        LocalDateTime currDateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, dateTimeFormatter);
//
//        if ((ChronoUnit.SECONDS.between(currDateTime,endDate1)) >=0 ){
//            return false;
//        }
//        return true;

//
//        String dateTime = "2012-02-22T02:06:58.147Z";
//        ZonedDateTime d = ZonedDateTime.parse(dateTime);
//
//        LocalDateTime endDateTime = LocalDateTime.ofInstant(Instant.parse(endDate), ZoneId.systemDefault());

        return !"0".equals(rate) && ChronoUnit.SECONDS.between(currDateTime, endDateTime) >= 0;
    }


    @Test
    public void localDateTime() {
        String simpleDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.out.println("simpleDate : " + simpleDate);
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));


        System.out.println(localDate);

        Assert.assertThat(localDate, is(simpleDate));
    }

    @Test
    public void calendarVsLocalDateTime() {
        String dateValue = "20190311";
        int amountDay = 10;

        Assert.assertThat(getEvaluateDate(dateValue, amountDay) , is(getEvaluateDateWithLocalDateTime(dateValue, amountDay)));


        dateValue = "20190101";
        Assert.assertThat(getEvaluateDate(dateValue, amountDay) , is(getEvaluateDateWithLocalDateTime(dateValue, amountDay)));
    }


    private String getEvaluateDate(String dateValue, int amountDay) {
        int year = Integer.parseInt(dateValue.substring(0, 4));
        int month = Integer.parseInt(dateValue.substring(4, 6));
        int date = Integer.parseInt(dateValue.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        calendar.add(Calendar.DATE, amountDay);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

        String evaluateDate = dateFormatter.format(calendar.getTime());

        return evaluateDate;
    }



    private String getEvaluateDateWithLocalDateTime(String dateValue, int amountDay) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        return LocalDate.parse(dateValue, dateTimeFormatter).
                plusDays(amountDay).
                format(dateTimeFormatter);
    }




}
