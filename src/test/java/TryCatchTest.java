import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TryCatchTest {

    @Test
    public void returnTest() {
        assertEquals(returnFianlly(),"3");
        assertEquals(returnGeneral(),"3");
        assertEquals(returnTry(),"3");
    }


    public static String returnFianlly() {
        try {
            throw new Exception("A");
        } catch (Exception ex) {
            return "2";
        } finally {
            return "3";
        }
    }


    public static String returnGeneral() {
        String returnString = "1";

        try {
            returnString = "2";
            returnString.indexOf(null);

            return "1";
        } catch (Exception ex) {
             return "3";
        } finally {
            System.out.println("finally!!");
        }
    }

    public static String returnTry() {
        String returnString = "1";

        try {
            returnString = "2";
            returnString.indexOf(null);
            //return returnString;
        } catch (Exception ex) {
            returnString = "3";

            //return returnString;
        } finally {
            //returnString = "4";
            return returnString;
        }

    }

}
