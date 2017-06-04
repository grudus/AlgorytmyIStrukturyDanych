package sentence;

import java.util.Map;
import java.util.PriorityQueue;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Sentence {
    private final String text;

    public Sentence(String text) {
        this.text = text;
    }

    public Map<Character, Long> charactersCount() {
        return text.chars()
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));
    }

    public PriorityQueue<Letter> characterCountAsQueue() {
        PriorityQueue<Letter> queue = new PriorityQueue<>(Letter::compareTo);
        charactersCount().entrySet().stream()
                .map(entry -> new Letter(entry.getKey(), entry.getValue()))
                .forEach(queue::add);
        return queue;
    }
}
