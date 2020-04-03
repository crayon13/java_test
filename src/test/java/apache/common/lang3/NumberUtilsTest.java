package apache.common.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class NumberUtilsTest {

    @Test
    public void minMaxTest() {
        int a = 1;
        int b = 3;

        assertThat(NumberUtils.max(1, 3)).isEqualTo(3);
        assertThat(NumberUtils.min(1, 3)).isEqualTo(1);

        assertThat((null == new Integer(2))).isFalse();
    }

    @Test
    public void tt() {
        Integer a = null;
        assertThat(NumberUtils.max(a, 1)).isEqualTo(1);
    }

}
