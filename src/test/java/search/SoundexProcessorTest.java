package search;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static search.SoundexProcessor.convert;

public class SoundexProcessorTest {
    @Test
    public void convertTest() {
        assertThat(convert("MaenTuMaen/SeuWeTeuSyeoCheu")).isEqualTo("M535");
        assertThat(convert("KinPal TiSyeoCheu")).isEqualTo("K514");
    }
}
