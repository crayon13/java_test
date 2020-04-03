package general;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CollectionTest {
    @Test
    public void List_subList_Test() {
        List<String> givenList = new ArrayList<>();
        givenList.add("a");
        givenList.add("b");
        givenList.add("c");
        Collections.shuffle(givenList);

        int toIndex = 10;
        toIndex = NumberUtils.min(toIndex, givenList.size());

        givenList.subList(0, toIndex);

        for (String given : givenList) {
            log.debug(given);
        }
    }


    @Test
    public void EmptyListTest() {
        List<String> list = Collections.emptyList();
        Collections.shuffle(list);


        int toIndex = NumberUtils.min(4, list.size());
        list.subList(0, toIndex);


    }
}
