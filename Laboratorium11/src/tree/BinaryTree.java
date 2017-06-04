package tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Consumer;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@SuppressWarnings("unused")
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree() {
        this(null);
    }

    public BinaryTree(T elem) {
        this.root = new Node<>(elem);
    }

    public void add(T elem) {
        if (root == null)
            root = new Node<>(elem);
        else
            add(elem, root);
    }

    private void add(T elem, Node<T> actual) {
        if (actual.isLessThan(elem))
            addRightChild(elem, actual);
        else
            addLeftChild(elem, actual);
        }

    private void addLeftChild(T elem, Node<T> actual) {
        if (actual.getLeft() == null)
            actual.setLeft(new Node<>(elem));
        else add(elem, actual.getLeft());
    }

    private void addRightChild(T elem, Node<T> actual) {
        if (actual.getRight() == null)
            actual.setRight(new Node<>(elem));
        else add(elem, actual.getRight());
    }

    public Optional<Node<T>> find(T elem) {
        return find(elem, root);
    }

    private Optional<Node<T>> find(T elem, Node<T> actual) {
        if (actual.isLessThan(elem)) {
            if (actual.getRight() != null)
                return find(elem, actual.getRight());
        } else if (actual.isGreaterThan(elem)) {
            if (actual.getLeft() != null)
                return find(elem, actual.getLeft());
        } else if (actual.isEqualTo(elem))
            return of(actual);

        return empty();
    }


    public void printAsTree() {
        printBinaryTree(root, 0);
    }

    public void printInOrder() {
        onEachInOrder(System.out::println);
    }
    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node<T> node) {
        if (node.getLeft() != null)
            printPostOrder(node.getLeft());
        if (node.getRight() != null)
            printPostOrder(node.getRight());

        System.out.printf("%s \n", node);
    }

    public int leafs() {
        return leafs(root);
    }

    private int leafs(Node<T> node) {
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else
            return leafs(node.getLeft()) + leafs(node.getRight());
    }

    public int nodes() {
        return nodes(root);
    }

    private int nodes(Node<T> node) {
        int nodes = 1;
        if (node.getRight() != null) nodes += nodes(node.getRight());
        if (node.getLeft() != null) nodes += nodes(node.getLeft());
        return nodes;
    }

    private void printBinaryTree(Node<T> node, int level) {
        if (node == null)
            return;
        printBinaryTree(node.getRight(), level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                System.out.print("|\t\t");
            System.out.println("|-------" + node);
        } else
            System.out.println(node);
        printBinaryTree(node.getLeft(), level + 1);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }

        int left = height(node.getLeft());
        int right = height(node.getRight());

        return Math.max(left, right) + 1;
    }

    public void printHorizontally() {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();
            System.out.print(temp + " ");
            if (temp.getLeft() != null) queue.add(temp.getLeft());
            if (temp.getRight() != null) queue.add(temp.getRight());
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public void onEachInOrder(Consumer<Node<T>> consumer) {
        onEachInOrder(consumer, root);
    }

    private void onEachInOrder(Consumer<Node<T>> consumer, Node<T> node) {
        if (root.getLeft() != null) {
            onEachInOrder(consumer, root.getLeft());
        }
        consumer.accept(node);
        if (root.getRight() != null) {
            onEachInOrder(consumer, root.getRight());
        }

    }

}
