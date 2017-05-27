package tree;

public class SimpleNode<T extends Comparable<T>> implements Node<T> {
    private final T value;
    private Node<T> left;
    private Node<T> right;

    public SimpleNode(T elem) {
        this.value = elem;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Node<T> getLeft() {
        return left;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Node<T> getRight() {
        return right;
    }

    @Override
    public <E extends Node<T>> void setLeft(E left) {
        this.left = left;
    }

    @Override
    public <E extends Node<T>> void setRight(E left) {
        this.right = left;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
