package java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class ZoneDateTimeTest {
    @Test
    public void compareLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        log.debug("localDateTime : {}, zonedDateTime : {}", localDateTime.toString(), zonedDateTime.toString());
    }
}
