package java8;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class OptionalTest {

    @Test
    public void ofNullTest() {
        assertThatThrownBy(() -> Optional.of(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void orElseNullTest() {
        // given
        Dto given = null;

        // when
        String nullMessage = "orElseNullTest";
        Dto when = Optional.ofNullable(given).orElse(new Dto(nullMessage));

        // then
        assertThat(when.getMessage()).isEqualTo(nullMessage);
    }

    @Test
    public void orElseTest() {
        // given
        String initailMessage = "intital";
        Dto given = new Dto(initailMessage);

        // when
        String nullMessage = "orElseTest";
        Dto when = Optional.ofNullable(given).orElseGet(() -> new Dto(nullMessage));

        // then
        assertThat(when.getMessage()).isEqualTo(initailMessage);
    }


    @Getter
    public class Dto {
        private String message;

        public Dto(String message) {
            this.message = message;
            log.debug(message + "!!");
        }
    }
}
