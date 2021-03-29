import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;

@Slf4j
public class TimeTest {
    private static final long TTL_SECONDS = MINUTES.toSeconds(5);
    private static final long TTL_SECONDS_FOR_EVENT = MINUTES.toSeconds(20);

    private static final LocalTime BEFORE_IN = LocalTime.of(0, 10);
    private static final List<LocalTime> EVENT_TIMES = Arrays.asList(
            LocalTime.of(0, 0),
            LocalTime.of(10, 0),
            LocalTime.of(14, 0),
            LocalTime.of(18, 0),
            LocalTime.of(11, 55)
            );

    @Test
    public void test() {
        boolean event = isEvent2();

        if (event) {
            log.debug(TTL_SECONDS_FOR_EVENT + "----");
        } else {
            log.debug(TTL_SECONDS + "----");
        }
    }

    private static boolean isEvent() {
        final LocalTime now = LocalTime.now();
        log.debug(now + "");
        final boolean event = EVENT_TIMES.stream()
                .anyMatch(eventTime -> {
                    int duration = toSeconds(eventTime) - toSeconds(now);
                    log.debug(eventTime + "/" + toSeconds(eventTime) + "/" + toSeconds(now));
                    log.debug("duration : " + duration);
                    return 0 <= duration && duration <= BEFORE_IN.toSecondOfDay() ;
                });

        return event;
    }

    private static boolean isEvent2() {
        final LocalTime now = LocalTime.now();

        return EVENT_TIMES.stream()
                .anyMatch(eventTime -> {
                    log.info(eventTime + "-------------");
                    log.debug(now.plusMinutes(11) + "-" + eventTime) ;
                    log.debug(eventTime.minusMinutes(11) + "-" + now) ;

                    return now.isAfter(eventTime.minusMinutes(10)) && eventTime.plusMinutes(1).isAfter(now);

                });

    }

    private static int toSeconds(LocalTime time) {
        return time.isBefore(BEFORE_IN)
                ? LocalTime.MAX.toSecondOfDay() + 1 + time.toSecondOfDay()
                : time.toSecondOfDay();
    }
}
