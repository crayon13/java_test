import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JSONObjectTest {

    @Test
    public void validJSONObjectTest() {
        JSONObject jsonObject = new JSONObject();
        assertEquals(jsonObject.length(), 0);
        assertTrue(jsonObject.isEmpty());
        assertFalse(jsonObject.isNullObject());

        jsonObject.put("a", "A");

        assertEquals(jsonObject.length(), 1);
        assertFalse(jsonObject.isEmpty());

    }
}
