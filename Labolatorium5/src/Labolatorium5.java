import java.util.Arrays;

public class Labolatorium5 {
    public static void main(String[] args) {
        InfixToPostfix converter = new InfixToPostfix();

        String infixExpression = "(4 + (6 * 6)) / 10";
        converter.convert(infixExpression);

        System.out.println("INFIX: " + infixExpression);
        System.out.println("POSTFIX: " + converter.prettyFormat());
        System.out.println("WYNIK: " + new RpnCalculator(converter.prettyFormat()).calculate());
    }
}
