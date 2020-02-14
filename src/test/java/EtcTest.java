import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
                "goods_view_log_201910",
                "goods_view_log_201911",
                "goods_view_log_201909"
        );

        Collections.sort(testList);
        for (String test : testList) {
            log.debug(test);
        }
        log.debug("--------------");

        Collections.reverse(testList);

        for (String test : testList) {
            log.debug(test);
        }
        log.debug("--------------");


        List<String> test2 = Arrays.asList("goods_view_log_201912");
//        test2.addAll(testList);
        test2.subList(0, 1);

        for (String test : test2) {
            log.debug(test);
        }
        log.debug("--------------");

        int remainCnt = 0;
        Collections.reverse(testList);
        for (String test : testList) {
            log.debug(test);
        }
        log.debug("--------------");

        List<String> test3 = testList.subList(0, testList.size() - remainCnt);
        for (String test : test3) {
            log.debug(test);
        }



        List<String> a = Arrays.asList("c");


        List<String> b = Arrays.asList("a", "b", "c", "d");
        Collections.shuffle(b);
        log.debug("b size - " + b.subList(0,1).get(0));
        log.debug("b size - " + b.size());


        String[] d = new String[0];
        List<String> e = Arrays.asList(d);


        List<String> c = Arrays.asList("a");
        log.debug("b size - " + c.subList(0,3).size());

    }

    @Test
    public void compareTest() {
        String a = "20191224";
        String b = "20191225";
        String c = "20191226";

        log.debug(a + " : " + b + " - " + a.compareTo(b));
        log.debug(c + " : " + b + " - " + c.compareTo(b));


        HashMap<String, Object> map = new HashMap<>();
        map.put("total", 0);
        Number totalCount = (Number)map.get("total");
    }


}
