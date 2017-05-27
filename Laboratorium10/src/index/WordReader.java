package index;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class WordReader {
    private final File file;

    public WordReader(File file) {
        this.file = file;
    }

    public List<Line> readLines() throws IOException {
        List<String> lines =  Files.lines(file.toPath())
                .map(this::convertLine)
                .collect(toList());


        return range(0, lines.size())
                .mapToObj(i -> new Line(lines.get(i), i + 1))
                .collect(toList());
    }

    private String convertLine(String s) {
        return s.replaceAll("[-,.;\'\"]", " ").trim();
    }
}
