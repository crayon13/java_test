import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
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



}
