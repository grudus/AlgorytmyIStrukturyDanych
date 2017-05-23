import java.util.AbstractMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.stream.Collectors.toMap;

public class RpnConverter {

    public static final Map<Character, Integer> OPERATOR_PRIOR = Stream.of(
            new AbstractMap.SimpleEntry<>('+', 1),
            new AbstractMap.SimpleEntry<>('-', 1),
            new AbstractMap.SimpleEntry<>('*', 2),
            new AbstractMap.SimpleEntry<>('/', 2),
            new AbstractMap.SimpleEntry<>('(', -1),
            new AbstractMap.SimpleEntry<>(')', -1)
    ).collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));


    private String postfix;
    private Stack<Character> operatorStack;

    public RpnConverter() {
        this.postfix = "";
        this.operatorStack = new Stack<>();
    }


    public RpnExpression convert(String expression) {
        StringBuilder builder = new StringBuilder();
        while (!expression.isEmpty()) {
            final char c = expression.charAt(0);

            if (isOperand(c) || isWhitespace(c)) {
                builder.append(c);
            } else if (isOperator(c)) {
                builder.append(processOperator(c));
            } else
                throw new IllegalArgumentException("Cannot read input operator: " + c);

            expression = expression.substring(1);
        }
        while (!operatorStack.empty())
            builder.append(" ").append(operatorStack.pop());
        this.postfix = builder.toString();
        return new RpnExpression(this.postfix);
    }


    private boolean isWhitespace(char c) {
        return Character.isSpaceChar(c);
    }

    private boolean isOperator(char c) {
        return OPERATOR_PRIOR.containsKey(c);
    }

    private String processOperator(char operator) {
        if (operatorStack.empty() || operator == '(')
            operatorStack.push(operator);
        else {
            Character topOp = operatorStack.peek();
            if (OPERATOR_PRIOR.get(operator) > OPERATOR_PRIOR.get(topOp))
                operatorStack.push(operator);
            else {
                String operators = "";
                while (!operatorStack.empty() && OPERATOR_PRIOR.get(operator) <= OPERATOR_PRIOR.get(topOp)) {
                    operators += " " + operatorStack.pop();
                    if (!operatorStack.empty()) {
                        topOp = operatorStack.peek();
                    }
                    if (topOp == '(') {
                        operatorStack.pop();
                        break;
                    }
                }
                if (operator != ')')
                    operatorStack.push(operator);
                return operators;
            }
        }

        return "";
    }


    private boolean isOperand(char c) {
        return Character.isDigit(c);
    }


}
