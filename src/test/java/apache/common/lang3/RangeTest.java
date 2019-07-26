package apache.common.lang3;

import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeTest {


    @Test
    public void contains() {
        assertThat(Range.between(1, 3).contains(2)).isTrue();
        assertThat(Range.between(1, 3).contains(4)).isFalse();

    }

    @Test
    public void getMaximumMinimum() {
        assertThat(Range.between(1,3).getMaximum()).isEqualTo(3);
        assertThat(Range.between(1,3).getMinimum()).isEqualTo(1);
    }

    @Test
    public void isStartedByEndBy() {
        assertThat(Range.between(1,3).isStartedBy(1)).isTrue();
        assertThat(Range.between(1,3).isEndedBy(3)).isTrue();
    }

    @Test
    public void isBeforeAfter() {
        assertThat(Range.between(1,3).isBefore(4)).isTrue();
        assertThat(Range.between(1,3).isAfter(0)).isTrue();
    }

    @Test
    public void isBeforeRangeAfterRange() {
        assertThat(Range.between(1,3).isBeforeRange(Range.between(4,10))).isTrue();
        assertThat(Range.between(1,3).isAfterRange(Range.between(-1,0))).isTrue();
    }
}
