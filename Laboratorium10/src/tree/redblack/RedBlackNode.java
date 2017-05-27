package tree.redblack;

import tree.Node;

import static tree.redblack.RedBlackNode.Color.BLACK;
import static tree.redblack.RedBlackNode.Color.RED;

public class RedBlackNode<T extends Comparable<T>> implements Node<T> {
    private T value;
    private RedBlackNode<T> parent;
    private RedBlackNode<T> left;
    private RedBlackNode<T> right;
    private Color color;

    RedBlackNode(T elem) {
       this(elem, null);
    }

    RedBlackNode(T elem, RedBlackNode<T> parent) {
        this.parent = parent;
        this.value = elem;
        this.color = RED;
    }


    @Override
    public T getValue() {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Node<T>> void setLeft(E left) {
        this.left = (RedBlackNode<T>) left;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Node<T>> void setRight(E right) {
        this.right = (RedBlackNode<T>) right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RedBlackNode<T> getLeft() {
        return left;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RedBlackNode<T> getRight() {
        return right;
    }

    RedBlackNode<T> grandParent() {
        return parent != null ? parent.getParent() : null;
    }

    RedBlackNode<T> uncle() {
        if (grandParent() == null)
            return null;
        return parent == grandParent().getRight() ? grandParent().getLeft() : grandParent().getRight();
    }

    void switchColor() {
        if (isBlack()) color = RED;
        else color = BLACK;
    }

    void setColor(Color color) {
        this.color = color;
    }

    boolean isBlack() {
        return color == BLACK;
    }

    boolean isRed() {
        return color == RED;
    }

    RedBlackNode<T> getParent() {
        return parent;
    }

    void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public enum Color {
        BLACK("(B)"), RED("<R>");
        public final String symbol;

        Color(String symbol) {
            this.symbol = symbol;
        }
    }

    @Override
    public String toString() {
        return color.symbol + value.toString();
    }
}
