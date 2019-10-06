/*
* https://secr.tistory.com/207 을 사용했습니다.
* */

package search;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class KoreanProcessor {
    public static String convertEnglishKeyboard(String sourceKoreanText) {
        String resultEng = "";

        for (int index = 0; index < sourceKoreanText.length(); index += 1) {

            /*  한글자씩 읽어들인다. */
            char chars = (char) (sourceKoreanText.charAt(index) - 0xAC00);

            if (chars <= 11172) {
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

            if (chars <= 11172) {
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
