package index;

import java.util.ArrayList;
import java.util.List;


public class Word implements Comparable<Word> {
    private final String word;
    private List<Integer> lineNumbers;

    public Word(String word, int lineNumber) {
        this.word = word;
        this.lineNumbers = new ArrayList<>();
        lineNumbers.add(lineNumber);
    }

    public Word(String word) {
        this.word = word;
        this.lineNumbers = new ArrayList<>();
    }

    public void addIndex(int lineNumber) {
        lineNumbers.add(lineNumber);
    }

    public int getFirstLineNumber() {
        return lineNumbers.get(0);
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " " + lineNumbers;
    }

    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.getWord());
    }
}
