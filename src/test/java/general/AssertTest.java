package general;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AssertTest {
    @Test
    public void dateTimeFormatAssert() {
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        try {
            LocalDate targetDate = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (DateTimeParseException ex) {
            throw new DateTimeException("Date Format(yyyyMMdd) not matched", ex);
        }



    }
}
