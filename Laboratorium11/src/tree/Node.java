package tree;

public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }


    public boolean isLessThan(T elem) {
        return getValue().compareTo(elem) < 0;
    }
    public boolean isGreaterThan(T elem) {
        return getValue().compareTo(elem) > 0;
    }
    public boolean isEqualTo(T elem) {
        return getValue().compareTo(elem) == 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
