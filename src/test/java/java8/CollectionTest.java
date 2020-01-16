package java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CollectionTest {

    @Test
    public void asArrayTest() {
        List<String> given = Arrays.asList("a", "b");

        // 에러 발생
        given.add("c");
        log.debug("size : {}", given.size());


        List<String> given2 = new ArrayList<>();
        given2.addAll(given);


        log.debug("size : {}", given2.size());

        Collections.sort(given2, Collections.reverseOrder());

        given2.subList(0,1);
    }
}
