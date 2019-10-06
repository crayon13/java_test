package search;
/*
* http://www.javased.com/?api=org.apache.commons.codec.language.Soundex 참고
* */

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Soundex;

public class CodecProcessor {
    private static final Soundex soundex = new Soundex();
    private static final Metaphone methaphone = new Metaphone();

    public static String convertSoundex(String text) {
        return soundex.encode(text);
    }

    public static String converMetaphone(String text) {
        methaphone.setMaxCodeLen(100);
        return methaphone.encode(text);
    }
}