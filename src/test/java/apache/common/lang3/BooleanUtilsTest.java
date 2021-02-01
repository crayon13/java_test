package apache.common.lang3;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BooleanUtilsTest {
    @Test
    public void toStringTrueFalseTest() {
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
        Integer zero = 0;
        assertThat(BooleanUtils.toBooleanObject(zero)).isFalse();

        Integer trueValue = 1;
        assertThat(BooleanUtils.toBooleanObject(trueValue)).isTrue();

        assertThat(BooleanUtils.toBoolean("Y")).isTrue();
        assertThat(BooleanUtils.toBoolean("N")).isFalse();

        String a = null;
        assertThat(BooleanUtils.toBoolean(a)).isFalse();

        assertThat(BooleanUtils.toBoolean("V")).isFalse();
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


    @Test
    public void toStringTest() {
        assertThat(BooleanUtils.toString(false,"Y", "N")).isEqualTo("N");
        assertThat(BooleanUtils.toString(true,"Y", "N")).isEqualTo("Y");
    }

    @Test
    public void andTest() {
        final boolean saleGoods = false;
        final boolean includeSoldOut = false;
        final boolean exclusiveYn = false;
        final boolean timeSale = false;

        boolean result1 = (saleGoods == false
                && includeSoldOut == false
                && exclusiveYn == false
                && timeSale == false);

        boolean result2 = (!saleGoods
                && !includeSoldOut
                && !exclusiveYn
                && !timeSale);

        assertThat(result1).isEqualTo(result2);
    }

}
