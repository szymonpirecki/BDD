package util.scanner;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class FileScanner {

    private static void mergeFiles(String fileFormat) {
        Path outputPath = Paths.get("src/test/resources/merged.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath);
             Stream<Path> featureFiles = Files.walk(Paths.get("src/test/resources/features"))
                     .filter(path -> path.toString().endsWith(fileFormat))) {

            if (featureFiles.findAny().isEmpty()){
                log.error("There are no {} files", fileFormat);
            }

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



}

