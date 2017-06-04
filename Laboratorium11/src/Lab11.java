import sentence.Letter;
import sentence.Sentence;
import tree.HuffmanTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;
import static sentence.Letter.EMPTY;

public class Lab11 {
    private static final String TEXT = "ala i ola.";

    public static void main(String[] args) {
        HuffmanTree tree = createTree();
        tree.printAsTree();

        Map<Character, String> charsToCode = tree.findCode().entrySet()
                .stream().collect(toMap(e -> e.getKey().getLetter(), Map.Entry::getValue));

        tree.findCode().forEach((letter, code) -> System.out.printf("%s|%d|%s\n", letter.getLetter(), letter.getFrequency(), code));

        String encoded = TEXT.chars().mapToObj(i -> charsToCode.get((char) i)).reduce((s1, s2) -> s1 + " " + s2).get();
        System.out.println(encoded);

        System.out.println(tree.decode(encoded.replaceAll(" ", "")));

        writeToFile(encoded);


    }

    private static void writeToFile(String encoded) {
        String filePath = new File("").getAbsolutePath() + "/text.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HuffmanTree createTree() {
        Queue<Letter> letters = new Sentence(TEXT).characterCountAsQueue();
        Queue<HuffmanTree> trees = new PriorityQueue<>(comparing(tree -> tree.getRoot().getValue()));

        while (!letters.isEmpty()) {
            trees.add(new HuffmanTree(letters.poll()));
        }

        while (trees.size() > 1) {
            HuffmanTree first = trees.poll();
            HuffmanTree second = trees.poll();
            HuffmanTree newTree = new HuffmanTree(new Letter(EMPTY, first.getRootFrequency() + second.getRootFrequency()));
            newTree.addLeft(first);
            newTree.addRight(second);
            trees.add(newTree);
        }
        return trees.poll();
    }

}
