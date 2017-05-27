package tree;

@SuppressWarnings("unused")
public class SimpleTree<T extends Comparable<T>> extends BinaryTree<T, SimpleNode<T>> {

    private SimpleTree(SimpleNode<T> root) {
        super(root, SimpleNode::new);
    }

    public SimpleTree(T value) {
        this(new SimpleNode<>(value));
    }

    public SimpleTree() {
        this((SimpleNode<T>)null);
    }
}
