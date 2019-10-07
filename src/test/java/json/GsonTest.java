package json;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class GsonTest {
    @Test
    public void simpleTest() {
        JsonObject rootNode = new JsonObject();

        JsonObject obj1 = new JsonObject();
        obj1.addProperty("name1","val1");
        obj1.addProperty("name2","val2");

        rootNode.add("obj1", obj1);

        JsonObject obj2 = new JsonObject();
        obj2.addProperty("name3","val3");
        obj2.addProperty("name4","val4");

        rootNode.add("obj2", obj2);

        JsonObject obj3 = new JsonObject();
        obj3.addProperty("name5","val5");
        obj3.addProperty("name6","val6");

        rootNode.add("obj3", obj3);


        log.debug(String.valueOf(rootNode));

    }

}
