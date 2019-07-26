

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileWriteTest {
    private String sourceFilePath = "/Users/crayon13/Downloads/test/test1.txt";
    private String destinationFilePath2 = "/Users/crayon13/Downloads/test/test2.txt";
    private String destinationFilePath3 = "/Users/crayon13/Downloads/test/test3.txt";
    private String destinationFilePath4 = "/Users/crayon13/Downloads/test/test4.txt";

    @BeforeEach
    public void setUp() {


    }

    @Test
    public void javaIoRenameTest() {
        copyFile(sourceFilePath, destinationFilePath2);
        copyFile(sourceFilePath, destinationFilePath3);

        File srcFile = new File(destinationFilePath3);
        File dstFile = new File(destinationFilePath4);
        srcFile.renameTo(dstFile);

    }

    public boolean copyFile(String srcFilePath, String dstnFilePath)
    {
        boolean bRet = true;

        File scrFile = new File(srcFilePath);
        File dstnFile = new File(dstnFilePath);
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

        try
        {
            sourceChannel = new FileInputStream(scrFile).getChannel();
            destinationChannel = new FileOutputStream(dstnFile).getChannel();

            sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
        }
        catch (FileNotFoundException e)
        {
            //������ ���� ����� �����ϸ� 0����Ʈ ���� ������ �����.
            FileChannel newFileChannel = null;
            try {
                newFileChannel = new FileOutputStream(scrFile).getChannel();
            } catch (FileNotFoundException e1) {
                e.printStackTrace();
                bRet = false;
            } catch (IOException e1) {
                e.printStackTrace();
                bRet = false;
            }
            finally
            {
                try
                {
                    newFileChannel.close();
                }
                catch (Exception e1)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            bRet = false;
        }
        finally
        {
            try
            {
                sourceChannel.close();
                destinationChannel.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return bRet;
    }
}
