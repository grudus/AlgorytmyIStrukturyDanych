import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.stream.Collectors.toMap;

public class InfixToPostfix {

    private static final Map<Character, Integer> operatorPrior = Stream.of(
            new SimpleEntry<>('+', 1),
            new SimpleEntry<>('-', 1),
            new SimpleEntry<>('*', 2),
            new SimpleEntry<>('/', 2),
            new SimpleEntry<>('(', -1),
            new SimpleEntry<>(')', -1)
    ).collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));


    private String postfix;
    private Stos<Character> stosOperatorow;

    public InfixToPostfix() {
        this.postfix = "";
        this.stosOperatorow = new Stos<>();
    }


    public String convert(String expression) {
        StringBuilder builder = new StringBuilder();
        while (!expression.isEmpty()) {
            final char c = expression.charAt(0);

            if (jestOperand(c) || jestSpacja(c)) {
                builder.append(c);
            }
            else if (jestOperator(c)) {
                builder.append(processOperator(c));
            } else
                throw new IllegalArgumentException("Nie mozna przetworzyc " + c);

            expression = expression.substring(1);
        }
        while (!stosOperatorow.empty())
            builder.append(" ").append(stosOperatorow.pop());
        this.postfix = builder.toString();
        return this.postfix;
    }

    public String prettyFormat() {
        return this.postfix.trim().replaceAll(" +", " ");
    }


    private boolean jestSpacja(char c) {
        return Character.isSpaceChar(c);
    }

    private boolean jestOperator(char c) {
        return operatorPrior.containsKey(c);
    }

    private String processOperator(char operator) {
        if (stosOperatorow.empty() || operator == '(')
            stosOperatorow.push(operator);
        else {
            Character topOp = stosOperatorow.peek();
            if (operatorPrior.get(operator) > operatorPrior.get(topOp))
                stosOperatorow.push(operator);
            else {
                String operators = "";
                while (!stosOperatorow.empty() && operatorPrior.get(operator) <= operatorPrior.get(topOp)) {
                    operators +=  " " + stosOperatorow.pop();
                    if (!stosOperatorow.empty()) {
                        topOp = stosOperatorow.peek();
                    }
                    if (topOp == '(') {
                        stosOperatorow.pop();
                        break;
                    }
                }
                if (operator != ')')
                    stosOperatorow.push(operator);
                return operators;
            }
        }

        return "";
    }


    private boolean jestOperand(char c) {
        return Character.isDigit(c);
    }


}
