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

    public RedBlackNode(T elem) {
       this(elem, null);
    }

    public RedBlackNode(T elem, RedBlackNode<T> parent) {
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


    public Color getColor() {
        return color;
    }

    public void switchColor() {
        if (isBlack()) color = RED;
        else color = BLACK;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return color == BLACK;
    }

    public boolean isRed() {
        return color == RED;
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public enum Color {
        BLACK("#"), RED("(@)");
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
