package util.scanner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileScanner {

    private static void mergeFeatureFiles() {
        Path outputPath = Paths.get("src/test/resources/merged.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath);
             Stream<Path> featureFiles = Files.walk(Paths.get("src/test/resources/features"))
                     .filter(path -> path.toString().endsWith(".feature"))) {

            for (Path path : (Iterable<Path>) featureFiles::iterator) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    if (line.isBlank()) {
                        continue;
                    }
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        mergeFeatureFiles();
    }
}

