import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void removeReferer() {
        String given = "http://ho.local.musinsa.com/web/";
        String actual = getMenuUrl(given);

        assertThat(actual).isEqualTo("/web/");

        given = "http://ho.local.musinsa.com:8080/web/";
        actual = getMenuUrl(given);

        assertThat(actual).isEqualTo("/web/");
    }


    String refererPattern = "com";

    private String getMenuUrl(String referer) {
        try {
            URL url = new URL(referer);
            log.debug("URL Path: {} ", url.getPath());
            if (!url.getHost().endsWith(refererPattern)) {
                throw new MalformedURLException("not allow pattern");
            }

            return url.getPath();
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }

        return StringUtils.EMPTY;
    }

    private String removeReferer(String referer) {

        String menuUrl = StringUtils.EMPTY;
        String regex = "(?<=" + "com" + ")(\\W|\\w)+$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(referer);

        while (matcher.find()) {
            menuUrl = matcher.group();
        }
        return menuUrl;
    }


    @Test
    public void replaceHtmlTagTest() {
        String given = "<table><tbody><tr><td><img src=\"http://image.musinsa.com/mfile_s01/_old/old_fr_4e8882ec79c859630.jpg\" alt=\"2011 FALL COLLECTION START UP!!\"></td></tr></tbody></table>";
        assertThat(replaceHtmlTag(given)).isEqualTo("");
    }


    private String replaceHtmlTag(String content) {
        return content.replaceAll("<[^>]*>", " ")
                .replaceAll("<!--", " ").replaceAll("-->", " ")
                .replace("&nbsp;","").replaceAll("\\s\\s+", " ").replace("본문 글 출력","").trim();
    }
}
