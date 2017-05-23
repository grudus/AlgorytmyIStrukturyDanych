import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;

public class RpnToBinaryTreeConverter {
    private final RpnExpression expression;
    private static final List<Character> OPERATORS = Collections.unmodifiableList(asList('+', '-', '/', '*'));

    public RpnToBinaryTreeConverter(RpnExpression expression) {
        this.expression = expression;
    }

    public RpnBinaryTree convert() {
        Stack<BinaryTree.Node<String>> stack = new Stack<>();

        for (String s : expression.asList()) {
            RpnBinaryTree.Node<String> node = new RpnBinaryTree.Node<>(s);
            if (isOperator(s)) {
                node.left = stack.pop();
                node.right = stack.pop();
            }
            stack.push(node);
        }

        return new RpnBinaryTree(stack.pop());
    }

    public static boolean isOperator(String s) {
        return OPERATORS.contains(s.charAt(0));
    }
}
