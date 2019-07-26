import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class EtcTest {

    @Test
    public void stringSplitAndTrimTest() {
        String testString = "a, b, c";

        Assert.assertThat(testString.split(",")[0].trim(), is("a"));

    }

    @Test
    public void listLamdaReduceTest(){
        String expectString = "1,2,3,4,5,6,7,8,9";

        List<String> testList = new ArrayList<>();
        for ( int index = 1; index < 10; index++) {
            testList.add("" + index);
        }

        String actualConcatString1 = testList.stream().reduce((s1, s2) -> s1 + "," + s2).get();
        Assert.assertThat(actualConcatString1, is(expectString));

        String actualConcatString2 = StringUtils.join(testList, ",");
        Assert.assertThat(actualConcatString2, is(expectString));
    }

    @Test
    public void listLamdaReduceTest2(){


    }

    private static String getExcludeKeyword(String keyword) {
        String[] excludeKeywords = {"무신사", "musinsa", "우신사", "wusinsa"};

        String result = Arrays.stream(excludeKeywords).anyMatch(excludeKeyword -> keyword.toLowerCase().equals(excludeKeyword.trim())) ? keyword : "";
        return result;
    }


}
