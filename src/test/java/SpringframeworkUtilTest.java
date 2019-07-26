import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;

public class SpringframeworkUtilTest {

    @Test
    public void ObjectUtilsTest() {
        LocalDateTime dRegis = null;

        Assert.assertThat(ObjectUtils.isEmpty(dRegis), is(org.apache.commons.lang3.ObjectUtils.allNotNull(dRegis)));
    }
}
