package java8;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
