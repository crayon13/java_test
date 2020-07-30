package java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public void orElseGetTest() {
        // given
        String initailMessage = "intital";
        Dto given = new Dto(initailMessage);

        // when
        String nullMessage = "orElseGetTest";
        Dto when = Optional.ofNullable(given).orElseGet(() -> new Dto(nullMessage + "1"));

        // then
        assertThat(when.getMessage()).isEqualTo(initailMessage);

        // given
        given = null;

        // when
        when = Optional.ofNullable(given).orElseGet(() -> new Dto(nullMessage + "2"));

        // then
        assertThat(when.getMessage()).isEqualTo(nullMessage + "2");
    }

    @Test
    public void orElseTest() {
        // given
        String initailMessage = "intital";
        Dto given = new Dto(initailMessage);

        // when
        String nullMessage = "orElseTest";
        Dto when = Optional.ofNullable(given).orElse(new Dto(nullMessage + "1"));

        // then
        assertThat(when.getMessage()).isEqualTo(initailMessage);

        // given
        given = null;

        // when
        when = Optional.ofNullable(given).orElse(new Dto(nullMessage + "2"));

        // then
        assertThat(when.getMessage()).isEqualTo(nullMessage + "2");
    }

    @Getter
    public class Dto {
        private String message;

        public Dto(String message) {
            this.message = message;
            log.debug(message + "!!");
        }
    }

    @Test
    public void isPresentTest() {
        Dto given = null;

        Optional<Dto> optionalDto = Optional.ofNullable(given);

        if (optionalDto.isPresent()) {
            log.debug("given is not null");
        } else {
            log.debug("given is null");
        }

        if (optionalDto.orElse(null) != null) {
            log.debug("given is not null");
        } else {
            log.debug("given is null");
        }

    }

    @Test
    public void orElseTest2() {
        List<TestVO> list = Collections.EMPTY_LIST;

        String a = list.stream().findFirst().map(testVO -> testVO.getName()).orElse(null);

        log.debug(a);
    }

    @Test
    public void orElseVsOrElseGetTest() {
        List<String> list = new ArrayList<>();
        list.add("A");

        long a1 = Optional.ofNullable(list).orElseGet(() -> getEmptyList("a1")).stream().count();
        log.debug("a1 : " + a1);

        long b1 = Optional.ofNullable(list).orElse(getEmptyList("b1")).stream().count();
        log.debug("b1 : " + b1);

        list = null;

        long a2 = Optional.ofNullable(list).orElseGet(() -> getEmptyList("a2")).stream().count();
        log.debug("a2 : " + a2);

        long b2 = Optional.ofNullable(list).orElse(getEmptyList("b2")).stream().count();
        log.debug("b2 : " + b2);
    }

    private List<String> getEmptyList(String message) {
        log.debug("++++++" + message);

        return Collections.emptyList();
    }


    @AllArgsConstructor
    @Getter
    public class TestVO {
        String name;

    }



}
