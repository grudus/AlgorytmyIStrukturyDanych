package tree;

public interface Node<T extends Comparable<T>> {
    T getValue();
    <E extends Node<T>> E getLeft();
    <E extends Node<T>> E getRight();

    <E extends Node<T>> void setLeft(E left);
    <E extends Node<T>> void setRight(E right);

    default boolean isLessThan(T elem) {
        return getValue().compareTo(elem) < 0;
    }

    default boolean isGreaterThan(T elem) {
        return getValue().compareTo(elem) > 0;
    }

    default boolean isEqualTo(T elem) {
        return getValue().compareTo(elem) == 0;
    }
}
