public class BinaryTree<T> {
    protected Node<T> root;

    public BinaryTree(Node<T> node) {
        this.root = node;
    }

    public void printAsTree() {
        printBinaryTree(root, 0);
    }

    public void printInOrder() {
        printInOrder(root);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    public int leafs() {
        return leafs(root);
    }

    private int leafs(Node<T> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        else
            return leafs(node.left) + leafs(node.right);
    }

    public int nodes() {
        return nodes(root);
    }

    private int nodes(Node<T> node) {
        int nodes = 1;
        if (node.right != null) nodes += nodes(node.right);
        if (node.left != null) nodes += nodes(node.left);
        return nodes;
    }

    private void printPostOrder(Node<T> node) {
        if (node.left != null)
            printPostOrder(node.left);
        if (node.right != null)
            printPostOrder(node.right);

        System.out.printf("%s ", node.elem);
    }

    private void printInOrder(Node<T> root) {
        if (root.left != null) {
            System.out.print("(");
            printInOrder(root.left);
        }
        System.out.print(root.elem);
        if (root.right != null) {
            printInOrder(root.right);
            System.out.print(")");
        }

    }

    private void printBinaryTree(Node<T> node, int level) {
        if (node == null)
            return;
        printBinaryTree(node.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                System.out.print("|\t\t");
            System.out.println("|-------" + node.elem);
        } else
            System.out.println(node.elem);
        printBinaryTree(node.left, level + 1);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }

        int left = height(node.left);
        int right = height(node.right);

        return Math.max(left, right) + 1;
    }

    public static class Node<T> {
        public T elem;
        public Node<T> left;
        public Node<T> right;

        public Node(T elem) {
            this.elem = elem;
        }

        @Override
        public String toString() {
            return elem.toString();
        }
    }
}
