import index.Word;
import index.WordReader;
import tree.redblack.RedBlackTree;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Lab10 {
    public static void main(String[] args) throws IOException {
        RedBlackTree<Word> tree = new RedBlackTree<>();
        tree.setOnDuplicateKeyAction((elem, node) -> node.getValue().addIndex(elem.getFirstLineNumber()));
        URL url = Lab10.class.getClassLoader().getResource("text");
        File file = new File(url.getPath());
        WordReader reader = new WordReader(file);

        reader.readLines().forEach(line -> {
            line.getWords().forEach(tree::add);
        });

        tree.printInOrder();
    }
}
