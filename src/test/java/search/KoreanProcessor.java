/*
* https://secr.tistory.com/207 을 사용했습니다.
* */

package search;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class KoreanProcessor {
    /* **********************************************
     * 자음 모음 분리
     * 설연수 -> ㅅㅓㄹㅇㅕㄴㅅㅜ,    바보 -> ㅂㅏㅂㅗ
     * **********************************************/
    /** 초성 - 가(ㄱ), 날(ㄴ) 닭(ㄷ) */
    public static char[] choSungUnicodes = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138,
        0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
        0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
    /** 중성 - 가(ㅏ), 야(ㅑ), 뺨(ㅑ)*/
    public static char[] jungSungUicodes = { 0x314f, 0x3150, 0x3151, 0x3152,
        0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a,
        0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162,
        0x3163 };
    /** 종성 - 가(없음), 갈(ㄹ) 천(ㄴ) */
    public static char[] jongSungUnicodes = { 0x0000, 0x3131, 0x3132, 0x3133,
        0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c,
        0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145,
        0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };


    /* **********************************************
     * 알파벳으로 변환
     * 설연수 -> tjfdustn, 멍충 -> ajdcnd
     * **********************************************/
    /** 초성 - 가(ㄱ), 날(ㄴ) 닭(ㄷ) */
    public static String[] choSungEnglishKeyboards = { "r", "R", "s", "e", "E",
        "f", "a", "q", "Q", "t", "T", "d", "w",
        "W", "c", "z", "x", "v", "g" };

    /** 중성 - 가(ㅏ), 야(ㅑ), 뺨(ㅑ)*/
    public static String[] jungSungEnglishKeyboards = { "k", "o", "i", "O",
        "j", "p", "u", "P", "h", "hk", "ho", "hl",
        "y", "n", "nj", "np", "nl", "b", "m", "ml",
        "l" };

    /** 종성 - 가(없음), 갈(ㄹ) 천(ㄴ) */
    public static String[] jongSungEnglishKeyboards = { "", "r", "R", "rt",
        "s", "sw", "sg", "e", "f", "fr", "fa", "fq",
        "ft", "fx", "fv", "fg", "a", "q", "qt", "t",
        "T", "d", "w", "c", "z", "x", "v", "g" };

    /** 단일 자음 - ㄱ,ㄴ,ㄷ,ㄹ... (ㄸ,ㅃ,ㅉ은 단일자음(초성)으로 쓰이지만 단일자음으론 안쓰임) */
    public static String[] singleJaumEnglishKeyboards = { "r", "R", "rt",
        "s", "sw", "sg", "e","E" ,"f", "fr", "fa", "fq",
        "ft", "fx", "fv", "fg", "a", "q","Q", "qt", "t",
        "T", "d", "w", "W", "c", "z", "x", "v", "g" };
//$LCtable = array("ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ");
//$LCetable = array("k","kk","n","d","tt","l","m","b","pp","s","ss","","j","jj","ch","k","t","p","h");

//$MVtable = array("ㅏ","ㅐ","ㅑ","ㅒ","ㅓ","ㅔ","ㅕ","ㅖ","ㅗ","ㅘ","ㅙ","ㅚ","ㅛ","ㅜ","ㅝ","ㅞ","ㅟ","ㅠ","ㅡ","ㅢ","ㅣ");
//$MVetable = array("a","ae","ya","yae","eo","e","yeo","ye","o","wa","wae","oe","yo","u","wo","we","wi","yu","eu","ui","i");

//$TCtable = array("","ㄱ","ㄲ","ㄳ","ㄴ","ㄵ","ㄶ","ㄷ","ㄹ","ㄺ","ㄻ","ㄼ","ㄽ","ㄾ","ㄿ","ㅀ","ㅁ","ㅂ","ㅄ","ㅅ","ㅆ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ");
//$TCetable = array("","g","kk","k","n","n","n","t","l","l","l","l","l","l","l","l","m","p","p","s","ss","ng","j","ch","k","t","p","h");

    public static String convertEnglishKeyboard(String sourceKoreanText) {
        String resultEng = "";

        for (int index = 0; index < sourceKoreanText.length(); index += 1) {

            /*  한글자씩 읽어들인다. */
            char chars = (char) (sourceKoreanText.charAt(index) - 0xAC00);

            if (chars >= 0 && chars <= 11172) {
                /* A. 자음과 모음이 합쳐진 글자인경우 */
                /* 알파벳으로 */
                resultEng += Korean.CHOSUNG.convertEnglishKeyboard(chars);
                resultEng += Korean.JUNGSUNG.convertEnglishKeyboard(chars);
                resultEng += Korean.JONGSUNG.convertEnglishKeyboard(chars);

            } else {
                /* B. 한글이 아니거나 자음만 있을경우 */

                /* 알파벳으로 */
                if (chars >= 34097 && chars <= 34126) {
                    /* 단일자음인 경우 */
                    resultEng += Korean.SINGLE_JAUM.convertEnglishKeyboard(chars);
                } else if (chars >= 34127 && chars <= 34147) {
                    /* 단일모음인 경우 */
                    resultEng += Korean.SINGLE_MOUM.convertEnglishKeyboard(chars);
                } else {
                    /* 알파벳인 경우 */
                    resultEng += ((char) (chars + 0xAC00));
                }
            }//if

        }//for

        log.debug("============ result ==========");
        log.debug("단어     : {}", sourceKoreanText);
        log.debug("알파벳   : {}", resultEng);

        return resultEng;
    }

    public static String convertEnglishSound(String sourceKoreanText) {
        String result = "";

        for (int index = 0; index < sourceKoreanText.length(); index+= 1) {

            /*  한글자씩 읽어들인다. */
            char chars = (char) (sourceKoreanText.charAt(index) - 0xAC00);

            if (chars >= 0 && chars <= 11172) {
                /* A. 자음과 모음이 합쳐진 글자인경우 */
                result += StringUtils.capitalize(
                    Korean.CHOSUNG.convertEnglishSound(chars)
                        + Korean.JUNGSUNG.convertEnglishSound(chars)
                );
                if ( Korean.JONGSUNG.getCharIndex(chars) != 0x0000 ) {
                    result += Korean.JONGSUNG.convertEnglishSound(chars);
                }
            } else {
                /* B. 한글이 아니거나 자음만 있을경우 */
                /* 자음분리 */
                result += ((char)(chars + 0xAC00));
            }//if

        }//for

        log.debug("============ result ==========");
        log.debug("단어     : {}", sourceKoreanText);
        log.debug("자음분리 : {}", result);

        return result;
    }

    public static String convertAtomicWord(String sourceKoreanText) {
        String result = "";

        for (int index = 0; index < sourceKoreanText.length(); index+= 1) {

            /*  한글자씩 읽어들인다. */
            char chars = (char) (sourceKoreanText.charAt(index) - 0xAC00);

            if (chars >= 0 && chars <= 11172) {
                /* A. 자음과 모음이 합쳐진 글자인경우 */
                result += Korean.CHOSUNG.convertAtomic(chars);
                result += Korean.JUNGSUNG.convertAtomic(chars);
                if ( Korean.JONGSUNG.getCharIndex(chars) != 0x0000 ) {
                    result += Korean.JONGSUNG.convertAtomic(chars);
                }
            } else {
                /* B. 한글이 아니거나 자음만 있을경우 */
                /* 자음분리 */
                result += ((char)(chars + 0xAC00));
            }//if

        }//for

        log.debug("============ result ==========");
        log.debug("단어     : {}", sourceKoreanText);
        log.debug("자음분리 : {}", result);

        return result;
    }

}
