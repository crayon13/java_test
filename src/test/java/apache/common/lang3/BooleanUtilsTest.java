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

    @Test
    public void toBooleanObjectTest() {
        Integer nullValue = null;
        assertThat(BooleanUtils.toBooleanObject(nullValue)).isFalse();

        Integer zero = 0;
        assertThat(BooleanUtils.toBooleanObject(zero)).isFalse();

        Integer trueValue = 1;
        assertThat(BooleanUtils.toBooleanObject(trueValue)).isTrue();
    }

    @Test
    public void isFalseTest() {
        assertThat(BooleanUtils.isFalse(null)).isFalse();
        assertThat(BooleanUtils.isFalse(false)).isTrue();
        assertThat(BooleanUtils.isFalse(true)).isFalse();
    }

    @Test
    public void isTrueTest() {
        assertThat(BooleanUtils.isTrue(null)).isFalse();
        assertThat(BooleanUtils.isTrue(false)).isFalse();
        assertThat(BooleanUtils.isTrue(true)).isTrue();
    }

    @Test
    public void isNotTrueTest() {
        assertThat(BooleanUtils.isNotTrue(null)).isTrue();
        assertThat(BooleanUtils.isNotTrue(false)).isTrue();
        assertThat(BooleanUtils.isNotTrue(true)).isFalse();
    }
}
