package apache.common.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StringUtilsTest {

    @Test
    @DisplayName("첫글자 대문자 변환")
    public void capitalize() {
        assertThat( StringUtils.capitalize("abc")).isEqualTo("Abc");
    }

    @Test
    @DisplayName("문자열 내 특정 문자 포함 확인 : indexOf() > 0 대체")
    public void containsAny() {
        assertThat(StringUtils.containsAny(",", "A,B,C")).isTrue();
    }

    @Test
    @DisplayName("empty 가 1개 이상 존재 : StringUtils.isEmpty(a) || StringUtils.isEmpty(b) 대체")
    public void isAnyEmpty() {
        String testStringNull = null;
        String testStringBlank = " ";
        String testStringEmpty = "";
        String testString1 = "1";
        String testString2 = "2";

        assertThat(
            StringUtils.isAnyEmpty(testStringNull, testStringBlank, testStringEmpty, testString1, testString2)
        ).isTrue();
    }

    @Test
    @DisplayName("empty 가 존재하지 않음 : StringUtils.isNotEmpty(a) && StringUtils.isNotEmpty(b) 대체")
    public void isNoneEmpty() {
        String testStringNull = null;
        String testStringBlank = " ";
        String testStringEmpty = "";
        String testString1 = "1";
        String testString2 = "2";
        
        assertThat(
            StringUtils.isNoneEmpty(testStringNull, testStringBlank, testStringEmpty, testString1, testString2)
        ).isFalse();
    }

    @Test
    @DisplayName("blank 가 1개 이상 존재 : StringUtils.isBlank(a) || StringUtils.isBlank(b) 대체")
    public void isAnyBlank() {
        String testStringNull = null;
        String testStringBlank = " ";
        String testStringEmpty = "";
        String testString1 = "1";
        String testString2 = "2";

        assertThat(
            StringUtils.isAnyBlank(testStringNull, testStringBlank, testStringEmpty, testString1, testString2)
        ).isTrue();
    }

    @Test
    @DisplayName("blank 가 존재하지 않음 : StringUtils.isNotBlank(a) && StringUtils.isNotBlank(b) 대체")
    public void isNoneBlank() {
        String testStringNull = null;
        String testStringBlank = " ";
        String testStringEmpty = "";
        String testString1 = "1";
        String testString2 = "2";

        assertThat(
            StringUtils.isNoneBlank(testStringNull, testStringBlank, testStringEmpty, testString1, testString2)
        ).isFalse();
    }

    @Test
    @DisplayName("모두가 blank : StringUtils.isBlank(a) && StringUtils.isBlank(b) 대체")
    public void isAllBlank() {
        String testStringNull = null;
        String testStringBlank = " ";
        String testStringEmpty = "";
        String testString1 = "1";


        assertThat(
                StringUtils.isAllBlank(testStringNull, testStringBlank, testStringEmpty)
        ).isTrue();

        assertThat(
                StringUtils.isAllBlank(testStringNull, testStringBlank, testStringEmpty, testString1)
        ).isFalse();
    }


    @Test
    @DisplayName("구분자 기준 문자 연결 시 loop 돌리지 않아도 됩니다. ㅠㅠ")
    public void join() {
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");

        assertThat( StringUtils.join(testList, ",")).isEqualTo("1,2,3");

        log.debug(StringUtils.joinWith("+", "A", "B", "C"));
    }

    @Test
    public void pad() {
        assertThat(StringUtils.rightPad("abc", 6, "*")).isEqualTo("abc***");
        assertThat(StringUtils.rightPad("abcdef", 6, "*")).isEqualTo("abcdef");
        assertThat(StringUtils.rightPad("abcdefg", 6, "*")).isEqualTo("abcdefg");

        assertThat(StringUtils.leftPad("abc", 6, "0")).isEqualTo("000abc");

        assertThat(StringUtils.leftPad("abc", 4, "0")).isEqualTo("0abc");
        assertThat(StringUtils.leftPad("abc", 4, "0")).isEqualTo("0abc");
    }

    @Test
    public void splitAndJoinTest() {
        String testString = "a,b,c,d e,f";
        String[] testArray = StringUtils.split(testString, ",");
        String actualString = StringUtils.join(testArray, " ");

        assertThat(testArray.length).isEqualTo(5);
        assertThat(actualString).isEqualTo(testString.replaceAll(",", " "));

        log.debug(actualString);
    }

    @Test
    public void replaceTest() {
        String testString = "abcd_{}";
        String expectString = "abcd_efg";

        log.debug(StringUtils.replace(testString, "{}", "efg"));
        assertThat(StringUtils.replace(testString, "{}", "efg")).isEqualTo(expectString);

    }

    @Test
    public void stringFormatExceptionTest() {
        String format = "a:%s, b:%s, c:%s";
        String result = String.format(format, "a", "b", "c", "d");

        log.debug(result);
    }


    @Test
    public void containTest() {
        assertThat("ab,c".contains(",")).isTrue();
        assertThat("ab,c".contains(".")).isFalse();
        assertThat("ab c".contains(" ")).isTrue();
    }

    @Test
    public void defaultIfBlankTest() {
        assertThat(StringUtils.defaultIfBlank("", "A")).isEqualTo("A");
        assertThat(StringUtils.defaultIfBlank(" ", "A")).isEqualTo("A");
        assertThat(StringUtils.defaultIfBlank(null, "A")).isEqualTo("A");

        int a = 1;
        a += 3;
        log .debug("" +a);
    }


    @Test
    public void containsAnyTest() {
        String testValue = "2";
        assertThat(StringUtils.containsAny(testValue, "2", "4")).isTrue();

        String[] mobileAgents = {"iPhone", "iPod", "Android", "IEMobile", "Mobi", "lgtelecom", "PPC"};
        assertThat(StringUtils.containsAny("key:abcd iPhone Test", mobileAgents)).isTrue();
        assertThat(StringUtils.containsAny("key:abcd PPC Test", mobileAgents)).isTrue();
        assertThat(StringUtils.containsAny("key:abcd any Test", mobileAgents)).isFalse();
    }

    @Test
    public void substringBetweenTest() {
        String testValue = ";device_kind=ios;app_ver=ios 2.23.0;";
        String appInfo = StringUtils.substringBetween(testValue, ";app_ver=", ";");

        assertThat(appInfo).isEqualTo("ios 2.23.0");
    }

    @Test
    public void substringAfterTest() {
        String testValue = "APP Name/App Value";

        assertThat(StringUtils.substringAfter(testValue,"/")).isEqualTo("App Value");
        assertThat(StringUtils.substringAfter(testValue,".")).isEqualTo("");

        testValue = "APP Name/App Value/add info";
        assertThat(StringUtils.substringAfter(testValue,"/")).isEqualTo("App Value/add info");

        assertThat(StringUtils.substringAfter("ios 2.23.0", " ")).isEqualTo("2.23.0");
    }

    @Test
    public void substringAfterLastTest() {
        String testValue = "APP Name/App Value/add info";

        assertThat(StringUtils.substringAfterLast(testValue,"/")).isEqualTo("add info");
        assertThat(StringUtils.substringAfterLast(testValue,".")).isEqualTo("");
    }


    @Test
    public void ddd() {
        String requestImageUrl = "https://image.msscdn.com/images/style/detail/10000/100_345.jpg";
        log.debug(String.valueOf(StringUtils.contains(requestImageUrl,"/images/style/detail/")) );
        log.debug(StringUtils.split(requestImageUrl, "/images/style/detail/")[1]);
        log.debug(requestImageUrl.split("/images/style/detail/")[1].split("/")[0]);

        requestImageUrl = StringUtils.split(StringUtils.split(requestImageUrl, "/images/style/detail/")[1], "/")[0];

        log.debug(requestImageUrl);
    }



}
