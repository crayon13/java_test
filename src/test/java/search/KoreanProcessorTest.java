package search;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static search.KoreanProcessor.*;

public class KoreanProcessorTest {

    @Test
    public void convertEnglishKeyboardTest() {
        assertThat(convertEnglishKeyboard("빠름")).isEqualTo("Qkfma");
        assertThat(convertEnglishKeyboard("맨투맨/스웨트셔츠")).isEqualTo("aosxnaos/tmdnpxmtucm");
        assertThat(convertEnglishKeyboard("ㅁ/a/ㅃ/ㅏ")).isEqualTo("a/a/Q/k");
    }

    @Test
    public void convetrEnglishSoundTest() {
        assertThat(convertEnglishSound("맨투맨/스웨트셔츠")).isEqualTo("MaenTuMaen/SeuWeTeuSyeoCheu");
    }

    @Test
    public void convertAtomicWordTest() {
        assertThat(convertAtomicWord("맨투맨/스웨트셔츠")).isEqualTo("ㅁㅐㄴㅌㅜㅁㅐㄴ/ㅅㅡㅇㅞㅌㅡㅅㅕㅊㅡ");
        assertThat(convertAtomicWord("ㅁ/a/ㅃ/ㅏ")).isEqualTo("ㅁ/a/ㅃ/ㅏ");
        assertThat(convertAtomicWord("읽다")).isEqualTo("ㅇㅣㄺㄷㅏ");
    }
}
