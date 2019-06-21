import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RegExpTest {

    @Test
    public void imageUrlFindTest() {
        assertThat(isValidImageUrl("http://i.011st.com/a.jpg"), is(true));
        assertThat(isValidImageUrl("https://i.011st.com/a.jpg"), is(true));
        assertThat(isValidImageUrl("http://image.11st.co.kr/a.jpg"), is(true));
        assertThat(isValidImageUrl("https://image.11st.co.kr/a.jpg"), is(true));

        assertThat(isValidImageUrl("http://image2.11st.co.kr/a.jpg"), is(false));
        assertThat(isValidImageUrl("https://iamge2.11st.co.kr/a.jpg"), is(false));

        assertThat(isValidImageUrl("abc/https://image.11st.co.kr/a.jpg"), is(false));
    }

    @Test
    public void replaceTest() {
        try {
            assertThat(replaceImageUrl("http://i.011st.com/a.jpg"), is("/a.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertThat(replaceImageUrl("https://i.011st.com/a.jpg"), is("/a.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertThat(replaceImageUrl("http://image.11st.co.kr/a.jpg"), is("/a.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertThat(replaceImageUrl("https://image.11st.co.kr/a.jpg"), is("/a.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            assertThat(replaceImageUrl("https://image2.11st.co.kr/a.jpg"), is("/a.jpg"));
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
