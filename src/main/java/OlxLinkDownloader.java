import java.net.*;
import java.io.*;
import java.util.*;

public class OlxLinkDownloader {
    public static void main(String[] args) throws Exception {

        List<URL> listOfUrls = List
                .of(new URL("https://www.olx.pl/oferty/q-kamera-go-pro/"),
                        new URL("https://www.olx.pl/d/oferty/q-kamera-go-pro/?page=2"),
                        new URL("https://www.olx.pl/oferty/q-kamera-go-pro/?page=3"));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();

        for (URL url : listOfUrls) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
                stringBuilder.append(System.lineSeparator());
            }
            bufferedReader.close();
        }
        Set<String> setOfLinks = new TreeSet<>();
        String content = stringBuilder.toString();

        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("https://www.olx.pl/d/oferta", i);
            if (i < 0)
                break;
            String substring = content.substring(i);
            String link = substring.split(".html")[0];
            link = link + ".html";
            setOfLinks.add(link);
        }

        setOfLinks.forEach(System.out::println);
        System.out.println("Number of links = " + setOfLinks.size());
    }
}
