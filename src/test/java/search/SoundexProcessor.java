package search;
/*
* http://www.javased.com/?api=org.apache.commons.codec.language.Soundex 참고
* */
import org.apache.commons.codec.language.Soundex;

public class SoundexProcessor {
    private static final Soundex soundex=new Soundex();

    public static String convert(String text) {
        return soundex.encode(text);
    }
}