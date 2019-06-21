import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRewriteTest {
    URL testUrl;
    URL expectUrl;

    @Before
    public void setUp() {
        testUrl = null;
        expectUrl = null;
    }


    @Test
    public void rol_11RoL_fixSz() {
        setTestUrl("http://i.011st.com/11RoL/pd/18/3/2/6/5/5/7/eSSGO/SD2129326557.jpg,/pd/2018/11/JiziQ/E_460.png;g=1;off=%2B15%2B15;fixSz=720x360");
        setExpectUrl("http://nrol.11st.co.kr/11st/rols.jpg?bdSz=720x360&prdImgSz=720x360&emblImgSz=130x130!&prdImg=/pd/18/3/2/6/5/5/7/eSSGO/SD2129326557.jpg&sz=720x360&emblImg=/pd/2018/11/JiziQ/E_460.png&g=1&off=%2B15%2B15");

        Assert.assertTrue("resultUrl : " + getResultUrl(testUrl), compare());
    }

    @Test
    public void rol_11RoL_no_fixSz() {
        setTestUrl("http://i.011st.com/11RoL/pd/18/3/2/6/5/5/7/eSSGO/SD2129326557.jpg,/pd/2018/11/JiziQ/E_460.png;g=1;off=%2B15%2B15");
        setExpectUrl("http://nrol.11st.co.kr/11st/rols.jpg?bdSz=600x600&prdImgSz=600x600&emblImgSz=130x130!&prdImg=/pd/18/3/2/6/5/5/7/eSSGO/SD2129326557.jpg&emblImg=/pd/2018/11/JiziQ/E_460.png&g=1&off=%2B15%2B15");

        Assert.assertTrue("resultUrl : " + getResultUrl(testUrl), compare());
    }

    private boolean compare() {
        return expectUrl.toString().equals(getResultUrl(testUrl));

    }

    private void setTestUrl(String spec) {
        try {
            testUrl = new URL(spec);
        } catch (Exception e) {
        }
    }

    private void setExpectUrl(String spec) {
        try {
            expectUrl = new URL(spec);
        } catch (MalformedURLException e) {
        }
    }

    private String getResultUrl(URL url) {

        for ( UrlRewriteRule urlRewriteRule : UrlRewriteRule.values() ) {
            String resultUrl = urlRewriteRule.getResultUrl( url.getPath() );
            if ( StringUtils.isNotBlank(resultUrl) ) {
                return resultUrl;
            }
        }

        return "";
    }

    private enum UrlRewriteRule {
        rol_11RoL_fixSz("^\\/11RoL\\/(.+)\\,(.+);g=([0-9]+)[;]?(off=[^;]+)?;fixSz=([0-9]+x[0-9]+)"
                , "http://nrol.11st.co.kr/11st/rols.jpg?bdSz=$5&prdImgSz=$5&emblImgSz=130x130!&prdImg=/$1&sz=$5&emblImg=$2&g=$3&$4"),
        rol_11RoL_no_fixSz("^\\/11RoL\\/(.+)\\,(.+);g=([0-9]+)[;]?(off=[^;]+)?"
                , "http://nrol.11st.co.kr/11st/rols.jpg?bdSz=600x600&prdImgSz=600x600&emblImgSz=130x130!&prdImg=/$1&emblImg=$2&g=$3&$4");

        protected final String findPattern;
        protected final String rewriteUrl;


        UrlRewriteRule(String findPattern, String rewriteUrl) {
            this.findPattern = findPattern;
            this.rewriteUrl = rewriteUrl;
        }

        public String getResultUrl(String originalUrl) {
            Pattern pattern = Pattern.compile(findPattern);
            Matcher matcher = pattern.matcher(originalUrl);

            String resultUrl = "";

            if ( matcher.find() ) {
                resultUrl = rewriteUrl;
                for ( int groupIdx = 1; groupIdx <= matcher.groupCount() ; groupIdx++) {
                    resultUrl = resultUrl.replace("$" + groupIdx, matcher.group(groupIdx));
                }
            }

            return resultUrl;
        }
    }

}
