import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

        assertEquals( BooleanUtils.toString(true, "a", "b"), "a");
        assertEquals( BooleanUtils.toString(false, "a", "b"), "b");
    }

    @Test
    public void collectionUtilsTest() {
        List<String> testList = new ArrayList<>();

    }

    @Test
    public void stringUtilsTest() {
        assertEquals( StringUtils.capitalize("abc"), "Abc");
        assertEquals( StringUtils.defaultString(null, "a"), "a");
        assertEquals( StringUtils.defaultIfEmpty(null, "a"), "a");
        assertEquals( StringUtils.defaultIfEmpty("", "a"), "a");
        assertEquals( StringUtils.isEmpty(""), true);
        assertEquals( StringUtils.isEmpty(null), true);

        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        testList.add("3");

        assertEquals( StringUtils.join(testList, ","), "1,2,3");

        String testStringNull = null;
        String testStringBlank = " ";
        String testString1 = "1";
        String testString2 = "2";
        String testString3 = "3";
        assertEquals(
                StringUtils.isAnyBlank(testStringNull, testStringBlank, testString1, testString2, testString3),
                true );
        assertEquals(
            StringUtils.isNoneBlank(testStringNull, testStringBlank, testString1, testString2, testString3),
            false );


        assertEquals(
            StringUtils.isAnyEmpty(testStringNull, testStringBlank, testString1, testString2, testString3),
            true );
        assertEquals(
            StringUtils.isNoneEmpty(testStringNull, testStringBlank, testString1, testString2, testString3),
            false );


        assertEquals(
            StringUtils.isAnyEmpty(testStringBlank, testString1, testString2, testString3),
            false );
        assertEquals(
            StringUtils.isNoneEmpty(testStringBlank, testString1, testString2, testString3),
            true);

    }

    @Test
    public void rangeTest() {
        int fromNumber = 1;
        int compareNumber = 4;
        int toNumber = 3;

        assertEquals(Range.between(fromNumber,toNumber).contains(compareNumber),
                between(fromNumber, compareNumber, toNumber));

        assertEquals( between(fromNumber, compareNumber, toNumber), false );

        assertThat( Range.between(1,3).getMaximum()).isEqualTo(3);
        assertThat( Range.between(1,3).getMinimum()).isEqualTo(1);;
        assertEquals( Range.between(1,3).isStartedBy(1), true);
        assertEquals( Range.between(1,3).isEndedBy(3), true);
        assertEquals( Range.between(1,3).isAfter(0), true);
        assertEquals( Range.between(1,3).isBefore(4), true);
        assertEquals( Range.between(1,3).isAfterRange(Range.between(-1,0)), true);
        assertEquals( Range.between(1,3).isBeforeRange(Range.between(4,10)), true);
    }

    private static boolean between(int startNumber, int compareNumber, int endNumber) {
        return startNumber <= compareNumber && compareNumber <= endNumber;
    }


    @Test
    public void numberUtilsTest() {
        assertEquals(NumberUtils.toInt("12335465", 0), 12335465);
        assertEquals(NumberUtils.toInt(null, 0), 0);
        assertEquals(NumberUtils.toInt("", 0), 0);
        assertEquals(NumberUtils.toInt("abc", 0), 0);
        assertEquals(NumberUtils.toInt("abc"), 0);

        assertEquals(NumberUtils.isNumber("a"), false);
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

        assertEquals(object1.equals(object2), true);
    }

    @Test
    public void toStringBuilder() {
        MockObject object1 = new MockObject("id", "name", 0);
        MockObject object2 = new MockObject("id", "name", 1);

        assertEquals(object1.toString(), not(object2.toString()));
    }

    @Test
    public void stringToLongTest(){
        String givenString = "1201906194115353690";

        assertEquals(Long.parseLong(givenString), 1201906194115353690L);
        System.out.println(Double.parseDouble(givenString));
    }


    @Test
    public void filenameUtilsTest() {
        assertEquals(FilenameUtils.getExtension("a.txt"), "txt");
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

