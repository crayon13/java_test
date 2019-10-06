package search;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static search.CodexProcessor.converMetaphone;
import static search.CodexProcessor.convertSoundex;

public class CodecProcessorTest {
    @Test
    public void convertSoundTest() {
        assertThat(convertSoundex("MaenTuMaen/SeuWeTeuSyeoCheu")).isEqualTo("M535");
        assertThat(convertSoundex("KinPal TiSyeoCheu")).isEqualTo("K514");
    }

    @Test
    public void convertMetaphoneTest() {
        assertThat(converMetaphone("MaenTuMaen/SeuWeTeuSyeoCheu")).isEqualTo("MNTMNSWTSYX");
        assertThat(converMetaphone("KinPal TiSyeoCheu")).isEqualTo("KNPLTSYX");
    }
}
