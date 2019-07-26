import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringframeworkUtilTest {

    @Test
    public void ObjectUtilsTest() {
        LocalDateTime dRegis = null;

        assertEquals(ObjectUtils.isEmpty(dRegis), org.apache.commons.lang3.ObjectUtils.allNotNull(dRegis));
    }
}
