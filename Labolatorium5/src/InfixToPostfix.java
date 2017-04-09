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


    public String convert(String expression) {
        stosOperatorow = new Stos<>();
        postfix = "";
        while (!expression.isEmpty()) {
            final char c = expression.charAt(0);

            if (jestOperand(c) || jestSpacja(c))
                postfix += c;
            else if (jestOperator(c)) {
                postfix += processOperatorSpacja(c);
            } else
                throw new IllegalArgumentException("Nie mozna przetworzyc " + c);

            expression = expression.substring(1);
        }
        while (!stosOperatorow.empty())
            postfix += stosOperatorow.pop();
        return postfix;
    }

    private boolean jestSpacja(char c) {
        return Character.isSpaceChar(c);
    }

    private boolean jestOperator(char c) {
        return operatorPrior.containsKey(c);
    }

    private String processOperatorSpacja(char c) {
        if (stosOperatorow.empty() || c == '(')
            stosOperatorow.push(c);
        else {
            Character topOp = stosOperatorow.peek();
            if (operatorPrior.get(c) > operatorPrior.get(topOp))
                stosOperatorow.push(c);
            else {
                String operators = "";
                while (!stosOperatorow.empty() && operatorPrior.get(c) <= operatorPrior.get(topOp)) {
                    operators += stosOperatorow.pop();
                    if (!stosOperatorow.empty()) {
                        topOp = stosOperatorow.peek();
                    }
                    if (topOp == '(') {
                        stosOperatorow.pop();
                        break;
                    }
                }
                if (c != ')')
                    stosOperatorow.push(c);
                return operators;
            }
        }

        return "";
    }

    private String processOperator(char c) {
        if (stosOperatorow.empty())
            stosOperatorow.push(c);
        else {
            Character topOp = stosOperatorow.peek();
            if (operatorPrior.get(c) > operatorPrior.get(topOp))
                stosOperatorow.push(c);
            else {
                String operators = "";
                while (!stosOperatorow.empty() && operatorPrior.get(c) <= operatorPrior.get(topOp)) {
                    operators += stosOperatorow.pop();
                    if (!stosOperatorow.empty()) {
                        topOp = stosOperatorow.peek();
                    }
                }
                stosOperatorow.push(c);
                return operators;
            }
        }

        return "";
    }

    private boolean jestOperand(char c) {
        return Character.isDigit(c);
    }


}
