import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;


public class ApacheCommonTest {

    @Test
    public void booleanUtilsTest() {
        System.out.println("---");

        assertTrue(BooleanUtils.toBoolean("true"));
        assertFalse(BooleanUtils.toBoolean("false"));
        assertTrue(BooleanUtils.toBoolean("t"));
        assertFalse(BooleanUtils.toBoolean("f"));
        assertTrue(BooleanUtils.toBoolean("Y"));
        assertFalse(BooleanUtils.toBoolean("N"));
        assertTrue(BooleanUtils.toBoolean("y"));
        assertFalse(BooleanUtils.toBoolean("n"));
        assertTrue(BooleanUtils.toBoolean("YES"));
        assertFalse(BooleanUtils.toBoolean("NO"));
        assertTrue(BooleanUtils.toBoolean("yes"));
        assertFalse(BooleanUtils.toBoolean("no"));
        assertTrue(BooleanUtils.toBoolean("ON"));
        assertFalse(BooleanUtils.toBoolean("OFF"));
        assertTrue(BooleanUtils.toBoolean("on"));
        assertFalse(BooleanUtils.toBoolean("off"));

        assertTrue("yes".equals(BooleanUtils.toStringYesNo(true)));
        assertTrue("no".equals(BooleanUtils.toStringYesNo(false)));

        assertThat( BooleanUtils.toString(true, "a", "b"), is("a"));
        assertThat( BooleanUtils.toString(false, "a", "b"), is("b"));
    }

    @Test
    public void collectionUtilsTest() {
        List<String> testList = new ArrayList<>();

    }

    @Test
    public void stringUtilsTest() {
        assertThat( StringUtils.capitalize("abc"), is("Abc") );
        assertThat( StringUtils.defaultString(null, "a"), is("a"));
        assertThat( StringUtils.defaultIfEmpty(null, "a"), is("a"));
        assertThat( StringUtils.defaultIfEmpty("", "a"), is("a"));
        assertThat( StringUtils.isEmpty(""), is(true));
        assertThat( StringUtils.isEmpty(null), is(true));

        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");

        assertThat( StringUtils.join(testList, ","), is("1,2,3"));

        String testString1 = "";
        String testString2 = "1";
        String testString3 = "2";
        String testString4 = "3";
        assertThat(
                StringUtils.isAnyEmpty(testString1, testString2, testString3, testString3),
                is(true) );

    }

    @Test
    public void rangeTest() {
        int fromNumber = 1;
        int compareNumber = 4;
        int toNumber = 3;

        assertThat(Range.between(fromNumber,toNumber).contains(compareNumber),
                is(between(fromNumber, compareNumber, toNumber)) );

        assertThat( between(fromNumber, compareNumber, toNumber), is(false) );

        assertThat( Range.between(1,3).getMaximum(), is(3));
        assertThat( Range.between(1,3).getMinimum(), is(1));
        assertThat( Range.between(1,3).isStartedBy(1), is(true));
        assertThat( Range.between(1,3).isEndedBy(3), is(true));
        assertThat( Range.between(1,3).isAfter(0), is(true));
        assertThat( Range.between(1,3).isBefore(4), is(true));
        assertThat( Range.between(1,3).isAfterRange(Range.between(-1,0)), is(true));
        assertThat( Range.between(1,3).isBeforeRange(Range.between(4,10)), is(true));
    }

    private static boolean between(int startNumber, int compareNumber, int endNumber) {
        return startNumber <= compareNumber && compareNumber <= endNumber;
    }


    @Test
    public void numberUtilsTest() {
        assertThat(NumberUtils.toInt("12335465", 0), is(12335465));
        assertThat(NumberUtils.toInt(null, 0), is(0));
        assertThat(NumberUtils.toInt("", 0), is(0));
        assertThat(NumberUtils.toInt("abc", 0), is(0));
        assertThat(NumberUtils.toInt("abc"), is(0));

        assertThat(NumberUtils.isNumber("a"), is(false));
    }

    @Test
    public void reflectionTest() {
        Class description = null;
        try {
            description = Class.forName("classes.Description");

            Method[] methods = description.getMethods();

            for ( Method method : methods) {


                if ( method.getName().equals("setName") ) {
                    System.out.println(method.toString());
                    method.invoke(description.newInstance(), "!" );
                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void dateUtilsTest() {
        String dateString = "2019/01/11 23:59";
        try {
            Date date = DateUtils.parseDateStrictly(dateString, "yyyy/MM/dd HH:mm");
            System.out.println(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            Date date = DateUtils.parseDateStrictly(dateString + ":59", "yyyy/MM/dd HH:mm:ss");
            System.out.println(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void equalsBuilderTest() {
        MockObject object1 = new MockObject("id", "name", 0);
        MockObject object2 = new MockObject("id", "name", 1);

        assertThat(object1.equals(object2), is(true));
    }

    @Test
    public void toStringBuilder() {
        MockObject object1 = new MockObject("id", "name", 0);
        MockObject object2 = new MockObject("id", "name", 1);

        assertThat(object1.toString(), not(object2.toString()));
    }

    @Test
    public void stringToLongTest(){
        String givenString = "1201906194115353690";

        assertThat(Long.parseLong(givenString), is(1201906194115353690L) );
        System.out.println(Double.parseDouble(givenString));
    }



    private class MockObject {
        MockObject(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        String id;
        String name;
        int age;

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            MockObject that = (MockObject)object;

            return new EqualsBuilder()
                    .append(id, that.id)
                    .append(name,that.name)
                    .isEquals();
        }

        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", id)
                    .append("name", name)
                    .toString();
        }
    }

}

