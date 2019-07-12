import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class EtcTest {

    @Test
    public void stringSplitAndTrimTest() {
        String testString = "a, b, c";

        Assert.assertThat(testString.split(",")[0].trim(), is("a"));

    }
}
