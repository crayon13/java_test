package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebCrawler {
    public String getConent(String sourceUrl) {
        URL url = null;
        try {
            url = new URL(sourceUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            InputStreamReader
                    inputStreamReader = new InputStreamReader((InputStream) url.getContent());


            StringBuilder content = new StringBuilder();
            char[] buf = new char[1024];
            for (int n = inputStreamReader.read(buf); n > -1; n = inputStreamReader.read(buf))
                content.append(buf, 0, n);
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }
}
