package search;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static search.KoreanProcessor.*;

@Slf4j
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


    @Test
    public void test() {
        String word = "ㄱㅏa가나다라";
        int aa = 0xAC00;


        for ( int index = 0; index < word.length() ; index++) {

            int init = word.charAt(index);
            int initUnicode = init - aa;
            char chars = word.charAt(index);
            log.debug(">" + chars + ":" + init + ":" + initUnicode+ ":" + aa + "-"
                + ( initUnicode / 21/ 28) + ":" + (initUnicode % (21 * 28) % 28 )

            );
        }

        char b = 1;
    }

}
