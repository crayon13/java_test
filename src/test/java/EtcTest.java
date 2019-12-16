import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class EtcTest {

    @Test
    public void stringSplitAndTrimTest() {
        String testString = "a, b, c";

        assertEquals(testString.split(",")[0].trim(), "a");

    }

    @Test
    public void randomTest() {
        String[] a = new String[2];

        int index = (int)(Math.random() * a.length);
        log.debug("index : " + index);
    }

    @Test
    public void bit(){
        int error = 2;
        int kids = 8;
        int female = 4;
        int life = 16;
        int error2 = 32;

        int total = kids + female;
        log.debug(Integer.toBinaryString(total));


        assertTrue((total & kids) > 0 );
        assertTrue((total & female) > 0 );

        assertTrue((total & life) == 0 );
        assertTrue((total & error) == 0 );
        assertTrue((total & error2) == 0 );

    }


    @Test
    public void setTest() {
        Set<String> strings = new HashSet<>();

        String currecntDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));


        log.debug(currecntDate);


        String test = "abc_{TEST}";

        log.debug(test.replaceAll("\\{TEST\\}","AAAA"));

    }



    @Test
    public void collectionsSortTest() {
        List<String> testList = Arrays.asList(
                "goods_view_log_201912",
                "goods_view_log_201910",
                "goods_view_log_201911"
        );

        Collections.sort(testList);
        Collections.reverse(testList);

        for (String test : testList) {
            log.debug(test);
        }

        log.debug("--------------");

        List<String> test2 = testList.subList(0, NumberUtils.min(2, testList.size()));


        for (String test : test2) {
            log.debug(test);
        }

    }


}
