package file;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class FileHandler {

    public boolean writeToFile(String sourceText, String targetFilePath, String targetFileCaracterSet, String sourceTextCharacterSet) {
        FileOutputStream fos;
        BufferedWriter out = null;

        try  {
            if ( StringUtils.isEmpty(sourceText) ) {
                throw new Exception("Html String is Empty!!");
            }

            fos = new FileOutputStream(targetFilePath);

            out = new BufferedWriter( new OutputStreamWriter( fos, targetFileCaracterSet ) );
            out.write( new String(sourceText.getBytes(sourceTextCharacterSet), targetFileCaracterSet) );

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("TransmitterServiceImpl-writeToFile-FileNotFoundException:\n"+ targetFilePath + "\n" + e);
            return false;
        } catch (IOException e) {
            System.out.println("TransmitterServiceImpl-writeToFile-IOException :\n:"+ targetFilePath  + "\n" + e);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                System.out.println("TransmitterServiceImpl-writeToFile-out.close() Exception :\n����:"+ targetFilePath  + "\n" + e);
            }
        }
    }


    public long getFileContenSize(String sourceFilePath) {
        Path sourcePath = Paths.get(sourceFilePath);

        FileChannel sourceChannel = null;

        try {
            sourceChannel = FileChannel.open(sourcePath, StandardOpenOption.READ);

            return sourceChannel.size();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if ( sourceChannel != null ) {
                try {
                    sourceChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean copyFileByNio(String sourceFilePath, String destinationFilePath) {

        if ( getFileContenSize(sourceFilePath) == 0 ) {
            return false;
        }

        Path sourcePath = Paths.get(sourceFilePath);
        Path destinationPath = Paths.get(destinationFilePath);

        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            return true;
        } catch (IOException e) {
            System.out.println("TransmitterServiceImpl-copyFileByNioChannel ���ʿ�����������:\n����-" + sourceFilePath + " ����\n-" + destinationFilePath + "\n" + e);
            return false;
        }

    }

}
