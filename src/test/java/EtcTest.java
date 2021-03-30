import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class EtcTest {

    @Test
    public void stringSplitAndTrimTest() {
        String testString = "a, b, c";

        assertEquals(testString.split(",")[0].trim(), "a");

    }

    @Test
    public void randomTest() {
        String[] a = new String[2];

        int index = (int)(Math.random() * a.length);
        log.debug("index : " + index);
    }

    @Test
    public void bit(){
        int error = 2;
        int kids = 8;
        int female = 4;
        int life = 16;
        int error2 = 32;

        int total = kids + female;
        log.debug(Integer.toBinaryString(total));


        assertTrue((total & kids) > 0 );
        assertTrue((total & female) > 0 );

        assertTrue((total & life) == 0 );
        assertTrue((total & error) == 0 );
        assertTrue((total & error2) == 0 );

    }


    @Test
    public void setTest() {
        Set<String> strings = new HashSet<>();

        String currecntDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));


        log.debug(currecntDate);


        String test = "abc_{TEST}";

        log.debug(test.replaceAll("\\{TEST\\}","AAAA"));

    }



    @Test
    public void collectionsSortTest() {
        List<String> testList = Arrays.asList(
                "goods_view_log_201910",
                "goods_view_log_201911",
                "goods_view_log_201909"
        );

        Collections.sort(testList);
        for (String test : testList) {
            log.debug(test);
        }
        log.debug("--------------");

        Collections.reverse(testList);

        for (String test : testList) {
            log.debug(test);
        }
        log.debug("--------------");


        List<String> test2 = Arrays.asList("goods_view_log_201912");
//        test2.addAll(testList);
        test2.subList(0, 1);

        for (String test : test2) {
            log.debug(test);
        }
        log.debug("--------------");

        int remainCnt = 0;
        Collections.reverse(testList);
        for (String test : testList) {
            log.debug(test);
        }
        log.debug("--------------");

        List<String> test3 = testList.subList(0, testList.size() - remainCnt);
        for (String test : test3) {
            log.debug(test);
        }



        List<String> a = Arrays.asList("c");


        List<String> b = Arrays.asList("a", "b", "c", "d");
        Collections.shuffle(b);
        log.debug("b size - " + b.subList(0,1).get(0));
        log.debug("b size - " + b.size());


        String[] d = new String[0];
        List<String> e = Arrays.asList(d);


        List<String> c = Arrays.asList("a");
        log.debug("b size - " + c.subList(0,3).size());

    }

    @Test
    public void compareTest() {
        String a = "20191224";
        String b = "20191225";
        String c = "20191226";

        log.debug(a + " : " + b + " - " + a.compareTo(b));
        log.debug(c + " : " + b + " - " + c.compareTo(b));


        HashMap<String, Object> map = new HashMap<>();
        map.put("total", 0);
        Number totalCount = (Number)map.get("total");
    }


    @Test
    public void listCopyTest() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");

        List<String> b = new ArrayList<>();
        b.addAll(a);

        log.debug(a.get(0));
        log.debug(b.get(0));

        a.set(0, "a");
        log.debug(a.get(0));
        log.debug(b.get(0));

        log.debug("a : " + a.size());
        List<String> c = a.subList(0, 2);
        log.debug("c : " + c.size());


        c = a.subList(0, a.size());
        log.debug("c : " + c.size());
    }


    @Test
    public void emptyListTest() {
        List<String> a = Collections.EMPTY_LIST;
        log.debug(a.isEmpty() + ":" + a.size());

        List<String> b = new ArrayList<>();
        log.debug(b.isEmpty() + ":" + b.size());
    }


    @Test
    public void patternTes() {
        String[] indices = ("goods-20200101,goods-20200102,goods-20200103" +
                ",goods-rank-20200102,goods-rank-20200103,goods-rank-20200104" +
                ",goods-stat-20200102,goods-stat-20200103,goods-stat-20200104")
                .split(",");

        String indexNamePrefix = StringUtils.removeEnd("goods-{}", "{}");

        String patternString = "^" + indexNamePrefix + "[0-9]+$";

        Pattern pattern = Pattern.compile(patternString);

        List<String> indexList = Arrays.stream(indices).filter(
                index -> pattern.matcher(index).find()
        ).collect(Collectors.toList());


        for (String index : indexList) {
            log.debug(index);
        }

        log.debug(StringUtils.replaceOnce("a-{}-{}", "{}", "b"));
        log.debug(StringUtils.remove("a-{}-{}", "{}"));


        log.debug("Count : " + StringUtils.countMatches("a-{}-{}", "{}"));

        test("");
        test(null, null);
        test();
    }


    @Test
    public void forBySetTest() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");

        for (String id : set) {
            log.debug(id);
        }
    }

    @Test
    public void stringConcat() {
        String b = null;
        String a1 = "a" + b;
        log.debug(a1);

        String a2 = "a".concat(b);
        log.debug(a2);
    }

    private void test(String... args) {
        if (args == null) {
            log.debug("length : null");
        } else {
            log.debug("length : " + args.length);
        }
    }


    @Test
    public void stringTest() {
        String a = "A";
        String b = a;

        a = "B";

        log.debug(a);
        log.debug(b);

        String c = new String("A");

        log.debug((b.hashCode() == c.hashCode()) + "");


        log.debug(setGoodsStarPercent(13664, 2897) + "..");


    }


    private int setGoodsStarPercent(Integer grade, Integer reviewCount) {

        if (grade == null || reviewCount == null) {
            return 0;
        }

        if (grade == 0 || reviewCount == 0) {
            return 0;
        }


        int returnValue = (int)((double)grade / reviewCount / 5 * 100);

        return returnValue;
    }

    @Test
    public void forSangkyun() {
        String[] keywords = {"오늘만+이+특가", "오늘만+1+1", "오늘만 1 1", "1+1+오늘만", "1 1 오늘만"};

        for (String keyword : keywords) {
            String phase1 = StringUtils.replace(keyword, "+", " ");

            Pattern pattern = Pattern.compile("[0-9] [0-9]");
            Matcher matcher = pattern.matcher(phase1);

            String phase2 = phase1;
            if (matcher.find()) {
                String group = matcher.group();
                phase2 = StringUtils.replace(
                        phase1,
                        group,
                        StringUtils.replace(group, " ", "+")
                );
            }

            log.debug(phase2);
        }


        String a = "1+1";

        log.debug(URLDecoder.decode(a));
    }

    @Test
    public void UrlTest() throws MalformedURLException {
        String urlString = "http://abc.com:8000";
        URL url = new URL(urlString);

        assertEquals("http", url.getProtocol());
        assertEquals("abc.com", url.getHost());
        assertEquals(8000, url.getPort());
    }

    
}
