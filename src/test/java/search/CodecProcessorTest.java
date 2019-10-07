package search;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static search.CodecProcessor.converMetaphone;
import static search.CodecProcessor.convertSoundex;

public class CodecProcessorTest {
    @Test
    public void convertSoundTest() {
        assertThat(convertSoundex("MaenTuMaen/SeuWeTeuSyeoCheu")).isEqualTo("M535");
        assertThat(convertSoundex("KinPal TiSyeoCheu")).isEqualTo("K514");
        assertThat(convertSoundex("032c")).isEqualTo("S000");
        assertThat(convertSoundex("10corsocomo")).isEqualTo("K422");

    }

    @Test
    public void convertMetaphoneTest() {
        assertThat(converMetaphone("MaenTuMaen/SeuWeTeuSyeoCheu")).isEqualTo("MNTMNSWTSYX");
        assertThat(converMetaphone("KinPal TiSyeoCheu")).isEqualTo("KNPLTSYX");
        assertThat(converMetaphone("10corsocomo")).isEqualTo("KLSKM");

    }
}
