package network;

import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WebCrawlerTest {

    @Test
    public void getConentTest() {
        String sourceUrl = "http://www.11st.co.kr/html/evt_nc/20190322_superConcertW_Apl.html";
        String content = new WebCrawler().getConent(sourceUrl);
        assertThat(isNotEmpty(content), is(true));
    }
}
