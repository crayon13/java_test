package java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LocalDateTest {

    @Test
    public void getCurrentDayTest() {
        LocalDate localDate = LocalDate.now();
        log.debug("" + localDate.getDayOfMonth());
        log.debug("" + localDate.getDayOfWeek());
        log.debug("" + localDate.getDayOfYear());
    }

    @Test
    public void getNextMonthString() {
        LocalDate localDate = LocalDate.now();

        log.debug(localDate.plusMonths(1).format(DateTimeFormatter.ofPattern("MM")));
    }

    @Test
    public void stringToLocalDateTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = "20200501";
        LocalDate beginCreateDate = LocalDate.parse(date, formatter);

        log.debug(beginCreateDate.format(DateTimeFormatter.ofPattern("yyyyMM")));
    }

}
