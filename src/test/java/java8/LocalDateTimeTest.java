package java8;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class LocalDateTimeTest {

    @Test
    @DisplayName("")
    public void checkRateDateTimeTest() {
        String given = "20190128235959";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        LocalDateTime testDateTime = LocalDateTime.parse(given,formatter);


        assertFalse(  checkRateDateTime("1", given) );


        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        log.debug(now);

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
        log.debug("simpleDate : {}", simpleDate);

        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        log.debug("localDate : {}, {}", localDate, "a");

        assertEquals(localDate, simpleDate);
    }

    @Test
    public void calendarVsLocalDateTime() {
        String dateValue = "20190311";
        int amountDay = 10;

        assertEquals(getEvaluateDate(dateValue, amountDay) , getEvaluateDateWithLocalDateTime(dateValue, amountDay));


        dateValue = "20190101";
        assertEquals(getEvaluateDate(dateValue, amountDay) , getEvaluateDateWithLocalDateTime(dateValue, amountDay));
    }

    @Test
    public void remainShapTimeSecTest() {
        final int HOUR_TO_MINUTE = 60;
        final int MINUTE_TO_SECOND = 60;

        LocalDateTime now = LocalDateTime.now();
        int minute = now.getMinute();
        int second = now.getSecond();

        int remainShapTimeSec = HOUR_TO_MINUTE * MINUTE_TO_SECOND - ( minute * MINUTE_TO_SECOND + second);

        log.debug("now : {} ,remainShapTimeSec : {}", now.toString(), remainShapTimeSec);

        assertEquals(remainShapTimeSec,
            (HOUR_TO_MINUTE - 1 - minute) * MINUTE_TO_SECOND + (MINUTE_TO_SECOND - second)
        );
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


    @Test
    public void convertTimeStampTest() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        LocalDateTime now1 = timestamp.toLocalDateTime();
        log.debug(now1.toString());

        LocalDateTime now2 = LocalDateTime.now();
        log.debug(now2.toString());


        log.debug(now2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void checkStringDate() {
        String given = "20191220";

        assertEquals(checkStringDate(given), given + "000000");
        assertEquals(checkStringDate(given), checkStringDate2(given));

    }


    private String checkStringDate(String date) {
        int nonFillCharLength = 14 - date.length();
        String fillingCharacter = "0";
        StringBuilder fillData = new StringBuilder();

        int idx = 0;
        while (idx < nonFillCharLength) {
            fillData.append(fillingCharacter);
            idx++;
        }
        return (date + fillData.toString());
    }

    private String checkStringDate2(String date) {
        return StringUtils.rightPad(date, 14, "0");
    }
}
