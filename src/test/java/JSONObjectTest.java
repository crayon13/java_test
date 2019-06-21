import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class JSONObjectTest {

    @Test
    public void validJSONObjectTest() {
        JSONObject jsonObject = new JSONObject();
        assertThat(jsonObject.length(), is(0));
        assertThat(jsonObject.isEmpty(), is(true));
        assertThat(jsonObject.isNullObject(), is(false));

        jsonObject.put("a", "A");

        assertThat(jsonObject.length(), is(1));
        assertThat(jsonObject.isEmpty(), is(false));

    }
}
