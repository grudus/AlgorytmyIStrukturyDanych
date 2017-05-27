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

        List<String> asList = expression.asList();
        for (int i = 0; i < asList.size(); i++) {
            String s = asList.get(i);
            RpnBinaryTree.Node<String> node = new RpnBinaryTree.Node<>(s);
            if (isOperator(s)) {
                node.right = stack.pop();
                node.left = stack.pop();
            }
            if (i < asList.size() - 1 && asList.get(i+1).equals("-")) {
                node.elem = "-" + node.elem;
                i++;
            }
            stack.push(node);
        }

        return new RpnBinaryTree(stack.pop());
    }

    public static boolean isOperator(String s) {
        return s.length() == 1 && OPERATORS.contains(s.charAt(0));
    }
}
