import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegExpTest {

    @Test
    public void imageUrlFindTest() {
        assertTrue(isValidImageUrl("http://i.011st.com/a.jpg"));
        assertTrue(isValidImageUrl("https://i.011st.com/a.jpg"));
        assertTrue(isValidImageUrl("http://image.11st.co.kr/a.jpg"));
        assertTrue(isValidImageUrl("https://image.11st.co.kr/a.jpg"));

        assertFalse(isValidImageUrl("http://image2.11st.co.kr/a.jpg"));
        assertFalse(isValidImageUrl("https://iamge2.11st.co.kr/a.jpg"));

        assertFalse(isValidImageUrl("abc/https://image.11st.co.kr/a.jpg"));
    }

    @Test
    public void replaceTest() {
        try {
            assertEquals(replaceImageUrl("http://i.011st.com/a.jpg"), "/a.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertEquals(replaceImageUrl("https://i.011st.com/a.jpg"),"/a.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertEquals(replaceImageUrl("http://image.11st.co.kr/a.jpg"),"/a.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertEquals(replaceImageUrl("https://image.11st.co.kr/a.jpg"),"/a.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            assertEquals(replaceImageUrl("https://image2.11st.co.kr/a.jpg"),"/a.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean isValidImageUrl(String checkString) {
        Pattern urlPattern = Pattern.compile("^(http|https)://(image.11st.co.kr|i.011st.com)/");
        Matcher urlMatcher = urlPattern.matcher(checkString);

        return urlMatcher.find();
    }

    private String replaceImageUrl(String checkString) throws Exception {
        Pattern urlPattern = Pattern.compile("^(http|https)://(image.11st.co.kr|i.011st.com)/");
        Matcher urlMatcher = urlPattern.matcher(checkString);

        if ( urlMatcher.find() ) {
            return urlMatcher.replaceFirst("/");
        } else {
            throw new Exception("aaa");
        }
    }
}
