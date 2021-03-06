package java8;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class StreamTest {
    @Test
    public void listLamdaReduceTest(){
        String expectString = "1,2,3,4,5,6,7,8,9";

        List<String> testList = new ArrayList<>();
        for ( int index = 1; index < 10; index++) {
            testList.add("" + index);
        }

        String actualConcatString1 = testList.stream().reduce((s1, s2) -> s1 + "," + s2).get();
        assertEquals(actualConcatString1, expectString);

        String actualConcatString2 = StringUtils.join(testList, ",");
        assertEquals(actualConcatString2, expectString);
    }

    @Test
    public void listLamdaReduceTest2(){


    }

    private static String getExcludeKeyword(String keyword) {
        String[] excludeKeywords = {"무신사", "musinsa", "우신사", "wusinsa"};

        String result = Arrays.stream(excludeKeywords).anyMatch(excludeKeyword -> keyword.toLowerCase().equals(excludeKeyword.trim())) ? keyword : "";
        return result;
    }


    @Test
    public void streamTest() {

        List<String> browserList = Arrays.asList(
            "iPhone", "iPod", "Android",
            "IEMobile", "Mobile", "lgtelecom",
            "PPC"
        );

        String browser = "IPHONE";

        boolean isMobileBrowser = browserList.stream().anyMatch(b -> StringUtils.containsIgnoreCase(b, browser));

        assertTrue(isMobileBrowser);
    }


    @Test
    public void filterAndfindFirstTest() {
        assertEquals( Sex.seaarhByCode7("8").name(), "K");
        assertEquals( Sex.seaarhByCode7("4").name(), "F");
        assertEquals( Sex.seaarhByCode7("").name(), "A");
        assertEquals( Sex.seaarhByCode7("d").name(), "A");

        assertEquals( Sex.seaarhByCode("8").name(), "K");
        assertEquals( Sex.seaarhByCode("4").name(), "F");
        assertEquals( Sex.seaarhByCode("").name(), "A");
        assertEquals( Sex.seaarhByCode("d").name(), "A");
    }


    @Test
    public void findNameListByBitTest() {
        assertEquals(SiteKindEnum.findNameListByBit("6").get(0), "MUSINSA");
        assertEquals(SiteKindEnum.findNameListByBit("6").get(1), "WUSINSA");

        assertEquals(SiteKindEnum.findNameListByBit("14").get(0), "MUSINSA");
        assertEquals(SiteKindEnum.findNameListByBit("14").get(1), "WUSINSA");
        assertEquals(SiteKindEnum.findNameListByBit("14").get(2), "PLSINSA");

        assertEquals(SiteKindEnum.findNameListByBit("1").size(), 0);
    }


    private enum Sex {
        K("8", "Kids"),
        F("4", "Female"),
        A("", "All");

        private String code;
        private String description;

        Sex(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public static Sex seaarhByCode7(String code) {
            for ( Sex sex : Sex.values() ) {
                if ( sex.code.equals(code) )  {
                    return sex;
                }
            }

            return Sex.A;
        }


        public static Sex seaarhByCode(String code) {
            return Arrays.stream(Sex.values()).filter(sex -> sex.code.equals(code)).findFirst().orElse(A);
        }

    }

    private enum SiteKindEnum {
        MUSINSA(2, "무신사"),
        WUSINSA(4, "우신사"),
        PLSINSA(8, "플신");

        private int bit;
        private String description;

        SiteKindEnum(int bit, String description) {
            this.bit = bit;
            this.description = description;
        }

        public static List<String> findNameListByBit(String code) {
            return Arrays.stream(SiteKindEnum.values())
                    .filter(siteKindEnum -> (siteKindEnum.bit & NumberUtils.toInt(code)) > 0)
                    .map(siteKindEnum -> siteKindEnum.name())
                    .collect(Collectors.toList());
        }
    }


    @Test
    public void toMapTest() {
        List<ClassA> classAList = new ArrayList<>();
        classAList.add(new ClassA(1, "a"));
        classAList.add(new ClassA(2, "b"));

        Map<String, ClassA> keywordMap = new HashMap<>();

        classAList.stream().map(classA -> {
            keywordMap.put(String.valueOf(classA.getNo()), classA);
            log.debug(String.valueOf(classA.getNo()));
            return keywordMap;
        }).count();

//        log.debug("count : " + count);
        log.debug(">>" + keywordMap.get("1").getName());

        keywordMap.get("1").setName("가");

        log.debug("++" + classAList.get(0).getName());
    }

    @Test
    public void intStreamTest() {
        List<Integer> expectedList = new ArrayList<>();

        log.debug("IntStream.of");
        IntStream.of(0, 10).forEach(
                value -> {
                    expectedList.add(value);
                    log.debug(value + ":" + (100 % 10 == value));
                }
        );

        assertThat(expectedList.size()).isEqualTo(2);

        expectedList.clear();
        log.debug("-----------------------------");
        log.debug("IntStream.range");
        IntStream.range(0, 10).forEach(
                value -> {
                    expectedList.add(value);
                    log.debug(value + ":" + (100 % 10 == value));
                }
        );

        assertThat(expectedList.size()).isEqualTo(10);
    }

    @Test
    public void longStreamNullTest() {
        String styleNosString = null;

        String[] sytleNos = org.apache.commons.lang.StringUtils.split(styleNosString, ",");

        List<Long> styleNoList = Collections.emptyList();

        if (sytleNos != null) {
            styleNoList = Arrays.stream(sytleNos)
                    .mapToLong(NumberUtils::toLong)
                    .boxed()
                    .collect(Collectors.toList());
        }

        log.debug(String.valueOf(styleNoList.size()));
    }

    @Getter
    @Setter
    private class ClassA {
        private int no;
        private String name;

        public ClassA(int no, String name) {
            this.no = no;
            this.name = name;
        }
    }
}


