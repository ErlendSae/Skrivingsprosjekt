package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class UserToFile {
    //funciton to read lines
    public static List<String> readLines(String path, boolean resource) throws IOException {
        if (!resource) {
            return Files.readAllLines(Path.of(path));
        }

        try (InputStream resourceAsStream = UserToFile.class.getResourceAsStream(path)) {
            if (resourceAsStream == null) {
                throw new IOException("Resource not found");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
                return reader.lines().toList();
            }
        }
    } 
    //Send a user to file
    public static void writeLines(String path, List<String> lines) throws IOException {
        Files.write(Path.of(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        // try {
        //     FileWriter myWriter = new FileWriter(path);
        //     myWriter.write(lines.get(0));
        //     myWriter.close();
        //   } catch (IOException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        //   }
}
}
