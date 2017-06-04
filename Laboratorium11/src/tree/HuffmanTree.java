package tree;

import sentence.Letter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Comparator.comparingLong;

public class HuffmanTree extends BinaryTree<Letter> implements Comparable<BinaryTree<Letter>> {


    public HuffmanTree(Letter letter) {
        super(letter);
    }

    public long getRootFrequency() {
        return getRoot().getValue().getFrequency();
    }

    public void addLeft(HuffmanTree tree) {
        getRoot().setLeft(tree.getRoot());
    }

    public void addRight(HuffmanTree tree) {
        getRoot().setRight(tree.getRoot());
    }

    public Map<Letter, String> findCode() {
        Map<Letter, String> map = new TreeMap<>(comparingLong(Letter::getFrequency).thenComparing(Letter::getLetter));
        findCode(getRoot(), "", map);
        return map;
    }

    private void findCode(Node<Letter> node, String actual, Map<Letter, String> map) {
        if (node.getLeft() != null) {
            findCode(node.getLeft(), actual + "1", map);
        }
        if (node.getRight() != null)
            findCode(node.getRight(), actual + "0", map);

        if (node.getRight() == null && node.getLeft() == null)
            map.put(node.getValue(), actual);
    }

    public String decode(String word) {
        List<String> decoded = new ArrayList<>();
        decode(getRoot(), word, decoded);
        return decoded.stream().reduce((s1, s2) -> s1 + s2).get();
    }

    private void decode(Node<Letter> node, String word, List<String> decoded) {
        if (word.isEmpty()) {
            decoded.add(node.getValue().getLetter() + "");
            return;
        }
        if (word.charAt(0) == '1') {
            if (node.getLeft() != null) {
                decode(node.getLeft(), word.substring(1), decoded);
            } else {
                decoded.add(node.getValue().getLetter() + "");
                decode(getRoot(), word, decoded);
            }
        }
        if (word.charAt(0) == '0')
            if (node.getRight() != null)
                decode(node.getRight(), word.substring(1), decoded);
            else {
                decoded.add(node.getValue().getLetter() + "");
                decode(getRoot(), word, decoded);
            }
    }

    @Override
    public int compareTo(BinaryTree<Letter> o) {
        return getRoot().getValue().compareTo(o.getRoot().getValue());
    }
}
