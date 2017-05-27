import tree.BinaryTree;
import tree.redblack.RedBlackTree;


public class Lab10 {
    public static void main(String[] args) {

        BinaryTree<Integer, ?> tree = new RedBlackTree<>(0);
//
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        tree.add(10);

//        tree.add(2);
//        tree.add(1);

        tree.printAsTree();
    }
}
