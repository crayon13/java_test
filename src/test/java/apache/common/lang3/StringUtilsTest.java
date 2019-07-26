package apache.common.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {

    @Test
    @DisplayName("첫글자 대문자 변환")
    public void capitalize() {
        assertThat( StringUtils.capitalize("abc"), is("Abc") );
    }

    @Test
    @DisplayName("문자열 내 특정 문자 포함 확인 : indexOf() > 0 대체")
    public void containsAny() {
        assertThat(StringUtils.containsAny(",", "A,B,C"), is(true));
    }

    @Test
    @DisplayName("empty 가 1개 이상 존재 : StringUtils.isEmpty(a) || StringUtils.isEmpty(b) 대체")
    public void isAnyEmpty() {
        String testString = "";
        String testString1 = "1";
        String testString2 = "2";
        String testString3 = "3";

        assertThat(
            StringUtils.isAnyEmpty(testString, testString1, testString2, testString3),
            is(true) );
    }

    @Test
    @DisplayName("empty 가 존재하지 않음 : StringUtils.isNotEmpty(a) && StringUtils.isNotEmpty(b) 대체")
    public void isNoneEmpty() {
        String testString = "";
        String testString1 = "1";
        String testString2 = "2";
        String testString3 = "3";

        assertThat(
            StringUtils.isNoneEmpty(testString, testString1, testString2, testString3),
            is(false) );
    }

    @Test
    @DisplayName("blank 가 1개 이상 존재 : StringUtils.isBlank(a) || StringUtils.isBlank(b) 대체")
    public void isAnyBlank() {
        String testString = " ";
        String testString1 = "1";
        String testString2 = "2";
        String testString3 = "3";

        assertThat(
            StringUtils.isAnyBlank(testString, testString1, testString2, testString3),
            is(true) );
    }

    @Test
    @DisplayName("blank 가 존재하지 않음 : StringUtils.isNotBlank(a) && StringUtils.isNotBlank(b) 대체")
    public void isNoneBlank() {
        String testString = " ";
        String testString1 = "1";
        String testString2 = "2";
        String testString3 = "3";

        assertThat(
            StringUtils.isNoneBlank(testString, testString1, testString2, testString3),
            is(false) );
    }


    @Test
    @DisplayName("구분자 기준 문자 연결 시 loop 돌리지 않아도 됩니다. ㅠㅠ")
    public void join() {
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");

        assertThat( StringUtils.join(testList, ","), is("1,2,3"));
    }

    @Test
    public void pad() {
        assertThat(StringUtils.rightPad("abc", 6, "*"), is("abc***"));
        assertThat(StringUtils.rightPad("abcdef", 6, "*"), is("abcdef"));
        assertThat(StringUtils.rightPad("abcdefg", 6, "*"), is("abcdefg"));

        assertThat(StringUtils.leftPad("abc", 6, "0"), is("000abc"));

        assertThat(StringUtils.leftPad("abc", 4, "0"), is("0abc"));
        assertEquals(StringUtils.leftPad("abc", 4, "0"), "0abc");
    }
}
