package tree.redblack;

import tree.BinaryTree;

import static tree.redblack.RedBlackNode.Color.BLACK;
import static tree.redblack.RedBlackNode.Color.RED;

public class RedBlackTree<T extends Comparable<T>> extends BinaryTree<T, RedBlackNode<T>> {

    public RedBlackTree(RedBlackNode<T> root) {
        super(root, RedBlackNode::new);
        root.setColor(BLACK);
        onDuplicateKey = (elem, node) -> addLeftChild(elem, (RedBlackNode<T>) node);
    }

    public RedBlackTree(T value) {
        this(new RedBlackNode<>(value));
    }

    public RedBlackTree() {
       super(null, RedBlackNode::new);
    }

    @Override
    public void add(T elem) {
        if (root == null) {
            root = new RedBlackNode<T>(elem);
            root.setColor(BLACK);
        }
        else
            add(elem, root);
    }

    private void add(T elem, RedBlackNode<T> actual) {
        if (actual.isLessThan(elem))
            addRightChild(elem, actual);
        else if (actual.isGreaterThan(elem))
            addLeftChild(elem, actual);
        else onDuplicateKey.accept(elem, actual);
    }

    private void addRightChild(T elem, RedBlackNode<T> actual) {
        if (actual.getRight() == null) {
            RedBlackNode<T> insereted = new RedBlackNode<>(elem, actual);
            actual.setRight(insereted);
            reorganizeTree(insereted, actual);
        } else add(elem, actual.getRight());
    }

    private void addLeftChild(T elem, RedBlackNode<T> actual) {
        if (actual.getLeft() == null) {
            RedBlackNode<T> insereted = new RedBlackNode<>(elem, actual);
            actual.setLeft(insereted);
            reorganizeTree(insereted, actual);
        } else add(elem, actual.getLeft());
    }

    private void reorganizeTree(RedBlackNode<T> inserted, RedBlackNode<T> parent) {
        if (parent.isBlack()) {
            inserted.setParent(parent);
            return;
        }

        RedBlackNode<T> grandParent = parent.getParent();
        RedBlackNode<T> uncle = grandParent.getLeft() == parent ? grandParent.getRight() : grandParent.getLeft();

        if (uncle != null && uncle.isRed())
            handleRedUncle(parent, uncle, grandParent);
        else if (blackUncleAndInnerChild(inserted, parent, grandParent))
            rotateInner(inserted, parent, grandParent);
        else if (blackUncleAndOuterChild(inserted, parent, grandParent))
            rotateOuter(inserted, parent, grandParent, grandParent.getLeft() == parent);


    }

    private void rotateInner(RedBlackNode<T> inserted, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
        boolean isLeftChild = grandParent.getLeft() == parent;
        if (isLeftChild) {
            parent.setRight(null);
            inserted.setLeft(parent);
            grandParent.setLeft(inserted);
        } else {
            parent.setLeft(null);
            inserted.setRight(parent);
            grandParent.setRight(inserted);
        }
        parent.setParent(inserted);

        rotateOuter(parent, inserted, grandParent, isLeftChild);
    }

    private void rotateOuter(RedBlackNode<T> inserted, RedBlackNode<T> parent, RedBlackNode<T> grandParent, boolean isLeftChild) {
        RedBlackNode<T> grandGrandParent = grandParent.getParent();
        if (isLeftChild) {
            RedBlackNode<T> rightSon = parent.getRight();
            parent.setRight(grandParent);
            grandParent.setLeft(rightSon);
        } else {
            RedBlackNode<T> leftSon = parent.getLeft();
            parent.setLeft(grandParent);
            grandParent.setRight(leftSon);

        }
        grandParent.setParent(parent);
        handleNewChild(grandGrandParent, parent);
        parent.switchColor();
        grandParent.switchColor();
    }

    private void handleNewChild(RedBlackNode<T> parent, RedBlackNode<T> child) {
        if (parent == null) {
            root = child;
        } else if (parent.isLessThan(child.getValue())) {
            parent.setRight(child);
        } else {
            parent.setLeft(child);
        }
        child.setParent(parent);

    }

    private boolean blackUncleAndInnerChild(RedBlackNode<T> inserted, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
        boolean isLeftChild = grandParent.getLeft() == parent;
        RedBlackNode<T> uncle = isLeftChild ? grandParent.getRight() : grandParent.getLeft();
        return (uncle != null && uncle.isBlack() || uncle == null) && (isLeftChild
                ? inserted == parent.getRight()
                : inserted == parent.getLeft());
    }


    private boolean blackUncleAndOuterChild(RedBlackNode<T> inserted, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
        boolean isLeftChild = grandParent.getLeft() == parent;
        RedBlackNode<T> uncle = isLeftChild ? grandParent.getRight() : grandParent.getLeft();
        return (uncle != null && uncle.isBlack() || uncle == null) && (isLeftChild
                ? inserted == parent.getLeft()
                : inserted == parent.getRight());
    }

    private void handleRedUncle(RedBlackNode<T> parent, RedBlackNode<T> uncle, RedBlackNode<T> grandParent) {
        parent.setColor(BLACK);
        uncle.setColor(BLACK);
        grandParent.setColor(RED);

        if (isRoot(grandParent)) {
            grandParent.setColor(BLACK);
            return;
        }
        reorganizeTree(grandParent, grandParent.getParent());
    }


    private boolean isRoot(RedBlackNode<T> grandParent) {
        return grandParent.getParent() == null;
    }
}
