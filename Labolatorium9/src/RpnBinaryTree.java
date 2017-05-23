public class RpnBinaryTree extends BinaryTree<String> {

    public RpnBinaryTree(Node<String> node) {
        super(node);
    }

    public double solve() {
        Node<String> copy = root;
        return solve(copy);
    }

    private double solve(Node<String> node) {
        if (RpnToBinaryTreeConverter.isOperator(node.left.elem)) {
            solve(node.left);
        }
        double left = Double.parseDouble(node.left.elem);

        if (RpnToBinaryTreeConverter.isOperator(node.right.elem)) {
            solve(node.right);
        }

        double right = Double.parseDouble(node.right.elem);
        double result = calculate(node.elem.charAt(0), right, left);
        node.elem = String.valueOf(result);
        return result;
    }

    private double calculate(char operator, double val1, double val2) {
        switch (operator) {
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
            case '/':
                double dzielnik = val2;
                if (dzielnik == 0) {
                    System.err.println("Dizelenie przez 0!");
                    System.exit(-1);
                }
                return val1 / val2;
            default:
                throw new IllegalStateException("Unknown operator " + operator);
        }
    }


}
