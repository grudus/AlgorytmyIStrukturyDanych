import sentence.Letter;
import sentence.Sentence;
import tree.HuffmanTree;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;
import static sentence.Letter.EMPTY;

public class Lab11 {
    private static final String TEXT = "aaaabbbccd";
    public static void main(String[] args) {
        Queue<Letter> quee = new Sentence(TEXT).characterCountAsQueue();

        Queue<HuffmanTree> trees = new PriorityQueue<>(comparing(b -> b.getRoot().getValue()));

        while (!quee.isEmpty()) {
            trees.add(new HuffmanTree(quee.poll()));
        }

       while (trees.size() > 1) {
           HuffmanTree first = trees.poll();
           HuffmanTree second = trees.poll();
           HuffmanTree newTree = new HuffmanTree(new Letter(EMPTY, first.getRootFrequency() + second.getRootFrequency()));
           newTree.addLeft(first);
           newTree.addRight(second);
           trees.add(newTree);
       }

       HuffmanTree tree = trees.poll();

        tree.printAsTree();
        Map<Character, String> charsToCode = tree.findCode().entrySet()
                .stream().collect(toMap(e -> e.getKey().getLetter(), Map.Entry::getValue));

        String decoded = TEXT.chars().mapToObj(i -> charsToCode.get((char)i)).reduce((s1, s2) -> s1 + " " + s2).get();
        System.out.println(decoded);

        System.out.println(tree.findCode());

        System.out.println(tree.decode(decoded));


    }
}
