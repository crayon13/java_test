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
        String testString = String.format("/mab/%s/%s/%s?age=%s&itemList=%s&gender=%s"
                ,"1", "2", "3", "4", "5", "6");
        assertEquals(testString, "/mab/1/2/3?age=4&itemList=5&gender=6");
    }

    @Test
    public void replaceIfNotEqualsTest() {
        assertEquals(StringUtils.containsAny(",", "A,B,C"),true);

    }


}
