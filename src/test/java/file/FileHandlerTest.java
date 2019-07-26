package file;

import network.WebCrawler;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileHandlerTest {
    private static String testFilePath = "/Users/crayon13/workspace/testFile/";

    @Test
    public void getFileContenSizeTest() {
        String sourceFilePath = testFilePath + "file.txt";
        assertThat(new FileHandler().getFileContenSize(sourceFilePath)).isEqualTo(10L);
    }

    @Test
    public void copyFileByNioTest() {
        String sourceFilePath = testFilePath + "file.txt";
        String destinationFilePath = testFilePath +"/file2.txt";
        boolean isCopy = new FileHandler().copyFileByNio(sourceFilePath, destinationFilePath);
        assertThat(isCopy).isTrue();
    }


    @Test
    public void writeAndCopy() {
        for ( int count = 0 ; count <= 1000; count++ ) {
            String sourceUrl = "http://www.11st.co.kr/html/evt_nc/20190322_superConcertW_Apl.html";
            String content = new WebCrawler().getConent(sourceUrl);

            writeAndCopy(count, content);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getFileContenSizeTest2() {
        String sourceFilePath = testFilePath + "file2.txt";
        for ( int count = 0 ; count <= 1000; count++ ) {
            System.out.println("count : " + count);
            assertThat(new FileHandler().getFileContenSize(sourceFilePath)).isNotEqualTo(0L);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeAndCopy(int count, String content) {
        System.out.println("count : " + count);

        String destinationFilePath = testFilePath + "/20190322_superConcertW_Apl.html";
        String sourceFilePath = destinationFilePath + ".ready";
        String characterSet = "EUC-KR";

        FileHandler fileHandler = new FileHandler();

        fileHandler.writeToFile(content, sourceFilePath, characterSet, characterSet);
        assertThat(fileHandler.getFileContenSize(sourceFilePath)).isGreaterThan(0L);

        fileHandler.copyFileByNio(sourceFilePath, destinationFilePath);
        assertThat(fileHandler.getFileContenSize(destinationFilePath)).isGreaterThan(0L);
    }

}
