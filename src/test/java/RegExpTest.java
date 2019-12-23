import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
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

    @Test
    public void aaa() {
        String replacePattern = "good_search-{}";
        String managePattern = "^" + StringUtils.replace(replacePattern, "{}", "[1-9].+\\d$");


        List<String> indices = Arrays.asList("a_good_search-1",
                "good_search-php",
                "good_search-201912112300",
                "good_search-201812112300",
                "good_search-201912122300");

        List<String> targetIndices = indices.stream().filter(index -> index.matches(managePattern)).collect(Collectors.toList());
        log.debug("" + targetIndices.size());
        log.debug(">" + "good_search-201912122300".matches("^good_search-[1-9].+\\d$"));
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
