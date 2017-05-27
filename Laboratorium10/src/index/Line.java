package index;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Line {
    private final String rawLine;
    private final int number;
    private final List<Word> words;

    Line(String rawLine, int number) {
        this.rawLine = rawLine;
        this.number = number;
        words = generateWords();
    }

    private List<Word> generateWords() {
        return Stream.of(rawLine.split("\\s+"))
                .map(word -> new Word(word, number))
                .collect(Collectors.toList());
    }

    public List<Word> getWords() {
        return words;
    }
}
