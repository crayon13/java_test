import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

    @Test
    public void cotainsTest() {
        String testString = "https://alp-ads.adoffice.11st.co.kr";

        assertEquals(testString.contains("alp-"), true);
        assertEquals(testString.contains("abc-"), false);
    }

    @Test
    public void formatTest() {
        assertEquals(
            String.format(
                "/mab/%s/%s/%s?age=%s&itemList=%s&gender=%s"
                ,"1", "2", "3", "4", "5", "6"
            ),
            "/mab/1/2/3?age=4&itemList=5&gender=6"
        );

        assertEquals(
            String.format("/mab/%d/%s/%d?age=%s&itemList=%d&gender=%s"
                ,1, "2", 3, "4", 5, "6"
            ),
            "/mab/1/2/3?age=4&itemList=5&gender=6"
        );

    }

    @Test
    public void replaceIfNotEqualsTest() {
        assertEquals(StringUtils.containsAny(",", "A,B,C"),true);

    }


    @Test
    public void stringBuilderTest() {
        StringBuilder sb = new StringBuilder(10);
    }


}
