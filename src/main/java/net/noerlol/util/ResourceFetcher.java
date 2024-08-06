package net.noerlol.util;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ResourceFetcher {
    private final Class<?> clazz;
    private final String filePath;
    public ResourceFetcher(Class<?> clazz, String filePath) {
        this.clazz = clazz;
        this.filePath = filePath;
    }

    public NImage getImage() throws IOException {
        NImage image;
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new NullPointerException("image path: " + filePath + " was null");
        }

        image = (NImage) ImageIO.read(inputStream);
        inputStream.close();
        return image;
    }

    public String[] getFileText() throws IOException {
        BufferedReader reader;
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new NullPointerException((String.format("file path %s was null", filePath)));
        }

        ArrayList<String> lines = new ArrayList<>();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines.toArray(new String[lines.size()]);
    }
}
