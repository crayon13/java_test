package apache.common.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class BooleanUtilsTest {
    @Test
    public void toStringTest() {
        Assert.assertThat(BooleanUtils.toStringTrueFalse(true), is("true"));
    }

    @Test
    public void toStringYesNoTest() {
        Assert.assertThat(BooleanUtils.toStringYesNo(true), is("yes"));
    }
}
