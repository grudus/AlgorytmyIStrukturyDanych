package tree;

public class SimpleTree<T extends Comparable<T>> extends BinaryTree<T, SimpleNode<T>> {

    public SimpleTree(SimpleNode<T> root) {
        super(root, SimpleNode::new);
    }
    public SimpleTree(T value) {
        this(new SimpleNode<>(value));
    }

    public SimpleTree() {
        this((SimpleNode<T>)null);
    }
}
