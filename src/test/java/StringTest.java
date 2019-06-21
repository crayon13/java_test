import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class StringTest {

    @Test
    public void cotainsTest() {
        String testString = "https://alp-ads.adoffice.11st.co.kr";

        Assert.assertThat(testString.contains("alp-"), is(true));
        Assert.assertThat(testString.contains("abc-"), is(false));
    }

    @Test
    public void formatTest() {
        String testString = String.format("/mab/%s/%s/%s?age=%s&itemList=%s&gender=%s"
                ,"1", "2", "3", "4", "5", "6");
        Assert.assertThat(testString, is("/mab/1/2/3?age=4&itemList=5&gender=6"));
    }

    @Test
    public void replaceIfNotEqualsTest() {
        Assert.assertThat(StringUtils.containsAny(",", "A,B,C"), is(true));

    }


}
