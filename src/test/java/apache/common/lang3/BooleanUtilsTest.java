package apache.common.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BooleanUtilsTest {
    @Test
    public void toStringTest() {
        assertThat(BooleanUtils.toStringTrueFalse(true)).isEqualTo("true");
    }

    @Test
    public void toStringYesNoTest() {
        assertThat(BooleanUtils.toStringYesNo(true)).isEqualTo("yes");
    }

    @Test
    public void toStringYNTest() {
        assertThat(BooleanUtils.toString(true, "Y", "N")).isEqualTo("Y");
        assertThat(BooleanUtils.toString(false, "Y", "N")).isEqualTo("N");
    }
}
