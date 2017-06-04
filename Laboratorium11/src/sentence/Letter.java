package sentence;

import static java.lang.String.format;

public class Letter implements Comparable<Letter> {
    public static final char EMPTY = '\0';

    private final char letter;
    private final long frequency;

    public Letter(char letter, long frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    public char getLetter() {
        return letter;
    }

    public Long getFrequency() {
        return frequency;
    }

    public boolean isEmpty() {
        return letter == EMPTY;
    }

    @Override
    public String toString() {
        return format("\'%s\'(%d)", letter, frequency);
    }

    @Override
    public int compareTo(Letter o) {
        long freqDiff = frequency - o.getFrequency();
        if (freqDiff == 0)
            return o.getLetter() - letter;
        return (int) freqDiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter1 = (Letter) o;

        if (letter != letter1.letter) return false;
        return frequency == letter1.frequency;
    }

    @Override
    public int hashCode() {
        int result = (int) letter;
        result = 31 * result + (int) (frequency ^ (frequency >>> 32));
        return result;
    }
}
