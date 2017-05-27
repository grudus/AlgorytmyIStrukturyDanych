package tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@SuppressWarnings("unused")
public abstract class BinaryTree<T extends Comparable<T>, E extends Node<T>> {
    protected E root;
    private final Function<T, ? extends Node<T>> elemToNode;

    protected BiConsumer<T, ? super Node<T>> onDuplicateKey;

    public BinaryTree(E root, Function<T, Node<T>> elemToNode) {
        this.root = root;
        this.elemToNode = elemToNode;
        onDuplicateKey = this::addLeftChild;
    }

    @SuppressWarnings("unchecked")
    public void add(T elem) {
        if (root == null)
            root = (E) elemToNode.apply(elem);
        else
            add(elem, root);
    }

    private void add(T elem, Node<T> actual) {
        if (actual.isLessThan(elem))
            addRightChild(elem, actual);
        else if (actual.isGreaterThan(elem))
            addLeftChild(elem, actual);
        else
            onDuplicateKey.accept(elem, actual);
    }

    private void addLeftChild(T elem, Node<T> actual) {
        if (actual.getLeft() == null)
            actual.setLeft(elemToNode.apply(elem));
        else add(elem, actual.getLeft());
    }

    private void addRightChild(T elem, Node<T> actual) {
        if (actual.getRight() == null)
            actual.setRight(elemToNode.apply(elem));
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
        printInOrder(root);
    }

    private void printInOrder(Node<T> root) {
        if (root.getLeft() != null) {
            printInOrder(root.getLeft());
        }
        System.out.println(root);
        if (root.getRight() != null) {
            printInOrder(root.getRight());
        }

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

    public void setOnDuplicateKeyAction(BiConsumer<T, Node<T>> onDuplicateKey) {
        this.onDuplicateKey = onDuplicateKey;
    }
}
