import java.util.Map;
import java.util.stream.Stream;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.stream.Collectors.toMap;

public class InfixToPostfix {
    private final Stos<Integer> liczby;
    private final Stos<Character> operatory;

    private static final Map<Character, Integer> operatorPrior = Stream.of(
            new SimpleEntry<>('+', 1),
            new SimpleEntry<>('-', 1),
            new SimpleEntry<>('*', 2),
            new SimpleEntry<>('/', 2)
    ).collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue));


    private String postfix;

    public InfixToPostfix() {
        this.liczby = new Stos<>();
        this.operatory = new Stos<>();
    }

    public String convert(String expression) {
        Stos<Character> stack = new Stos<>();
        String postfix = "";
        while (!expression.isEmpty()) {
            final char c = expression.charAt(0);

            if (jestOperand(c) || jestSpacja(c))
                postfix += c;
            else if (jestOperator(c)) {
                postfix += processOperator(c, stack);
            } else
                throw new IllegalArgumentException("Nie mozna przetworzyc " + c);

            expression = expression.substring(1);
        }
        while (!stack.empty())
            postfix += stack.pop();
        return postfix;
    }

    private boolean jestSpacja(char c) {
        return Character.isSpaceChar(c);
    }

    private boolean jestOperator(char c) {
        return operatorPrior.containsKey(c);
    }

    private String processOperator(char c, Stos<Character> stos) {
        if (stos.empty())
            stos.push(c);
        else {
            Character topOp = stos.peek();
            if (operatorPrior.get(c) > operatorPrior.get(topOp))
                stos.push(c);
            else {
                String operators = "";
                while (!stos.empty() && operatorPrior.get(c) <= operatorPrior.get(topOp)) {
                    operators += stos.pop();
                    if (!stos.empty()) {
                        topOp = stos.peek();
                    }
                }
                stos.push(c);
                return operators;
            }
        }

        return "";
    }

    private boolean jestOperand(char c) {
        return Character.isDigit(c);
    }


}
